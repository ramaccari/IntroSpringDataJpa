package com.main.presistence.repository;

import com.main.presistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerCrudRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    List<Customer> findByNameContaining(String name);

    List<Customer> findByNameStartingWith(String name);

    List<Customer> findByNameEndingWith(String name);

    List<Customer> findByNameContainingAndIdGreaterThanEqualOrderByIdDesc(String name, Long id);

    @Query("select c from Customer c where c.name like %?1% and c.id >= ?2 order by c.id desc")
    List<Customer> getByNameAndByIdGreaterThan(String name, Long id);

    @Query(value = "select * from clientes c where c.nombre like %?1% and c.id >= ?2 order by c.id desc", nativeQuery = true)
    List<Customer> getByNameAndByIdGreaterThanNative(String name, Long id);

    List<Customer> findByAddressesCountry(String country);

    @Query("select c from Customer c join fetch addresses a where a.country = ?1")
    List<Customer> findCustomersFrom(String country);

}
