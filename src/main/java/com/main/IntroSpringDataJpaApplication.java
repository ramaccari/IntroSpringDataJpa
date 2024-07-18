package com.main;

import com.main.presistence.entity.Customer;
import com.main.presistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
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

			Customer ramon = new Customer();
			ramon.setName("Ramon Hernadez");
			ramon.setPassword("ramon123");
			Customer luis = new Customer();
			luis.setName("Luis Marquez");
			luis.setPassword("luis123");
			List<Customer> customerList = List.of(ramon, luis);
			System.out.println("Salvamos una lista de Customer");
			customerCrudRepository.saveAll(customerList);

			System.out.println("Imprimiendo todos los Customer");
			customerCrudRepository.findAll().forEach(System.out::println);

			System.out.println("Buscando y modificando el cliente 3");
			customerCrudRepository.findById(Long.valueOf(3)).ifPresent(c -> {
				c.setName("Luis Chaves");
				customerCrudRepository.save(c);
			});

			System.out.println("Eliminando al Customer con id 2");
			customerCrudRepository.deleteById(Long.valueOf(2));

			System.out.println("Buscando el Customer con id 2");
			customerCrudRepository.findById(Long.valueOf(2))
					.ifPresentOrElse(System.out::println,
							() -> System.out.println("El customer con id 2 ya no está en la tabla"));
		};
	}

}
