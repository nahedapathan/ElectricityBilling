package ebs.billing.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BillDTO {

	private Long billId;

    private Integer unitsConsumed;

    private Double amount;

    private LocalDate billDate;

    private String billStatus;

    private Long customerId;

    private Long taxId;
}
