package telran.droneMedications.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Drone {

@Id
@Column(length = 100)
String serialNumber;

@Column(nullable = false)
@Enumerated(value=EnumType.STRING)
DroneModelType model;

@Min(0)
@Max(500)
@Column(nullable = false)
int weightLimit;

@Column(nullable = false)
@Min(0)
@Max(100)
int batteryCapacity;

@Column(nullable = false)
@Enumerated(value=EnumType.STRING)
DroneState state;

}
