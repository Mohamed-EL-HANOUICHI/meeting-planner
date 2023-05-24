package meeting.planner.services;

import lombok.AllArgsConstructor;
import meeting.planner.dtos.CreateRoomRequest;
import meeting.planner.entities.Room;
import meeting.planner.exceptions.NoRoomsAvailableException;
import meeting.planner.repositories.RoomsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RoomsServiceImpl implements RoomsService{

    private final RoomsRepository roomsRepository;

    @Override
    public List<Room> allRooms() {
        return roomsRepository.findAll();
    }

    @Override
    public Room createRoom(CreateRoomRequest createRoomRequest) {

        Room room = new Room();
        room.setCapacity(createRoomRequest.getCapacity());
        room.setName(createRoomRequest.getName());
        room.setEquipments(createRoomRequest.getEquipments());
        return roomsRepository.save(room);
    }


    @Override
    public List<Room> findAvailableRooms(LocalDateTime startDate, LocalDateTime endDate, int capacity){
        final Optional<List<Room>> availableRooms = roomsRepository
                .findAvailableRoomsForMeeting(startDate, endDate, capacity);
       return availableRooms.orElseThrow(NoRoomsAvailableException::new);
    }
}
