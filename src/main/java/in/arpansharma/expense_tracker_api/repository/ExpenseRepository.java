package in.arpansharma.expense_tracker_api.repository;

import in.arpansharma.expense_tracker_api.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    public Page<Expense> findByCategory(String category, Pageable page);
}
