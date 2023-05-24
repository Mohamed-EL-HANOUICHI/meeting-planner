package meeting.planner.controller;

import meeting.planner.dtos.BookRoomRequest;
import meeting.planner.dtos.MeetingType;
import meeting.planner.entities.Room;
import meeting.planner.services.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;


    @Test
    void getRoomTest() {

        //given
        final Room roomExpected = new Room();
        roomExpected.setCapacity(10);
        roomExpected.setName("E100");
        Mockito.when(bookingService.book(Mockito.any())).thenReturn(roomExpected);

        final BookRoomRequest bookRoomRequest = new BookRoomRequest();
        bookRoomRequest.setMeetingType(MeetingType.VC);
        bookRoomRequest.setStarDate("2023-05-24 04:00");
        bookRoomRequest.setEndDate("2023-05-24 05:00");
        bookRoomRequest.setPersonsNumber(3);

        //when
        ResponseEntity<Room> roomResult = bookingController.book(bookRoomRequest);

        //then
        assertEquals(roomExpected, roomResult.getBody());
        assertEquals(HttpStatus.OK, roomResult.getStatusCode());
    }

}
