package hr.synverso.managers;

import hr.synverso.common.exceptions.AppError;
import hr.synverso.common.exceptions.AppException;
import hr.synverso.domain.Customer;
import hr.synverso.domain.address.City;
import hr.synverso.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CustomerManagerImpl implements CustomerManager{

	private final CustomerRepository customerRepository;

	@Override
	public Customer findCustomerById(Long id) throws AppException {
		Optional<Customer> customerOptional = customerRepository.findById(id);

		if (!customerOptional.isPresent()) {
			log.warn("Failed getting customer: customer {} doesn't exist!", id);
			throw new AppException(AppError.NON_EXISTING_CUSTOMER);
		}

		return customerOptional.get();
	}
}
