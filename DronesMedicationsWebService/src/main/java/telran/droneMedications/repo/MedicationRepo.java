package telran.droneMedications.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.droneMedications.entities.Medication;

public interface MedicationRepo extends JpaRepository<Medication, String> {

}
