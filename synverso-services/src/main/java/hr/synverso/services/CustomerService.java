package hr.synverso.services;

import hr.synverso.common.dto.CreateCustomerDTO;
import hr.synverso.common.dto.CustomerBasicInfoDTO;
import hr.synverso.common.dto.CustomerDTO;
import hr.synverso.common.exceptions.AppException;
import hr.synverso.repositories.criteria.CustomerSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

	CustomerDTO getCustomerInfo(Long id) throws AppException;

	Page<CustomerBasicInfoDTO> getCustomerList(CustomerSearchCriteria criteria, Pageable pageable) throws AppException;

	CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) throws AppException;

	CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) throws AppException;

	void deleteCustomer(Long customerId) throws AppException;
}
