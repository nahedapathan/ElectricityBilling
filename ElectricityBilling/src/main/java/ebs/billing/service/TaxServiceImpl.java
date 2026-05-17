package ebs.billing.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ebs.billing.dto.TaxDTO;
import ebs.billing.exception.ResourceNotFoundException;
import ebs.billing.model.Tax;
import ebs.billing.repository.TaxRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {
	
	private final TaxRepository taxRepository;
	private final ModelMapper mapper;
	 @Override
	    public TaxDTO createTax(TaxDTO dto) {

	        Tax tax = mapper.map(dto, Tax.class);

	        Tax savedTax = taxRepository.save(tax);

	        return mapper.map(savedTax, TaxDTO.class);
	    }

	    @Override
	    public TaxDTO getTaxById(Long id) {

	        Tax tax = taxRepository.findById(id)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Tax not found"));

	        return mapper.map(tax, TaxDTO.class);
	    }

	    @Override
	    public List<TaxDTO> getAllTaxes() {

	        return taxRepository.findAll()
	                .stream()
	                .map(tax -> mapper.map(tax, TaxDTO.class))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public TaxDTO updateTax(Long id, TaxDTO dto) {

	        Tax tax = taxRepository.findById(id)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Tax not found"));

	        tax.setTaxName(dto.getTaxName());
	        tax.setTaxPercentage(dto.getTaxPercentage());

	        Tax updatedTax = taxRepository.save(tax);

	        return mapper.map(updatedTax, TaxDTO.class);
	    }

	    @Override
	    public void deleteTax(Long id) {

	        Tax tax = taxRepository.findById(id)
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("Tax not found"));

	        taxRepository.delete(tax);
	    }

}
