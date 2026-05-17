package ebs.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.billing.model.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {

}
