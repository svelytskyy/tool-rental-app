package com.toolrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for the Tool Rental Application.
 */
@SpringBootApplication
public class ToolRentalApplication {

    private static final Logger logger = LoggerFactory.getLogger(ToolRentalApplication.class);

    public static void main(String[] args) {
        logger.info("Starting ToolRentalApplication...");
        SpringApplication.run(ToolRentalApplication.class, args);
        logger.info("ToolRentalApplication started successfully.");
    }
}
