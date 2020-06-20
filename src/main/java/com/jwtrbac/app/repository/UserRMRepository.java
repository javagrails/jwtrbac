package com.jwtrbac.app.repository;

import com.jwtrbac.app.domain.User;
import com.jwtrbac.app.domain.UserRM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link UserRM} entity.
 */
@Repository
public interface UserRMRepository extends JpaRepository<UserRM, Long> {

    List<User> findAllByUserId(Long userId);

    List<User> findAllByUserIdAndActiveIsTrue(Long userId);

    List<User> findAllByUserIdAndActiveIsFalse(Long userId);
}
