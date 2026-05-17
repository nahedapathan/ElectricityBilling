package ebs.billing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ebs.billing.dto.CustomerDTO;
import ebs.billing.exception.ResourceNotFoundException;
import ebs.billing.model.Customer;
import ebs.billing.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

	private final CustomerRepository repository;
	private final ModelMapper mapper;
	
	@Override
	public CustomerDTO createCustomer(CustomerDTO dto) {
		Customer customer=mapper.map(dto, Customer.class);
		Customer saved=repository.save(customer);
		return  mapper.map(saved, CustomerDTO.class);
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		Customer customer=repository.findById(id)
				                .orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
		return mapper.map(customer, CustomerDTO.class);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return repository.findAll()
				         .stream()
				         .map(c -> mapper.map(c, CustomerDTO.class))
				         .collect(Collectors.toList());
	}
	

	@Override
	public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
		 Customer customer=repository.findById(id)
				                     .orElseThrow(() -> new ResourceNotFoundException("Customer Not found "));
		 customer.setCustomerName(dto.getCustomerName());
		 customer.setEmail(dto.getEmail());
		 customer.setPhone(dto.getPhone());
		 customer.setAddress(dto.getAddress());
		 
		 Customer updated=repository.save(customer);
		 return mapper.map(updated, CustomerDTO.class);
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customer =repository.findById(id)
				                      .orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
		repository.delete(customer);
		
	}

}
