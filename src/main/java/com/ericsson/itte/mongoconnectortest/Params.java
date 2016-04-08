package com.ericsson.itte.mongoconnectortest;

/**
 * Useful constants
 */
public class Params {
    public static final String MONGO_IP = "127.0.0.1";
    public static final int MONGO_HTTP_PORT = 28017;
    public static final String MONGO_HOSTNAME = "my-mongo";
    public static final int MONGO_PORT = 27017;

    public static final String COLON = ":";
    public static final String SLASH = "/";
    public static final String HTTP = "http" + COLON + SLASH + SLASH;

    public static final String TEST_MONGO = "testmongo";
    public static final String PERSON = "person";
    public static final String TEST_MONGO_PERSON_PATH = SLASH + TEST_MONGO + SLASH + PERSON + SLASH;

    public static final String MIME_TYPE_TEXT_PLAIN = "text/plain";
}
