package com.main.presistence.repository;

import com.main.presistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerCrudRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    List<Customer> findByNameContaining(String name);

    List<Customer> findByNameStartingWith(String name);

    List<Customer> findByNameEndingWith(String name);

    List<Customer> findByNameContainingAndIdGreaterThanEqualOrderByIdDesc(String name, Long id);

}
