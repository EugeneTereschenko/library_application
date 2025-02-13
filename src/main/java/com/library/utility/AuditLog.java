package com.library.utility;
// Logs actions like book checkouts and returns.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditLog {
    private static final Logger logger = LoggerFactory.getLogger(AuditLog.class);

    public static void log(String message) {
        logger.info(message);
    }
}
