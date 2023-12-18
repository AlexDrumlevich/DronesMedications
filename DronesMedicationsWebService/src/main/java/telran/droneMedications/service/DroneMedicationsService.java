package telran.droneMedications.service;

import java.util.List;

public interface DroneMedicationsService {
	public DroneDto dronRregistration(DroneDto drone);
	public boolean droneLoadingWith(MedicationDto medication);
	public List<MedicationDto> checkLoadedMedicationItemsFor(DroneDto drone); 
}
