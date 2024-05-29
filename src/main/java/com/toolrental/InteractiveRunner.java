package com.toolrental;

import com.toolrental.model.RentalAgreement;
import com.toolrental.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class handles the interactive console input for the tool rental application.
 */
@Component
public class InteractiveRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InteractiveRunner.class);

    @Autowired
    private CheckoutService checkoutService;

    @Value("${runInteractive:false}")
    private boolean runInteractive;

    @Override
    public void run(String... args) throws Exception {
        logger.info("runInteractive property: {}", runInteractive);

        if (!runInteractive) {
            logger.info("Skipping interactive mode. Set -DrunInteractive=true to enable.");
            return; // Skip running logic if the property is not set
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter tool code (or type 'exit' to quit): ");
                String toolCode = scanner.nextLine();
                if (toolCode.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter rental days: ");
                int rentalDays;
                try {
                    rentalDays = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    logger.error("Invalid number of rental days. Please enter a valid number.");
                    continue;
                }

                System.out.print("Enter discount percent: ");
                int discountPercent;
                try {
                    discountPercent = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    logger.error("Invalid discount percent. Please enter a valid number.");
                    continue;
                }

                System.out.print("Enter checkout date (yyyy-MM-dd): ");
                String checkoutDateString = scanner.nextLine();
                LocalDate checkoutDate;
                try {
                    checkoutDate = LocalDate.parse(checkoutDateString);
                } catch (Exception e) {
                    logger.error("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
                    continue;
                }

                try {
                    RentalAgreement rentalAgreement = checkoutService.checkout(toolCode, rentalDays, discountPercent, checkoutDate);
                    rentalAgreement.printAgreement();
                } catch (IllegalArgumentException e) {
                    logger.error("Error: {}", e.getMessage());
                }
            }
        }

        logger.info("Thank you for using the Tool Rental application!");
    }
}
