package ebs.billing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="meters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meterId;
	
	@Column(unique = true)
	private String meterNumber;
	private String meterType;
	private String phaseCode;
	private String installationAddress;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
}
