package com.main;

import com.main.presistence.entity.Customer;
import com.main.presistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class IntroSpringDataJpaApplication {

	@Autowired
	private CustomerCrudRepository customerCrudRepository;

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner testQueryMethodsCommand() {
		return args -> {
			Customer juan = new Customer();
			juan.setName("Juan Lopez");
			juan.setUsername("juan123");
			juan.setPassword("juan123");

			Customer ramon = new Customer();
			ramon.setName("Ramon Hernadez");
			ramon.setUsername("ramon123");
			ramon.setPassword("juan123");

			Customer ramon2 = new Customer();
			ramon2.setName("Ramon Chavez");
			ramon2.setUsername("ramonc123");
			ramon2.setPassword("juanc123");

			Customer luis = new Customer();
			luis.setName("Luis Marquez");
			luis.setUsername("luism123");
			luis.setPassword("juanm123");

			Customer luis2 = new Customer();
			luis2.setName("Luis Cañas");
			luis2.setUsername("luisc123");
			luis2.setPassword("juanc123");

			List<Customer> customerList = List.of(juan, ramon, luis, luis2, ramon2);
			System.out.println("Salvamos una lista de Customer");
			customerCrudRepository.saveAll(customerList);

			System.out.println("Probando queryMethod: findByUsername");
			customerCrudRepository.findByUsername("luism123")
					.ifPresentOrElse(System.out::println, () -> System.out.println("Customer no encontrado"));

			System.out.println("Probando queryMethod: findByNameContaining");
			customerCrudRepository.findByNameContaining("o").forEach(System.out::println);

			System.out.println("Probando queryMethod: findByNameStartingWith");
			customerCrudRepository.findByNameStartingWith("ramon").forEach(System.out::println);

			System.out.println("Probando queryMethod: findByNameEndingWith");
			customerCrudRepository.findByNameEndingWith("ez").forEach(System.out::println);

			System.out.println("Probando queryMethod: findByNameContainingAndIdGreaterThanEqualOrderByIdDesc");
			customerCrudRepository.findByNameContainingAndIdGreaterThanEqualOrderByIdDesc("ez", Long.valueOf(3))
					.forEach(System.out::println);
		};
	}

}
