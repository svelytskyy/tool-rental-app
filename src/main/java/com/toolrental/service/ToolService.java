package com.toolrental.service;

import com.toolrental.model.Tool;
import com.toolrental.model.ToolCost;
import com.toolrental.repository.ToolCostRepository;
import com.toolrental.repository.ToolRepository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to handle tool-related operations.
 */
@Service
public class ToolService {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private ToolRepository toolRepository;
    @Autowired
    private ToolCostRepository toolCostRepository;
    

    /**
     * Retrieves a tool by its code.
     * @param toolCode The tool code.
     * @return The tool.
     */
    public Tool getToolByCode(String toolCode) {
        return cacheService.getToolByCode(toolCode);
    }

    /**
     * Retrieves the cost information for a tool type.
     * @param toolType The tool type.
     * @return The tool cost.
     */
    public ToolCost getToolCostByType(String toolType) {
        return cacheService.getToolCostByType(toolType);
    }
    
    @PostConstruct
    public void init() {
        List<Tool> tools = toolRepository.findAll();
        System.out.println("Tools: " + tools);

        List<ToolCost> toolCosts = toolCostRepository.findAll();
        System.out.println("Tool Costs: " + toolCosts);
    }
    
}
