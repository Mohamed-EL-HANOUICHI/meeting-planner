package meeting.planner.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import meeting.planner.entities.Room;
import meeting.planner.requests.MeetingType;
import meeting.planner.services.ReservationService;


@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {
	
	@Mock
	private ReservationService reservationService;
		
	@InjectMocks
	private ReservationController reservationController;
	
	
	@Test
	public void getRoomTest() {
		
		//given		
		Room roomExpected = new Room();
		roomExpected.setCapacity(10);
		roomExpected.setName("E100");
		Mockito.when(reservationService.reservateRoom(Mockito.any())).thenReturn(roomExpected);
		
		//when
		ResponseEntity<Room> roomResult = reservationController.getRoom(3, MeetingType.VC, "2023-05-24 04:00", "2023-05-24 05:00");		
		
		//then		
		assertEquals(roomExpected, roomResult.getBody());
		assertEquals(HttpStatus.OK, roomResult.getStatusCode());		
	}

}
