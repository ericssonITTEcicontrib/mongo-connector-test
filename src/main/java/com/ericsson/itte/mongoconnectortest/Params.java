package com.ericsson.itte.mongoconnectortest;

/**
 * Useful constants
 */
public class Params {
    public static final String BASE_IP = "127.0.0.1";

    public static final String MONGO_IP = BASE_IP;
    public static final int MONGO_HTTP_PORT = 28017;
    public static final String MONGO_HOSTNAME = "my-mongo";
    public static final int MONGO_PORT = 27017;

    public static final String ES_IP = BASE_IP;
    public static final int ES_PORT = 9200;

    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String HTTP = "http" + COLON + SLASH + SLASH;

    public static final String TEST_MONGO = "testmongo";
    public static final String PERSON = "person";
    public static final String NAME = "name";
    public static final String FILTER = "filter";
    public static final String UNDERLINE = "_";

    public static final String REPL_SET_GET_STATUS_PATH = SLASH + "replSetGetStatus";
    public static final String TEST_MONGO_PERSON_PATH = SLASH + TEST_MONGO + SLASH + PERSON + SLASH;

    public static final String MIME_TYPE_TEXT_PLAIN = "text/plain";

    public static final String ROOT_PATH = SLASH;
    public static final String TESTMONGO_SEARCH_PATH = SLASH + TEST_MONGO + SLASH + "_search";
    public static final String TESTMONGO_PERSON_SEARCH_PATH = SLASH + TEST_MONGO + SLASH + PERSON + SLASH + "_search";

    public static final int SPECIAL_INDEX = 23;
}
