package br.com.api.repository;

import br.com.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByName(String userName);

    Customer findByEmail(String email);
}
