package com.main;

import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@SpringBootApplication
public class IntroSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringDataJpaApplication.class, args);
	}


	@Bean
	public CommandLineRunner validateDSCommand(DataSource ds) {
		return args -> {
			System.out.println("Probando conexión y DS");
			Connection con = ds.getConnection();
			PreparedStatement pstm = con.prepareStatement("select * from characters");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String mensaje = rs.getString("id") + " - " + rs.getString("name");
				System.out.println(mensaje);
			}
			rs.close();
			pstm.close();
			con.close();
		};
	}

	@Bean
	public CommandLineRunner validateEntityManagerCommand(EntityManager em) {
		return args -> {
			System.out.println("Probando conexión y EntityManager");
			List<Object[]> result = em.createNativeQuery("select * from characters").getResultList();
			result.forEach(e -> {
				String mensaje = e[0] + " - " + e[1];
				System.out.println(mensaje);
			});
		};
	}

}
