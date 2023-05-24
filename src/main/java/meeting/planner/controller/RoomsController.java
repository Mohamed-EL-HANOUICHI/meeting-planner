package meeting.planner.controller;


import lombok.AllArgsConstructor;
import meeting.planner.dtos.CreateRoomRequest;
import meeting.planner.entities.Meeting;
import meeting.planner.entities.Room;
import meeting.planner.services.RoomsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "**")
@RequestMapping("/api/rooms")
@AllArgsConstructor
public class RoomsController {

    private final RoomsService roomsService;

    @GetMapping("/all")
    public ResponseEntity<List<Room>> allRooms() {

        final List<Room> rooms = roomsService.allRooms();
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    @PostMapping("/create")
    public ResponseEntity<Room> create(@RequestBody CreateRoomRequest createRoomRequest) {

        final Room room =  roomsService.createRoom(createRoomRequest);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }
}
