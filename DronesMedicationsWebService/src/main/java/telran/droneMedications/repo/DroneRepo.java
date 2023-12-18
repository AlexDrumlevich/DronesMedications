package telran.droneMedications.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.droneMedications.entities.Drone;

public interface DroneRepo extends JpaRepository<Drone, String> {

}
