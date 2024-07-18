package com.main.presistence.repository;

import com.main.presistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressCrudRepository extends JpaRepository<Address, Long> {

}
