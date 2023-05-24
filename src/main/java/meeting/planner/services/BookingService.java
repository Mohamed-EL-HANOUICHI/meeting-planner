package meeting.planner.services;

import meeting.planner.entities.Room;
import meeting.planner.dtos.BookRoomRequest;


public interface BookingService {
	
	Room book(BookRoomRequest bookRoomRequest);

}
