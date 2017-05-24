package com.jd.githubtwitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseManager {

    protected static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Return the config file
     * @return
     */
    protected Properties getConfig() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream("config.properties");
            // load a properties file
            prop.load(input);
        } catch (IOException io) {
            log.error("Fail to get property file");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error("Fail to close the property file");
                }
            }
        }
        return prop;
    }
}
