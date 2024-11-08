package org.gameloom.connect.game.role.dal;

import org.gameloom.connect.game.role.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleName);
}
