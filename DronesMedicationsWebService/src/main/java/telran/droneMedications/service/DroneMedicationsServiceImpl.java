package telran.droneMedications.service;

import java.util.List;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DroneMedicationsServiceImpl implements DroneMedicationsService {

	@Transactional(readOnly = false)
	@Override
	public DroneDto dronRregistration(DroneDto drone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean droneLoadingWith(MedicationDto medication) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MedicationDto> checkLoadedMedicationItemsFor(DroneDto drone) {
		// TODO Auto-generated method stub
		return null;
	}

}
