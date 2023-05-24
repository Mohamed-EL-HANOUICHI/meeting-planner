package meeting.planner.services;

import meeting.planner.dtos.CreateRoomRequest;
import meeting.planner.entities.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomsService {

    List<Room> allRooms();

    Room createRoom(CreateRoomRequest createRoomRequest);

    List<Room> findAvailableRooms(LocalDateTime startDate, LocalDateTime endDate, int capacity);
}

