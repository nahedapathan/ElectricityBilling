package ebs.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebs.billing.model.Meter;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {

}
