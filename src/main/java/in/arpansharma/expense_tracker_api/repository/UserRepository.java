package in.arpansharma.expense_tracker_api.repository;

import in.arpansharma.expense_tracker_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByemail(String email);

    User findByEmail(String email);
}
