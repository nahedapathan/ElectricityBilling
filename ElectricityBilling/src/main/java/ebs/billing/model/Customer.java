package ebs.billing.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
	
    private String customerName;
    
    @Column(unique=true)
    private String email;
    
    private String phone;
    
    private String address;
    
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Meter meter;
    
    @OneToMany(mappedBy = "customer",cascade=CascadeType.ALL)
    private List<Bill> bills;
    
    
    


}
