package bj.logikteck.springbootjspangular.repositories;

import bj.logikteck.springbootjspangular.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}
