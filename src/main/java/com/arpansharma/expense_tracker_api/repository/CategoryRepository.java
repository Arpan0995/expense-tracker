package com.arpansharma.expense_tracker_api.repository;

import com.arpansharma.expense_tracker_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    public List<Category> findByUserId(Long user_id);

    Optional<Category> findByUserIdAndCategoryId(Long user_id, String category_Id);
}
