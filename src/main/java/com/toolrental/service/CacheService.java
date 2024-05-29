package com.toolrental.service;

import com.toolrental.model.Tool;
import com.toolrental.model.ToolCost;
import com.toolrental.repository.ToolRepository;
import com.toolrental.repository.ToolCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class to cache tool and tool cost data.
 */
@Service
public class CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ToolCostRepository toolCostRepository;

    private Map<String, Tool> toolCache = new HashMap<>();
    private Map<String, ToolCost> toolCostCache = new HashMap<>();

    /**
     * Loads data from the database into the cache.
     */
    @PostConstruct
    public void loadCache() {
        logger.info("Loading tools into cache");
        toolRepository.findAll().forEach(tool -> toolCache.put(tool.getCode(), tool));
        logger.info("tool cache : " + toolCache.toString());

        logger.info("Loading tool costs into cache");
        toolCostRepository.findAll().forEach(toolCost -> toolCostCache.put(toolCost.getToolType(), toolCost));
        logger.info("tool cost cache : " + toolCostCache.toString());
    }

    /**
     * Retrieves a tool from the cache.
     * @param toolCode The tool code.
     * @return The tool.
     */
    public Tool getToolByCode(String toolCode) {
        return toolCache.get(toolCode);
    }

    /**
     * Retrieves a tool cost from the cache.
     * @param toolType The tool type.
     * @return The tool cost.
     */
    public ToolCost getToolCostByType(String toolType) {
        return toolCostCache.get(toolType);
    }
}
