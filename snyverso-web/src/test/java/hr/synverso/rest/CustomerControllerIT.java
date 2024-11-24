package hr.synverso.rest;

import hr.synverso.CustomerWebContextAwareIT;
import hr.synverso.app.rest.utils.ResponseMessage;
import hr.synverso.common.dto.CreateCustomerDTO;
import hr.synverso.common.dto.CustomerBasicInfoDTO;
import hr.synverso.common.dto.CustomerDTO;
import hr.synverso.common.exceptions.AppError;
import hr.synverso.common.exceptions.AppException;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerControllerIT extends CustomerWebContextAwareIT {


	@Test
	public void searchUserByFirstAndLastName() throws AppException {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/customers?name=Mate"),
				HttpMethod.GET,
				entity,
				String.class);

		List<CustomerBasicInfoDTO> customerBasicInfoDTOS = getPageFromBody(response, CustomerBasicInfoDTO.class);

		assertEquals(1, customerBasicInfoDTOS.size());

		assertEquals("Mate", customerBasicInfoDTOS.get(0).getFirstName());
		assertEquals("Matić", customerBasicInfoDTOS.get(0).getLastName());

		response = restTemplate.exchange(
				createURLWithPort("/api/customers?name=Pero P"),
				HttpMethod.GET,
				entity,
				String.class);

		customerBasicInfoDTOS = getPageFromBody(response, CustomerBasicInfoDTO.class);

		assertEquals(2, customerBasicInfoDTOS.size());

		assertEquals("Pero", customerBasicInfoDTOS.get(0).getFirstName());
		assertEquals("Perić", customerBasicInfoDTOS.get(0).getLastName());

		ResponseEntity<String> lastNameFirstNameResponse = restTemplate.exchange(
				createURLWithPort("/api/customers?name=Perić P"),
				HttpMethod.GET,
				entity,
				String.class);

		customerBasicInfoDTOS = getPageFromBody(lastNameFirstNameResponse, CustomerBasicInfoDTO.class);

		assertEquals(2, customerBasicInfoDTOS.size());

		assertEquals("Pero", customerBasicInfoDTOS.get(0).getFirstName());
		assertEquals("Perić", customerBasicInfoDTOS.get(0).getLastName());
	}

	@Test
	@DirtiesContext
	public void findCustomerById() throws AppException {

		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/customers/1"),
				HttpMethod.GET,
				entity,
				String.class);

		CustomerDTO customerDTO = getDTOObjectFromBody(response, CustomerDTO.class);

		assertNotNull(customerDTO.getId());
		assertEquals("1", customerDTO.getId());
		assertEquals("Mate", customerDTO.getFirstName());
		assertEquals("Matić", customerDTO.getLastName());
		assertEquals("Cvite Fiskovića 3", customerDTO.getAddress().getStreetWithHomeNumber());
		assertEquals("21000", customerDTO.getAddress().getZip());
		assertEquals("Split", customerDTO.getAddress().getCity().getName());

	}

	@Test
	@DirtiesContext
	public void findCustomerByNonExistingId() {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<ResponseMessage> response = restTemplate.exchange(
				createURLWithPort("/api/customers/0"),
				HttpMethod.GET,
				entity,
				ResponseMessage.class);

		assertEquals(AppError.NON_EXISTING_CUSTOMER.name(), response.getBody().getErrorCode());
	}

	@Test
	@DirtiesContext
	public void createCustomer() throws AppException {
		CreateCustomerDTO customerDTO = new CreateCustomerDTO();
		customerDTO.setEmail("aanic@gmail.com");
		customerDTO.setUsername("aanic");
		customerDTO.setFirstName("Ana");
		customerDTO.setLastName("Anic");
		HttpEntity<CreateCustomerDTO> entity = new HttpEntity<>(customerDTO, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/customers"),
				HttpMethod.POST,
				entity,
				String.class);

		CustomerDTO result = getDTOObjectFromBody(response, CustomerDTO.class);

		assertEquals("Ana", result.getFirstName());
		assertEquals("Anic", result.getLastName());

	}

	@Test
	@DirtiesContext
	public void updateCustomer() throws AppException {
		CustomerDTO user = CustomerDTO.builder()
				.firstName("Ana")
				.lastName("Anic")
				.build();
		HttpEntity<CustomerDTO> entity = new HttpEntity<>(user, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/customers/1"),
				HttpMethod.PUT,
				entity,
				String.class);

		CustomerDTO result = getDTOObjectFromBody(response, CustomerDTO.class);
		assertEquals("Ana", result.getFirstName());
	}

	@Test
	@DirtiesContext
	public void deleteCustomer() {

		HttpEntity<CustomerDTO> entity = new HttpEntity<>(null, headers);
		ResponseEntity<ResponseMessage> response = restTemplate.exchange(
				createURLWithPort("/api/customers/4"),
				HttpMethod.DELETE,
				entity,
				ResponseMessage.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@DirtiesContext
	public void deleteNonExistingCustomer() {

		HttpEntity<CustomerDTO> entity = new HttpEntity<>(null, headers);

		ResponseEntity<ResponseMessage> response = restTemplate.exchange(
				createURLWithPort("/api/customers/0"),
				HttpMethod.DELETE,
				entity,
				ResponseMessage.class);
		assertEquals(AppError.NON_EXISTING_CUSTOMER.name(), response.getBody().getErrorCode());
	}
}
