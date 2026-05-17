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

import ebs.billing.dto.MeterDTO;
import ebs.billing.service.MeterService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/meters")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MeterController {

	private final MeterService meterService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MeterDTO createMeter(@RequestBody MeterDTO dto)
	{
		return meterService.createMeter(dto);
		
	}
	
	@GetMapping("/{id}")
	public MeterDTO getMeterById(@PathVariable Long id)
	{
		return meterService.getMeterById(id);
	}
	
	@GetMapping
	public List<MeterDTO> getAllMeters()
	{
		return meterService.getAllMeters();
	}
	
	@PutMapping("/{id}")
	public MeterDTO updateMeter(@PathVariable Long id,@RequestBody MeterDTO dto)
	{
		return meterService.updateMeter(id, dto);
	}
	
	public String deleteMeter(@PathVariable Long id)
	{
		meterService.deleteMeter(id);
		return "Meter Deleted Successfully";
	}
	
}
