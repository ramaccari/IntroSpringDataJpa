package com.main.presistence.repository;

import com.main.presistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressCrudRepository extends JpaRepository<Address, Long> {

    public List<Address> findByCustomerNameEndingWith(String customerName);

    // Como customer no es una lista no hace falta el fetch del join
    @Query("select a from Address a join customer c where c.name like %?1")
    public List<Address> findCustomerEndingWith(String customerName);

}
