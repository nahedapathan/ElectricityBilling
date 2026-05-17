package ebs.billing.dto;

import lombok.Data;

@Data
public class TaxDTO {

	private Long taxId;

    private String taxName;

    private Double taxPercentage;
}
