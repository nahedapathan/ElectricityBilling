package ebs.billing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ebs.billing.dto.CustomerDTO;
import ebs.billing.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO dto)
	{
		return service.createCustomer(dto);
	}
	
	@GetMapping("/{id}")
	public CustomerDTO getCustomerById(@PathVariable Long id)
	{
		return service.getCustomerById(id);
	}
	
	@GetMapping
	public List<CustomerDTO> getAllCustomers()
	{
		return service.getAllCustomers();
	}
	
	@PutMapping("/{id}")
	public CustomerDTO updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO dto)
	{
		return service.updateCustomer(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable Long id)
	{
		service.deleteCustomer(id);
		return "Customer deleted successfully";
	}
	
}
