package hr.synverso.repositories;

import hr.synverso.domain.Customer;
import hr.synverso.repositories.criteria.CustomerSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerSearchRepository {

	Page<Customer> findCustomersBySearchCriteria(CustomerSearchCriteria criteria, Pageable pageable);
}
