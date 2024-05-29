package com.toolrental.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 * Entity representing a tool.
 */
@Entity
@Data
public class Tool {
    @Id
    private String code;
    private String toolType;
    private String brand;

    public String getCode() {
        return code;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }
}
