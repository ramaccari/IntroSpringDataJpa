package com.main.presistence.repository;

import com.main.presistence.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerCrudRepository extends CrudRepository<Customer, Long> {

}
