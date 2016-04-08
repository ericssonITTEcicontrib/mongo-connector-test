package com.ericsson.itte.mongoconnectortest.util;

import com.ericsson.itte.mongoconnectortest.Params;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.bson.Document;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

/**
 * Util class for MongoDB
 */
@Log4j2 public class MongoUtil {

    /**
     * check via rest api if mongo replica set is initiated
     * <p>
     * e.g, GET http://142.133.111.170:28017/replSetGetStatus
     * </p>
     *
     * @param mongoIP       the ip of the mongo server
     * @param mongoHttpPort the port for the rest api of the mongo server
     * @param hostname      the hostname of the mongo server
     * @param mongoPort     the port of the mongo server
     */
    public static void checkReplicaSet(String mongoIP, int mongoHttpPort, String hostname,
        int mongoPort) {
        String base_uri = Params.HTTP + mongoIP;
        String expected_name = hostname + Params.COLON + mongoPort;
        RestAssured.given().log().path().baseUri(base_uri).port(mongoHttpPort).
            get(Params.REPL_SET_GET_STATUS_PATH).then().log().ifError().assertThat().statusCode(HttpStatus.SC_OK)
            .contentType(ContentType.JSON).body("members",
            Matchers.hasItem(Matchers.hasEntry(Matchers.is("name"), Matchers.is(expected_name))));
    }

    /**
     * create ten person documents in mongo server
     * <p>
     * using db {@link Params#TEST_MONGO} and collection {@link Params#PERSON}
     * </p>
     *
     * @param mongoIP       the ip of the mongo server
     * @param mongoPort     the port of the mongo server
     * @param mongoHttpPort the port for the rest api of the mongo server
     */
    public static void create10Persons(String mongoIP, int mongoPort, int mongoHttpPort) {

        MongoClient mongoClient = new MongoClient(mongoIP, mongoPort);
        MongoDatabase database = mongoClient.getDatabase(Params.TEST_MONGO);
        MongoCollection<Document> collection = database.getCollection(Params.PERSON);
        collection.insertMany(get10PersonDocs());
        mongoClient.close();
        // verify
        // http://142.133.111.170:28017/testmongo/person/
        String base_uri = Params.HTTP + mongoIP;

        // content type is not that precise from mongo-http interface
        RestAssured.registerParser(Params.MIME_TYPE_TEXT_PLAIN, Parser.JSON);
        RestAssured.given().contentType(ContentType.JSON).baseUri(base_uri).port(mongoHttpPort).
            get(Params.TEST_MONGO_PERSON_PATH).then().assertThat().statusCode(HttpStatus.SC_OK).
            //contentType(ContentType.JSON).
                body("total_rows", Matchers.equalTo(10));
        RestAssured.unregisterParser("text/plain");
    }

    private static List<Document> get10PersonDocs() {
        List<Document> docs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            docs.add(new Document("name", Integer.toString(i)));
        }
        return docs;
    }

    /**
     * clean up test db in the mongo server
     * <p>
     * delete db {@link Params#TEST_MONGO}
     * </p>
     *
     * @param mongoIP       the ip of the mongo server
     * @param mongoPort     the port of the mongo server
     * @param mongoHttpPort the port for the rest api of the mongo server
     */
    public static void cleanUp(String mongoIP, int mongoPort, int mongoHttpPort) {
        MongoClient mongoClient = new MongoClient(mongoIP, mongoPort);
        MongoDatabase database = mongoClient.getDatabase(Params.TEST_MONGO);
        database.drop();
        mongoClient.close();

        // verify
        // http://142.133.111.170:28017/testmongo/person/
        RestAssured.registerParser(Params.MIME_TYPE_TEXT_PLAIN, Parser.JSON);
        String base_uri = Params.HTTP + mongoIP;
        RestAssured.given().contentType(ContentType.JSON).baseUri(base_uri).port(mongoHttpPort).
            get(Params.TEST_MONGO_PERSON_PATH).then().assertThat().statusCode(HttpStatus.SC_OK).
            //contentType(ContentType.JSON).
                body("total_rows", Matchers.equalTo(0));
        RestAssured.unregisterParser("text/plain");
    }
}
