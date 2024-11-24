package hr.synverso.managers;

import hr.synverso.common.exceptions.AppException;
import hr.synverso.domain.Customer;

public interface CustomerManager {

	Customer findCustomerById(Long id) throws AppException;
}
