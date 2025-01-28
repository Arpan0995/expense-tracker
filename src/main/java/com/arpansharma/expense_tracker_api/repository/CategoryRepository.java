package com.arpansharma.expense_tracker_api.repository;

import com.arpansharma.expense_tracker_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
