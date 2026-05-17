package ebs.billing.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.billing.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
}
