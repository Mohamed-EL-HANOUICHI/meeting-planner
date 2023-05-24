package meeting.planner.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import meeting.planner.entities.Room;
import meeting.planner.requests.MeetingType;
import meeting.planner.requests.RoomRequest;
import meeting.planner.services.ReservationService;

@RestController
@CrossOrigin(origins = "**")
@RequestMapping("/api")
public class ReservationController {
	
	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

	@Autowired
	private ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping("/room")
	public ResponseEntity<Room> getRoom(@RequestParam int personsNumber, @RequestParam MeetingType meetingType, @RequestParam String startDate, @RequestParam String endDate) {	
		
		RoomRequest roomRequest = new RoomRequest();
		roomRequest.setMeetingType(meetingType);
		roomRequest.setPersonsNumber(personsNumber);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

		roomRequest.setStarDate(LocalDateTime.parse(startDate, formatter));
		roomRequest.setEndDate(LocalDateTime.parse(endDate, formatter));
		
		return ResponseEntity.status(HttpStatus.OK).body(reservationService.reservateRoom(roomRequest));
	}
	
	// to test
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {	
				
		return ResponseEntity.status(HttpStatus.OK).body("Hello word");
	}
		
}
