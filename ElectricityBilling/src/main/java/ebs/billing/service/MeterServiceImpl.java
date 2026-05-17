package ebs.billing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ebs.billing.dto.MeterDTO;
import ebs.billing.exception.ResourceNotFoundException;
import ebs.billing.model.Customer;
import ebs.billing.model.Meter;
import ebs.billing.repository.CustomerRepository;
import ebs.billing.repository.MeterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements MeterService {

	private final MeterRepository meterRepository;
	private final CustomerRepository customerRepository;
	private final ModelMapper mapper;
	
	
	@Override
	public MeterDTO createMeter(MeterDTO dto) {
		Customer customer=customerRepository.findById(dto.getCustomerId())
				                           .orElseThrow(() ->new ResourceNotFoundException("Customer not found"));
		Meter meter=mapper.map(dto, Meter.class);
		meter.setCustomer(customer);
		Meter savedMeter=meterRepository.save(meter);
		MeterDTO response=mapper.map(savedMeter, MeterDTO.class);
		response.setCustomerId(customer.getCustomerId());
		return response;
	}
	

	@Override
	public MeterDTO getMeterById(Long id) {
		Meter meter=meterRepository.findById(id)
				               .orElseThrow(() -> new ResourceNotFoundException("Meter Not Found"));
		MeterDTO dto=mapper.map(meter, MeterDTO.class);
		dto.setCustomerId(meter.getCustomer().getCustomerId());
		return dto;
	}

	@Override
	public List<MeterDTO> getAllMeters() {
		return meterRepository.findAll()
				              .stream()
				              .map(meter ->{
				            	  MeterDTO dto=mapper.map(meter, MeterDTO.class);
				            	  dto.setCustomerId(meter.getCustomer().getCustomerId());
				            	  return dto;
				              })
				              .collect(Collectors.toList());
	}

	@Override
	public MeterDTO updateMeter(Long id, MeterDTO dto) {
		Meter meter=meterRepository.findById(id)
				        .orElseThrow(()->new ResourceNotFoundException("Meter not found"));
		
		Customer customer=customerRepository.findById(dto.getCustomerId())
				                 .orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
		
		meter.setMeterNumber(dto.getMeterNumber());
		meter.setMeterType(dto.getMeterType());
		meter.setPhaseCode(dto.getPhaseCode());
		meter.setInstallationAddress(dto.getInstallationAddress());
		meter.setCustomer(customer);
		
		
		Meter updatedMeter=meterRepository.save(meter);
		MeterDTO response=mapper.map(updatedMeter, MeterDTO.class);
		response.setCustomerId(customer.getCustomerId());
		return response;
	}

	@Override
	public void deleteMeter(Long id) {
	 
		Meter meter=meterRepository.findById(id)
				                  .orElseThrow(() ->new ResourceNotFoundException("Meter Not Found"));
		meterRepository.delete(meter);
		
	}

}
