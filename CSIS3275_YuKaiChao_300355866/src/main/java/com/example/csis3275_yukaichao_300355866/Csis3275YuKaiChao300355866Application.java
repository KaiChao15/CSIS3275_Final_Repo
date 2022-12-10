package com.example.csis3275_yukaichao_300355866;

import com.example.csis3275_yukaichao_300355866.entities.Customer;
import com.example.csis3275_yukaichao_300355866.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Csis3275YuKaiChao300355866Application {

    public static void main(String[] args) {
        SpringApplication.run(Csis3275YuKaiChao300355866Application.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
//		return args -> {
//            customerRepository.save(new Customer(null, "Jam", "5A", new Date()));
//            customerRepository.save(new Customer(null, "John", "5C", new Date()));
//
//            customerRepository.findAll().forEach(p->{
//				System.out.println(p.getName());
//			});
//		};
//	}
}
