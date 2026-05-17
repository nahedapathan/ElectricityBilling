package ebs.billing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ebs.billing.dto.BillDTO;
import ebs.billing.exception.ResourceNotFoundException;
import ebs.billing.model.Bill;
import ebs.billing.model.Customer;
import ebs.billing.model.Tax;
import ebs.billing.repository.BillRepository;
import ebs.billing.repository.CustomerRepository;
import ebs.billing.repository.TaxRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
	
	
	private final BillRepository billRepository;
	private final CustomerRepository customerRepository;
	private final TaxRepository taxRepository;
	private final ModelMapper mapper;
	
	
	@Override
	public BillDTO createBill(BillDTO dto) {
		Customer customer=customerRepository.findById(dto.getCustomerId())
				        .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
		Tax tax=taxRepository.findById(dto.getTaxId())
				          .orElseThrow(()-> new ResourceNotFoundException("Tax not found"));
		
		Bill bill =mapper.map(dto, Bill.class);
		bill.setCustomer(customer);
		bill.setTax(tax);
		
		Bill savedBill=billRepository.save(bill);
		BillDTO response=mapper.map(savedBill, BillDTO.class);
		
		response.setCustomerId(customer.getCustomerId());
		response.setTaxId(tax.getTaxId());
		return response;
		
	}

	@Override
	public BillDTO getBillById(Long id) {
		
		Bill bill=billRepository.findById(id)
				           .orElseThrow(()->new ResourceNotFoundException("Bill not found"));
		BillDTO dto=mapper.map(bill, BillDTO.class);
		dto.setCustomerId(bill.getCustomer().getCustomerId());
		dto.setTaxId(bill.getTax().getTaxId());
		
		return dto;
	}

	@Override
	public List<BillDTO> getAllBills() {
		return billRepository.findAll()
				             .stream()
				             .map(bill ->{
				            	 BillDTO dto=mapper.map(bill, BillDTO.class);
				            	 dto.setCustomerId(bill.getCustomer().getCustomerId());
				            	 dto.setTaxId(bill.getTax().getTaxId());
				            	 return dto;
				             })
				             .collect(Collectors.toList());
	}

	@Override
	public BillDTO updateBill(Long id, BillDTO dto) {
		Bill bill =billRepository.findById(id)
				                  .orElseThrow(()-> new ResourceNotFoundException("Bill not found"));
		
		Customer customer=customerRepository.findById(dto.getCustomerId())
				          .orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
		Tax tax=taxRepository.findById(dto.getTaxId())
				            .orElseThrow(()-> new ResourceNotFoundException("Tax not found"));
		bill.setUnitsConsumed(dto.getUnitsConsumed());
		bill.setAmount(dto.getAmount());
		bill.setBillDate(dto.getBillDate());
		bill.setBillStatus(dto.getBillStatus());
		bill.setCustomer(customer);
		bill.setTax(tax);
		
		Bill updatedBill=billRepository.save(bill);
		BillDTO response=mapper.map(updatedBill, BillDTO.class);
		response.setCustomerId(customer.getCustomerId());
		response.setTaxId(tax.getTaxId());
		
		return response;
	}

	@Override
	public void deleteBill(Long id) {
	
		Bill bill=billRepository.findById(id)
				        .orElseThrow(()-> new ResourceNotFoundException("Bill not found"));
		billRepository.delete(bill);
	}

}
