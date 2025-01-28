package com.arpansharma.expense_tracker_api.repository;

import com.arpansharma.expense_tracker_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByemail(String email);

    User findByEmail(String email);
}
