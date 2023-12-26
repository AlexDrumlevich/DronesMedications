package telran.drones;

//('Drone-1', 'Middleweight', 300, 100, 'IDLE'),
//('Drone-2', 'Middleweight', 300, 100, 'IDLE');

//('MED_1', 'Medication-1', 200),
//('MED_2', 'Medication-2', 350)	


import static org.junit.jupiter.api.Assertions.*;

import java.security.Provider.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.Any;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import ch.qos.logback.core.status.Status;
import telran.drones.api.PropertiesNames;
import telran.drones.dto.LogDto;
import telran.drones.dto.State;
import telran.drones.repo.DroneRepo;
import telran.drones.service.DronesService;
import telran.drones.service.DronesServiceImpl;

@SpringBootTest(properties = {PropertiesNames.PERIODIC_UNIT_MICROS + "=10"})
@Sql(scripts = "test_periodic_data.sql")
class DronesServicePeriodicTaskTest {

 @Autowired
 DronesService dronesService;
 @Autowired
 DroneRepo dronesRepo;
@Autowired
Map<State, State> stateMovesMap;
@Value("${" + PropertiesNames.DECREASE_BATTERY_PER_PERIODIC_UNIT + ":2}")
int decreaseBatteryPerPeriodicUnit;
 	@Test
	void test() throws InterruptedException {	
		
 		
		dronesService.loadDrone("Drone-1", "MED_1");
		
		Thread.sleep(1000);
		
		//checking to return to state IDLE and to battery level 100
		assertEquals(State.IDLE, dronesRepo .findById("Drone-1").get().getState());
		assertEquals(100, dronesRepo.findById("Drone-1").get().getBatteryCapacity());

		//checking event logs
		List<LogDto> logs = dronesService.checkLogs("Drone-1")
				.stream()
				.sorted((l1, l2) -> l1.timestamp().compareTo(l2.timestamp()))
				.toList();
		
		Object[] stateBattery = {State.LOADING, 100};
	
		logs.stream().forEach(l -> {
			
			assertEquals((State)stateBattery[0], l.state());
			assertEquals((int)stateBattery[1], l.batteryCapacity());
			
			
		 	if(!(l.state().equals(State.IDLE))) {
		 		stateBattery[0] = stateMovesMap.get(l.state());			
				stateBattery[1] = (int)stateBattery[1] - decreaseBatteryPerPeriodicUnit;
		
		 	} else {
		 		stateBattery[0] = State.IDLE;			
				stateBattery[1] = (int)stateBattery[1] + decreaseBatteryPerPeriodicUnit;
			}
			
		
			
			
		});
		
		
	}

}
