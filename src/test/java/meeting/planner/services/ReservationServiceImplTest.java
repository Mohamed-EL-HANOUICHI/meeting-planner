package meeting.planner.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import meeting.planner.dao.MeetingServiceDao;
import meeting.planner.dao.RoomServiceDao;
import meeting.planner.entities.Meeting;
import meeting.planner.entities.Room;
import meeting.planner.requests.MeetingType;
import meeting.planner.requests.RoomRequest;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {
	
	@Mock
	private RoomServiceDao roomServiceDao;
	
	@Mock
	private MeetingServiceDao meetingServiceDoa;
	
	@InjectMocks
	private ReservationServiceImpl reservationServiceImpl;

	@Test
	public void reservateRoomTest() {
		
		//given		
		List<Room> listRooms = new ArrayList<>();
		Room room = new Room();
		room.setCapacity(10);
		room.setName("E100");
		listRooms.add(room);
		Mockito.when(roomServiceDao.findAll()).thenReturn(listRooms);
		
		List<Meeting> listMeetings = new ArrayList<>();
		
		Mockito.when(meetingServiceDoa.getAvailableRooms(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(listMeetings);

		RoomRequest roomRequest = new RoomRequest();
		roomRequest.setPersonsNumber(5);
		roomRequest.setMeetingType(MeetingType.RS);
		
		//when
		Room roomResult = reservationServiceImpl.reservateRoom(roomRequest);		
		
		//then		
		assertEquals(listRooms.get(0), roomResult);
	}


}
