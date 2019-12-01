package com.konradzadroga.drivingschool.rest_api.user;
import com.konradzadroga.drivingschool.rest_api.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findAllByRolesName(RoleName roleName);
}
