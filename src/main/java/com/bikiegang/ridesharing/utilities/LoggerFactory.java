package com.bikiegang.ridesharing.utilities;

import org.apache.log4j.*;

import java.io.File;

/**
 * Created by hpduy17 on 12/2/14.
 */
public class LoggerFactory {
    public static final String REQUEST = "[ CLIENT REQUEST ] - ";
    public static final String RESPONSE = "[ SERVER RESPONSE ] - ";
    public static Logger createLogger(Class c) {
        Logger logger = Logger.getLogger(c);
        try {
            Layout layout = new PatternLayout("%d{yyyy/MM/dd hh:mm:ss} %5p %c{1}:%L - %m%n");
            String logFilePath = Path.getLogPath()+ File.separator+"fun-ride.log";
            File logFile = new File(logFilePath);
            if(!logFile.exists())
                logFile.createNewFile();
            FileAppender appender = new FileAppender(layout, logFilePath, true);
            logger.addAppender(appender);
            logger.setLevel(Level.ALL);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return logger;
    }
}
