package hr.synverso.repositories;

import hr.synverso.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerSearchRepository {
}
