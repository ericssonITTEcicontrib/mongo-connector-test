package com.ericsson.itte.mongoconnectortest.util;

import com.ericsson.itte.mongoconnectortest.Params;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

/**
 * Util class for ES
 */
@Log4j2 public class ESUtil {

    /**
     * check if the es server is started
     * <p>
     * e.g, GET http://142.133.111.170:9200
     * </p>
     *
     * @param esIP       the ip of the es server
     * @param esHttpPort the port for the rest api of the es server
     */
    public static void checkESStarted(String esIP, int esHttpPort) {
        String base_uri = Params.HTTP + esIP;
        RestAssured.given().log().path().baseUri(base_uri).port(esHttpPort).
            get(Params.ROOT_PATH).then().log().ifError().assertThat().statusCode(HttpStatus.SC_OK)
            .contentType(ContentType.JSON);
    }

    /**
     * check if the documents for 10 persons are in the es server
     * <p>
     * using index {@link Params#TEST_MONGO} and type {@link Params#PERSON}
     * </p>
     *
     * @param esIP       the ip of the es server
     * @param esHttpPort the http port of the es server
     */
    public static void check10Persons(String esIP, int esHttpPort) {
        String base_uri = Params.HTTP + esIP;
        for (int i = 0; i < 10; i++) {
            String body = "{\n" + "  \"query\": { \"match\": { \"name\": " + i + " } }\n" + "}";
            RestAssured.given().log().path().log().body().baseUri(base_uri).port(esHttpPort).
                body(body).
                post(Params.TESTMONGO_PERSON_SEARCH_PATH).then().log().ifError().assertThat()
                .statusCode(HttpStatus.SC_OK).contentType(ContentType.JSON)
                .body("hits.total", Matchers.greaterThanOrEqualTo(1));
        }
    }

    /**
     * check non-existence of the index
     * <p>
     * using index {@link Params#TEST_MONGO}
     * </p>
     *
     * @param esIP       the ip of the es server
     * @param esHttpPort the http port of the es server
     */
    public static void noIndex(String esIP, int esHttpPort) {
        String base_uri = Params.HTTP + esIP;

        RestAssured.given().log().path().log().body().baseUri(base_uri).port(esHttpPort).
            get(Params.TESTMONGO_SEARCH_PATH).then().assertThat()
            .statusCode(HttpStatus.SC_NOT_FOUND);

    }

    /**
     * check if the document for 1 persons are in the es server
     * <p>
     * using index {@link Params#TEST_MONGO} and type {@link Params#PERSON}
     * </p>
     *
     * @param esIP       the ip of the es server
     * @param esHttpPort the http port of the es server
     * @param name       the name to check
     */
    public static void check1Persons(String esIP, int esHttpPort, int name) {
        String base_uri = Params.HTTP + esIP;
        String body = "{\n" + "  \"query\": { \"match\": { \"name\": " + name + " } }\n" + "}";
        RestAssured.given().log().path().log().body().baseUri(base_uri).port(esHttpPort).
            body(body).
            post(Params.TESTMONGO_PERSON_SEARCH_PATH).then().log().ifError().assertThat()
            .statusCode(HttpStatus.SC_OK).contentType(ContentType.JSON)
            .body("hits.total", Matchers.greaterThanOrEqualTo(1));
    }
}
