package com.ericsson.itte.mongoconnectortest.util;

import com.ericsson.itte.mongoconnectortest.Params;
import lombok.extern.log4j.Log4j2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * General Utility class
 */
@Log4j2 public class CommonUtil {
    /**
     * thread sleep for the given {@code seconds} seconds
     * <p>
     *     error will be logged if {@link InterruptedException} is encountered
     * </p>
     * @param duration the duration
     */
    public static void sleep(int duration){
        log.info("sleep for " + duration + " seconds ...");
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(duration));
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * clean up the testing env.
     * @param stage the stage
     */
    public static void cleanUp(String stage) {
        log.info("cleanUp in stage: " + stage);
        MongoUtil.cleanUp(Params.MONGO_IP, Params.MONGO_PORT, Params.MONGO_HTTP_PORT);
        sleep(10);
        ESUtil.noIndex(Params.ES_IP, Params.ES_PORT);
    }

    /**
     * get a random number
     *
     * @param bound the upper bound (exclusive).  Must be positive.
     * @return an {@code int} value between zero (inclusive) and {@code bound} (exclusive)
     */
    public static int getRandomInt(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
}
