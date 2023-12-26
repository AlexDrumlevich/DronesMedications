package telran.drones.entities;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import telran.drones.dto.LogDto;
import telran.drones.dto.State;
@Entity

@NoArgsConstructor
@Table(name="logs")
@Getter
public class EventLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@ManyToOne
	@JoinColumn(name="drone_number", nullable = false, updatable = false)
	Drone drone;
	@ManyToOne
	@JoinColumn(name="medication_code", nullable = false, updatable = false)
	Medication medication;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	LocalDateTime timestamp;
	@Column(nullable = false)
	State droneState;
	@Column(nullable = false)
	byte batteryCapacity;
	
	public LogDto build() {
		return new LogDto(timestamp, drone.number, droneState, batteryCapacity, medication.code);
	}

	public EventLog(Drone drone, Medication medication, LocalDateTime timestamp) {
		this.drone = drone;
		this.medication = medication;
		this.timestamp = timestamp;
		this.droneState = drone.state;
		this.batteryCapacity = drone.batteryCapacity;
	}	
}
