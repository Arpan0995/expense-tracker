package in.arpansharma.expense_tracker_api.repository;

import in.arpansharma.expense_tracker_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
