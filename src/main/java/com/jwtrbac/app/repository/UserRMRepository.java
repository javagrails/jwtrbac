package com.jwtrbac.app.repository;

import com.jwtrbac.app.domain.UserRM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link UserRM} entity.
 */
@Repository
public interface UserRMRepository extends JpaRepository<UserRM, Long>, JpaSpecificationExecutor<UserRM> {

    List<UserRM> findAllByUserId(Long userId);

    List<UserRM> findAllByUserIdAndActiveIsTrue(Long userId);

    List<UserRM> findAllByUserIdAndActiveIsFalse(Long userId);
}
