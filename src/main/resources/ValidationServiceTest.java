package com.toolrental.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationServiceTest {

    private ValidationService validationService = new ValidationService();

    @Test
    public void testValidateRentalDays() {
        assertThrows(IllegalArgumentException.class, () -> validationService.validateRentalDays(0));
        assertDoesNotThrow(() -> validationService.validateRentalDays(1));
    }

    @Test
    public void testValidateDiscountPercent() {
        assertThrows(IllegalArgumentException.class, () -> validationService.validateDiscountPercent(-1));
        assertThrows(IllegalArgumentException.class, () -> validationService.validateDiscountPercent(101));
        assertDoesNotThrow(() -> validationService.validateDiscountPercent(50));
    }

    @Test
    public void testValidateCheckoutDate() {
        assertThrows(IllegalArgumentException.class, () -> validationService.validateCheckoutDate(LocalDate.now().minusDays(1)));
        assertDoesNotThrow(() -> validationService.validateCheckoutDate(LocalDate.now()));
        assertDoesNotThrow(() -> validationService.validateCheckoutDate(LocalDate.now().plusDays(1)));
    }
}
