package ebs.billing.service;

import java.util.List;

import ebs.billing.dto.BillDTO;

public interface BillService {

	BillDTO createBill(BillDTO dto);
	BillDTO getBillById(Long id);
	List<BillDTO> getAllBills();
	BillDTO updateBill(Long id,BillDTO dto);
	void deleteBill(Long id);
	
}
