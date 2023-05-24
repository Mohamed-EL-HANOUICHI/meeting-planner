package meeting.planner.controller;


import lombok.AllArgsConstructor;
import meeting.planner.dtos.BookRoomRequest;
import meeting.planner.entities.Meeting;
import meeting.planner.entities.Room;
import meeting.planner.services.MeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "**")
@RequestMapping("/api/meetings")
@AllArgsConstructor
public class MeetingsController {

    private final MeetingService meetingService;


    @GetMapping("/all")
    public ResponseEntity<List<Meeting>> allMeetings() {

        final List<Meeting> meetings = meetingService.allMeetings();
        return ResponseEntity.status(HttpStatus.OK).body(meetings);
    }

}
