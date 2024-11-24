package hr.synverso.services;

import hr.synverso.AppWebContextAwareUT;
import hr.synverso.common.dto.CreateCustomerDTO;
import hr.synverso.common.exceptions.AppError;
import hr.synverso.common.exceptions.AppException;
import hr.synverso.common.mappers.CustomerMapper;
import hr.synverso.domain.Customer;
import hr.synverso.domain.codebook.Gender;
import hr.synverso.managers.CityManager;
import hr.synverso.managers.CustomerManager;
import hr.synverso.repositories.CustomerRepository;
import hr.synverso.repositories.criteria.CustomerSearchCriteria;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest extends AppWebContextAwareUT {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CityManager cityManager;

	@Mock
	private CustomerManager customerManager;

	@Mock
	private CustomerMapper customerMapper;

	@Test
	void getCustomerInfo() throws AppException {

		when(customerManager.findCustomerById(eq(1L))).thenReturn(getCustomerData());
		assertDoesNotThrow(() -> customerService.getCustomerInfo(1L));
	}

	@Test
	void getCustomerList() {

		when(customerRepository.findCustomersBySearchCriteria(
				any(CustomerSearchCriteria.class),
				any(Pageable.class)))
				.thenReturn(new PageImpl<>(List.of(getCustomerData()), Pageable.unpaged(), 10));
		assertDoesNotThrow(() -> customerService.getCustomerList(any(),any()));
	}

	@Test
	void createCustomer() {

		when(customerRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);
		assertDoesNotThrow(() -> customerService.createCustomer(getCreateCustomerDTO()));
	}

	@Test
	void createCustomer_Email_BadRequest() {
		CreateCustomerDTO customerDTO=getCreateCustomerDTO();
		customerDTO.setEmail(null);
		assertException(AppError.BAD_REQUEST, () -> customerService.createCustomer(customerDTO));

	}

	@Test
	void createCustomer_Username_BadRequest() {
		CreateCustomerDTO customerDTO=getCreateCustomerDTO();
		customerDTO.setUsername(null);
		assertException(AppError.BAD_REQUEST, () -> customerService.createCustomer(customerDTO));

	}


	private Customer getCustomerData() {
		return Customer.builder()
				.id(1L)
				.firstName("Ana")
				.lastName("Anic")
				.email("aanic@gmail.com")
				.username("aanic")
				.gender(Gender.F)
				.dateOfBirth(LocalDate.now())
				.build();

	}

	private CreateCustomerDTO getCreateCustomerDTO(){
		CreateCustomerDTO customerDTO = new CreateCustomerDTO();
		customerDTO.setEmail("aanic@gmail.com");
		customerDTO.setUsername("aanic");
		customerDTO.setFirstName("Ana");
		customerDTO.setLastName("Anic");
		return  customerDTO;
	}
}