package meeting.planner.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import meeting.planner.dtos.BookRoomRequest;
import meeting.planner.entities.Room;
import meeting.planner.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "**")
@RequestMapping("/api/rooms")
@AllArgsConstructor
@Slf4j
public class BookingController {

	private final BookingService bookingService;


	@PostMapping("/book")
	public ResponseEntity<Room> book(@RequestBody BookRoomRequest bookRoomRequest) {

		log.info("Booking a room");
		final Room room = bookingService.book(bookRoomRequest);
		return ResponseEntity.status(HttpStatus.OK).body(room);
	}

}
