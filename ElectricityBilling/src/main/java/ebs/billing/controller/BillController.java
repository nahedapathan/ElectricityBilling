package ebs.billing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ebs.billing.dto.BillDTO;
import ebs.billing.service.BillService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class BillController {

	private final BillService billService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BillDTO createBill(@RequestBody BillDTO dto)
	{
		return billService.createBill(dto);
	}
	
	@GetMapping("/{id}")
	public BillDTO getBillById(@PathVariable Long id)
	{
		return billService.getBillById(id);
	}
	
	@GetMapping
	public List<BillDTO> getAllBills()
	{
		return billService.getAllBills();
	}
	
	@PutMapping("/{id}")
	public BillDTO updateBill(@PathVariable Long id, @RequestBody BillDTO dto)
	{
		return billService.updateBill(id, dto);
	
	}
	
	public String deleteBill(@PathVariable Long id)
	{
		billService.deleteBill(id);
		return "Bill Deleted Successfully";
	}
}
