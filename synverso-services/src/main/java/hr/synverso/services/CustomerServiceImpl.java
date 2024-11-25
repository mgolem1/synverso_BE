package hr.synverso.services;

import hr.synverso.common.dto.CreateCustomerDTO;
import hr.synverso.common.dto.CustomerBasicInfoDTO;
import hr.synverso.common.dto.CustomerDTO;
import hr.synverso.common.dto.address.AddressDTO;
import hr.synverso.common.exceptions.AppError;
import hr.synverso.common.exceptions.AppException;
import hr.synverso.common.mappers.CustomerMapper;
import hr.synverso.domain.Customer;
import hr.synverso.domain.address.Address;
import hr.synverso.domain.address.City;
import hr.synverso.domain.codebook.Gender;
import hr.synverso.managers.CityManager;
import hr.synverso.managers.CustomerManager;
import hr.synverso.repositories.CustomerRepository;
import hr.synverso.repositories.criteria.CustomerSearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service(value = "customerService")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	private final CityManager cityManager;

	private final CustomerManager customerManager;

	private final CustomerMapper customerMapper;

	@Override
	public CustomerDTO getCustomerInfo(Long id) throws AppException {

		Customer customer = customerManager.findCustomerById(id);
		return customerMapper.customerToCustomerDTO(customer);
	}

	@Override
	public Page<CustomerBasicInfoDTO> getCustomerList(CustomerSearchCriteria criteria, Pageable pageable) {
		Page<Customer> customers = customerRepository.findCustomersBySearchCriteria(criteria, pageable);

		return customers.map(customerMapper::customerToCustomerBasicInfoDTO);
	}

	@Override
	public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) throws AppException {

		Gender gender = createCustomerDTO.getGender() != null && !createCustomerDTO.getGender().isEmpty() ? Gender.valueOf(createCustomerDTO.getGender()) : null;

		if(createCustomerDTO.getEmail()==null){
			log.warn("Email can not be null!");
			throw new AppException(AppError.BAD_REQUEST, "Email can not be null!");
		}
		if(createCustomerDTO.getUsername()==null){
			log.warn("Username can not be null!");
			throw new AppException(AppError.BAD_REQUEST, "Username can not be null!");
		}

		Customer customer = Customer.builder()
				.firstName(createCustomerDTO.getFirstName())
				.lastName(createCustomerDTO.getLastName())
				.identificationNumber(createCustomerDTO.getIdentificationNumber())
				.dateOfBirth(createCustomerDTO.getDateOfBirth() != null && !createCustomerDTO.getDateOfBirth().isEmpty() ? LocalDate.parse(createCustomerDTO.getDateOfBirth()) : null)
				.gender(gender)
				.email(createCustomerDTO.getEmail())
				.username(createCustomerDTO.getUsername())
				.build();

		if (createCustomerDTO.getAddress() != null) {
			Address address = new Address();
			AddressDTO addressDTO = createCustomerDTO.getAddress();

			address.setCountry(addressDTO.getCountry());
			address.setZip(addressDTO.getZip());
			address.setCountry(addressDTO.getCountry());
			address.setStreetWithHomeNumber(addressDTO.getStreetWithHomeNumber());

			if (addressDTO.getCity() == null) {
				log.warn("City can not be null!");
				throw new AppException(AppError.BAD_REQUEST, "City can not be null!");
			}

			City city = cityManager.getCityById(Long.valueOf(addressDTO.getCity().getId()));
			address.setCity(city);
			customer.setAddress(address);
		}

		customerRepository.save(customer);
		return customerMapper.customerToCustomerDTO(customer);
	}

	@Override
	public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) throws AppException {

		Customer customer = customerManager.findCustomerById(customerId);

		Gender gender = customerDTO.getGender() != null && !customerDTO.getGender().isEmpty() ? Gender.valueOf(customerDTO.getGender()) : null;

		customer.setGender(gender);

		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setIdentificationNumber(customerDTO.getIdentificationNumber());
		customer.setDateOfBirth(customerDTO.getDateOfBirth() != null && !customerDTO.getDateOfBirth().isEmpty() ? LocalDate.parse(customerDTO.getDateOfBirth()) : null);


		if (customerDTO.getAddress() != null) {
			Address address = new Address();
			AddressDTO addressDTO = customerDTO.getAddress();

			address.setCountry(addressDTO.getCountry());
			address.setZip(addressDTO.getZip());
			address.setCountry(addressDTO.getCountry());
			address.setStreetWithHomeNumber(addressDTO.getStreetWithHomeNumber());

			if (addressDTO.getCity() == null) {
				log.warn("City must not be null!");
				throw new AppException(AppError.BAD_REQUEST, "City can not be null!");
			}

			City city = cityManager.getCityById(Long.valueOf(addressDTO.getCity().getId()));
			address.setCity(city);
			customer.setAddress(address);
		} else {
			customer.setAddress(null);
		}

		customerRepository.save(customer);
		return customerMapper.customerToCustomerDTO(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) throws AppException {
		Customer customer = customerManager.findCustomerById(customerId);
		customerRepository.delete(customer);
	}
}
