package in.arpansharma.expense_tracker_api.repository;

import in.arpansharma.expense_tracker_api.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    public Page<Expense> findByCategory(String category, Pageable page);

    public Page<Expense> findByNameContaining(String name, Pageable page);

    public Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);

    public Page<Expense> findByUserId(Long user_id, Pageable page);

    public Optional<Expense> findByUserIdAndId(Long user_id, Long id);

}
