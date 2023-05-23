package kz.galymbay.diploma.repository;

import kz.galymbay.diploma.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
//    Role findByName(String role);

    Role findByRoleName(String roleName);
}
