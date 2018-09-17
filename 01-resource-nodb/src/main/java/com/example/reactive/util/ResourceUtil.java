package com.example.reactive.util;


import com.example.reactive.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;

public class ResourceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUtil.class);

    public static Resource makeResource(Long id) {
        sleepForRandomTime(id);
        return new Resource(id, valueOf(id), format("/resources/%s", id), id * 10);
    }

    private static void sleepForRandomTime(Long id) {
        try {
            seeIfExceptionalCase(id);
            sleep(2000);
            LOGGER.info("Resource {} created", id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void seeIfExceptionalCase(Long id) {
        if(id%19==0)
            throw new RuntimeException("Excptional case: " + id);
    }
}