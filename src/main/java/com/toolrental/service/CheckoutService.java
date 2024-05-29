package com.toolrental.service;

import com.toolrental.model.Tool;
import com.toolrental.model.ToolCost;
import com.toolrental.model.RentalAgreement;
import com.toolrental.util.HolidayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Service class to handle the checkout process and generate rental agreements.
 */
@Service
public class CheckoutService {

    @Autowired
    private ToolService toolService;

    @Autowired
    private ValidationService validationService;

    /**
     * Processes the checkout and generates a rental agreement.
     * @param toolCode The tool code.
     * @param rentalDays The number of rental days.
     * @param discountPercent The discount percentage.
     * @param checkoutDate The checkout date.
     * @return The rental agreement.
     */
    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        validationService.validateInputs(rentalDays, discountPercent, checkoutDate);

        Tool tool = toolService.getToolByCode(toolCode);
        if (tool == null) {
            throw new IllegalArgumentException("Invalid tool code");
        }

        ToolCost toolCost = toolService.getToolCostByType(tool.getToolType());
        if (toolCost == null) {
            throw new IllegalArgumentException("Invalid tool type");
        }

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = calculateChargeDays(toolCost, checkoutDate, dueDate);
        double preDiscountCharge = chargeDays * toolCost.getDailyCharge();
        double discountAmount = preDiscountCharge * discountPercent / 100;
        double finalCharge = preDiscountCharge - discountAmount;

        return new RentalAgreement(toolCode, tool.getToolType(), tool.getBrand(), rentalDays, checkoutDate, dueDate, toolCost.getDailyCharge(), chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    /**
     * Calculates the number of chargeable days for a rental period.
     * @param toolCost The tool cost information.
     * @param checkoutDate The checkout date.
     * @param dueDate The due date.
     * @return The number of chargeable days.
     */
    private int calculateChargeDays(ToolCost toolCost, LocalDate checkoutDate, LocalDate dueDate) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate;

        while (!currentDate.isAfter(dueDate)) {
            boolean isWeekend = currentDate.getDayOfWeek().getValue() >= 6;
            boolean isHoliday = HolidayManager.getInstance().isHoliday(currentDate);

            if ((toolCost.isWeekdayCharge() && !isWeekend && !isHoliday) ||
                (toolCost.isWeekendCharge() && isWeekend) ||
                (toolCost.isHolidayCharge() && isHoliday)) {
                chargeDays++;
            }

            currentDate = currentDate.plusDays(1);
        }

        return chargeDays;
    }
}
