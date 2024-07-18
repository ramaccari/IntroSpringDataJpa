package com.main;

import com.main.presistence.entity.Customer;
import com.main.presistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class IntroSpringDataJpaApplication {

	@Autowired
	private CustomerCrudRepository customerCrudRepository;

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner testCustomerRepository() {
		return args -> {
			Customer juan = new Customer();
			juan.setName("Juan Lopez");
			juan.setPassword("juan123");
			Customer juanPersisted = customerCrudRepository.save(juan);
			System.out.println("Se guardó la entidad Customer: " + juanPersisted.toString());

			System.out.println("Imprimiendo todos los Customer");
			customerCrudRepository.findAll().forEach(System.out::println);

			System.out.println("Buscando el Customer con id 1");
			customerCrudRepository.findById(Long.valueOf(1)).ifPresent(System.out::println);

			System.out.println("Eliminando al Customer con id 1");
			customerCrudRepository.deleteById(Long.valueOf(1));

			System.out.println("Buscando el Customer con id 1");
			customerCrudRepository.findById(Long.valueOf(1))
					.ifPresentOrElse(System.out::println,
							() -> System.out.println("El customer con id 1 ya no está en la tabla"));
		};
	}

}
