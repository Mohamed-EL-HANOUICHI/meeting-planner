package meeting.planner.services;

import meeting.planner.dao.Room;
import meeting.planner.requests.RoomRequest;

public interface ReservationService {
	
	Room reservateRoom(RoomRequest roomRequest);

}
