package com.toolrental.service;

import com.toolrental.model.Tool;
import com.toolrental.model.ToolCost;
import com.toolrental.repository.ToolRepository;
import com.toolrental.repository.ToolCostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ToolRentalApplication.class)
class ToolServiceTest {

    @Mock
    private ToolRepository toolRepository;

    @Mock
    private ToolCostRepository toolCostRepository;

    @InjectMocks
    private ToolService toolService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetToolByCode() {
        Tool mockTool = new Tool("LADW", "Ladder", "Werner");
        when(toolRepository.findById(anyString())).thenReturn(Optional.of(mockTool));

        Tool tool = toolService.getToolByCode("LADW");
        assertNotNull(tool);
        assertEquals("LADW", tool.getToolCode());
        assertEquals("Ladder", tool.getToolType());
        assertEquals("Werner", tool.getBrand());
    }

    @Test
    void testGetToolCostByType() {
        ToolCost mockToolCost = new ToolCost("Ladder", 1.99, true, true, false);
        when(toolCostRepository.findById(anyString())).thenReturn(Optional.of(mockToolCost));

        ToolCost toolCost = toolService.getToolCostByType("Ladder");
        assertNotNull(toolCost);
        assertEquals("Ladder", toolCost.getToolType());
        assertEquals(1.99, toolCost.getDailyCharge());
    }
}
