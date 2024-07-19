package com.main;

import com.main.presistence.entity.Address;
import com.main.presistence.entity.Customer;
import com.main.presistence.repository.AddressCrudRepository;
import com.main.presistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class IntroSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner testCustomerCrudRepositoryCommand(CustomerCrudRepository repository) {
		return args -> {
			Customer juan = new Customer();
			juan.setName("Juan Lopez");
			juan.setUsername("juan123");
			juan.setPassword("juan123");
			Address juanAddress1 = new Address();
			juanAddress1.setCountry("Honduras");
			juanAddress1.setAddress("Calle 123, Calle Principal Col. Y, San Salvador");
			Address juanAddress2 = new Address();
			juanAddress2.setCountry("El Salvador");
			juanAddress2.setAddress("Calle 654, Calle Principal Col. ABC, Tegucigalpa");
			juan.addAddress(juanAddress1);
			juan.addAddress(juanAddress2);

			Customer ramon = new Customer();
			ramon.setName("Ramon Hernadez");
			ramon.setUsername("ramon123");
			ramon.setPassword("juan123");
			Address ramonAddress = new Address();
			ramonAddress.setCountry("El Salvador");
			ramonAddress.setAddress("Calle 456, Calle Principal Col. X, San Salvador");
			ramon.addAddress(ramonAddress);

			Customer luis = new Customer();
			luis.setName("Luis Marquez");
			luis.setUsername("luism123");
			luis.setPassword("juanm123");
			Address luisAddress = new Address();
			luisAddress.setCountry("El Salvador");
			luisAddress.setAddress("Calle 789, Calle Principal Col. Z, San Salvador");
			luis.addAddress(luisAddress);

			List<Customer> customerList = List.of(juan, ramon, luis);
			System.out.println("Salvamos una lista de Customer");
			repository.saveAll(customerList);

			System.out.println("Probando queryMethod: findByUsername");
			repository.findByUsername("luism123")
					.ifPresentOrElse(System.out::println, () -> System.out.println("Customer no encontrado"));

			System.out.println("Probando queryMethod: findByNameContaining");
			repository.findByNameContaining("o").forEach(System.out::println);

			System.out.println("Probando queryMethod: findByNameStartingWith");
			repository.findByNameStartingWith("ramon").forEach(System.out::println);

			System.out.println("Probando queryMethod: findByNameEndingWith");
			repository.findByNameEndingWith("ez").forEach(System.out::println);

			System.out.println("Probando queryMethod: findByNameContainingAndIdGreaterThanEqualOrderByIdDesc");
			repository.findByNameContainingAndIdGreaterThanEqualOrderByIdDesc("ez", Long.valueOf(3))
					.forEach(System.out::println);

			System.out.println("Probando @query con JPQL: getByNameAndByIdGreaterThan");
			repository.getByNameAndByIdGreaterThan("ez", Long.valueOf(3))
					.forEach(System.out::println);

			System.out.println("Probando @query nativo con JPQL: getByNameAndByIdGreaterThanNative");
			repository.getByNameAndByIdGreaterThanNative("ez", Long.valueOf(3))
					.forEach(System.out::println);

			System.out.println("====> Probando bideccionalidad desde Customer");
			repository.findAll().forEach(c -> System.out.println("Cliente: "
					+ c.getName() + ", cantidad de direciones: "
					+ c.getAddresses().size()));
		};
	}

	@Bean
	public CommandLineRunner testAddressCrudRepositoryCommand(AddressCrudRepository repository) {
		return args -> {
			System.out.println("====> Probando bideccionalidad desde Address");
			repository.findAll().forEach(a -> System.out.println(a +  " cliente: " + a.getCustomer().getName()));
		};
	}

}
