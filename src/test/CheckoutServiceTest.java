package com.toolrental.service;

import com.toolrental.model.RentalAgreement;
import com.toolrental.model.Tool;
import com.toolrental.model.ToolCost;
import com.toolrental.util.HolidayManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ToolRentalApplication.class)

class CheckoutServiceTest {

    @Mock
    private ToolService toolService;

    @InjectMocks
    private CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckout() {
        Tool mockTool = new Tool("LADW", "Ladder", "Werner");
        ToolCost mockToolCost = new ToolCost("Ladder", 1.99, true, true, false);
        when(toolService.getToolByCode(anyString())).thenReturn(mockTool);
        when(toolService.getToolCostByType(anyString())).thenReturn(mockToolCost);

        LocalDate checkoutDate = LocalDate.of(2023, 6, 1);
        RentalAgreement agreement = checkoutService.checkout("LADW", 5, 10, checkoutDate);

        assertEquals("LADW", agreement.getToolCode());
        assertEquals("Ladder", agreement.getToolType());
        assertEquals("Werner", agreement.getBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals(checkoutDate, agreement.getCheckoutDate());
        assertEquals(checkoutDate.plusDays(5), agreement.getDueDate());
        assertEquals(1.99, agreement.getDailyCharge());
        assertEquals(4, agreement.getChargeDays());
        assertEquals(7.96, agreement.getPreDiscountCharge());
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(0.80, agreement.getDiscountAmount());
        assertEquals(7.16, agreement.getFinalCharge());
    }
}
