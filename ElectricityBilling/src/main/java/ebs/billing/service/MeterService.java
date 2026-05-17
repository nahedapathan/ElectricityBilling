package ebs.billing.service;

import java.util.List;

import ebs.billing.dto.MeterDTO;

public interface MeterService {

	MeterDTO createMeter(MeterDTO dto);
	MeterDTO getMeterById(Long id);
	List<MeterDTO> getAllMeters();
	MeterDTO updateMeter(Long id,MeterDTO dto);
	void deleteMeter(Long id);
}
