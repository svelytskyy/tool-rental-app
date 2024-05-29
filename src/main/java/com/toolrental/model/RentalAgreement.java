package com.toolrental.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Model class for Rental Agreement.
 */
public class RentalAgreement {

    private static final Logger logger = LoggerFactory.getLogger(RentalAgreement.class);

    private String toolCode;
    private String toolType;
    private String brand;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    /**
     * Constructor for RentalAgreement.
     * 
     * @param toolCode The tool code.
     * @param toolType The tool type.
     * @param brand The brand of the tool.
     * @param rentalDays The number of rental days.
     * @param checkoutDate The checkout date.
     * @param dueDate The due date.
     * @param dailyRentalCharge The daily rental charge.
     * @param chargeDays The number of chargeable days.
     * @param preDiscountCharge The charge before discount.
     * @param discountPercent The discount percentage.
     * @param discountAmount The discount amount.
     * @param finalCharge The final charge after discount.
     */
    public RentalAgreement(String toolCode, String toolType, String brand, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, double dailyRentalCharge, int chargeDays, double preDiscountCharge, int discountPercent, double discountAmount, double finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    /**
     * Prints the rental agreement.
     */
    public void printAgreement() {
        logger.info("Printing rental agreement...");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println("Tool code: " + toolCode);
        System.out.println("Tool type: " + toolType);
        System.out.println("Tool brand: " + brand);
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(formatter));
        System.out.println("Due date: " + dueDate.format(formatter));
        System.out.println("Daily rental charge: $" + dailyRentalCharge);
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: $" + preDiscountCharge);
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: $" + discountAmount);
        System.out.println("Final charge: $" + finalCharge);
        logger.info("Rental agreement printed.");
    }
}
