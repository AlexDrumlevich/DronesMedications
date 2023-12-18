package telran.droneMedications.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
public class Medication {
	
	@Id
	@Pattern(regexp = "(-|//w|//d|_)+")
	String name;
	
	@Column(nullable = false)
	@Min(0)
	double weight;
	
	@Column(nullable = false)
	@Pattern(regexp = "(//[A-Z]|//d|_)+")
	String code;
}
