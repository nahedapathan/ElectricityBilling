package ebs.billing.service;

import java.util.List;

import ebs.billing.dto.TaxDTO;

public interface TaxService {

	TaxDTO createTax(TaxDTO dto);
	TaxDTO getTaxById(Long id);
	List<TaxDTO> getAllTaxes();
	TaxDTO updateTax(Long id,TaxDTO dto);
	void deleteTax(Long id);
}
