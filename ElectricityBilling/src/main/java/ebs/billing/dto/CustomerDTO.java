package ebs.billing.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDTO {

	private Long customerId;
	
	@NotBlank(message="Customer name is required")
	private String customerName;
	
	@Email(message="Invalid email format")
	private String email;
	
	@Pattern( regexp = "^[0-9]{10}$")
	private String phone;
	private String address;
}
