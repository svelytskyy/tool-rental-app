package com.toolrental.service;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 * Service class to handle input validation.
 */
@Service
public class ValidationService {

    /**
     * Validates the input parameters for the checkout process.
     * @param rentalDays The number of rental days.
     * @param discountPercent The discount percentage.
     * @param checkoutDate The checkout date.
     */
    public void validateInputs(int rentalDays, int discountPercent, LocalDate checkoutDate) {
        validateRentalDays(rentalDays);
        validateDiscountPercent(discountPercent);
        validateCheckoutDate(checkoutDate);
    }

    /**
     * Validates the number of rental days.
     * @param rentalDays The number of rental days.
     */
    public void validateRentalDays(int rentalDays) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater");
        }
    }

    /**
     * Validates the discount percentage.
     * @param discountPercent The discount percentage.
     */
    public void validateDiscountPercent(int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }
    }

    /**
     * Validates the checkout date.
     * @param checkoutDate The checkout date.
     */
    public void validateCheckoutDate(LocalDate checkoutDate) {
        if (checkoutDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Checkout date cannot be in the past");
        }
    }
}
