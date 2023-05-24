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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import meeting.planner.controller.BookingController;
import meeting.planner.dtos.BookRoomRequest;
import meeting.planner.dtos.MeetingType;
import meeting.planner.entities.Meeting;
import meeting.planner.entities.Room;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {
	
	    @Mock
	    private RoomsService roomsService;
	    
	    @Mock
	    private MeetingService meetingService;

	    @InjectMocks
	    private BookingServiceImpl bookingServiceImpl;


	    @Test
	    void getRoomTest() {

	        //given
	    	List<Room> availableRooms = new ArrayList<Room>();
	        Room roomExpected = new Room();
	        roomExpected.setCapacity(10);
	        roomExpected.setName("E100");
	        availableRooms.add(roomExpected);
	        Mockito.when(roomsService.findAvailableRooms(Mockito.any(),Mockito.any(),Mockito.anyInt())).thenReturn(availableRooms);
	        Meeting bookedMeeting = new Meeting();
	        bookedMeeting.setRoom(roomExpected);
	        Mockito.when(meetingService.createMeeting(Mockito.any())).thenReturn(bookedMeeting);

	        BookRoomRequest bookRoomRequest = new BookRoomRequest();
	        bookRoomRequest.setMeetingType(MeetingType.RS);
	        bookRoomRequest.setStarDate("2023-05-24 04:00");
	        bookRoomRequest.setEndDate("2023-05-24 05:00");
	        bookRoomRequest.setPersonsNumber(3);

	        //when
	        Room roomResult = bookingServiceImpl.book(bookRoomRequest);

	        //then
	        assertEquals(roomExpected, roomResult);
	    }


}
