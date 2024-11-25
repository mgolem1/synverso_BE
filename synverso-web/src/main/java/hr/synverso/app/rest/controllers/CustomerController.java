package hr.synverso.app.rest.controllers;


import hr.synverso.app.rest.utils.ResponseMessage;
import hr.synverso.common.dto.CreateCustomerDTO;
import hr.synverso.common.dto.CustomerBasicInfoDTO;
import hr.synverso.common.dto.CustomerDTO;
import hr.synverso.common.exceptions.AppException;
import hr.synverso.repositories.criteria.CustomerSearchCriteria;
import hr.synverso.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping("")
	public ResponseEntity<ResponseMessage<Page<CustomerBasicInfoDTO>>> getUserList(@RequestParam(value = "name", required = false) String name,
																				   @PageableDefault(size = 20, sort = "lastName", direction = Sort.Direction.ASC) Pageable pageable) throws AppException {

		CustomerSearchCriteria criteria = CustomerSearchCriteria.builder()
				.name(name)
				.build();

		return ResponseEntity.ok(new ResponseMessage<>(customerService.getCustomerList(criteria, pageable)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseMessage<CustomerDTO>> getCustomerInfo(@PathVariable("id") Long customerId) throws AppException {
		CustomerDTO customerDTO = customerService.getCustomerInfo(customerId);

		return ResponseEntity.ok(new ResponseMessage<>(customerDTO));
	}

	@PostMapping("")
	public ResponseEntity<ResponseMessage<CustomerDTO>> createCustomer(@Valid @RequestBody CreateCustomerDTO createCustomerDTO) throws AppException {
		return ResponseEntity.ok(new ResponseMessage<>(customerService.createCustomer(createCustomerDTO)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseMessage<CustomerDTO>> updateCustomer(@PathVariable("id") Long customerId, @Valid @RequestBody CustomerDTO customerDTO) throws AppException {
		return ResponseEntity.ok(new ResponseMessage<>(customerService.updateCustomer(customerId, customerDTO)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage<String>> deleteCustomer(@PathVariable(value = "id") Long id) throws AppException {
		customerService.deleteCustomer(id);
		return ResponseEntity.ok(new ResponseMessage<>(""));
	}
}
