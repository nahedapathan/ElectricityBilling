package ebs.billing.dto;

import lombok.Data;

@Data
public class MeterDTO {

	private Long meterId;

    private String meterNumber;

    private String meterType;

    private String phaseCode;

    private String installationAddress;

    private Long customerId;
}
