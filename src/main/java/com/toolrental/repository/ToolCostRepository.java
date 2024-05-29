package com.toolrental.repository;

import com.toolrental.model.ToolCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for ToolCost entities.
 */
@Repository
public interface ToolCostRepository extends JpaRepository<ToolCost, Long> {
    Optional<ToolCost> findByToolType(String toolType);
}
