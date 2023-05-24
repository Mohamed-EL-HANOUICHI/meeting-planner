package meeting.planner.services;

import lombok.AllArgsConstructor;
import meeting.planner.dtos.BookRoomRequest;
import meeting.planner.dtos.MeetingType;
import meeting.planner.entities.Equipment;
import meeting.planner.entities.Meeting;
import meeting.planner.entities.Room;
import meeting.planner.exceptions.NoRoomsAvailableException;
import meeting.planner.repositories.MeetingsRepository;
import meeting.planner.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomsService roomsService;
    private final MeetingService meetingService;


    @Override
    public Room book(BookRoomRequest bookRoomRequest) {

        final LocalDateTime startDate = DateUtils.toLocalDateTime(bookRoomRequest.getStarDate());
        final LocalDateTime endDate = DateUtils.toLocalDateTime(bookRoomRequest.getEndDate());

        final List<Room> availableRooms = roomsService
                .findAvailableRooms(startDate, endDate, bookRoomRequest.getPersonsNumber());

        final List<Equipment> neededEquipments = getNeededEquipments(bookRoomRequest.getMeetingType());

        final Predicate<Room> roomPredicate = room -> bookRoomRequest.getMeetingType().equals(MeetingType.RS) ?
                room.getCapacity() >= 3 :
                room.getEquipments().containsAll(neededEquipments);

        final Room matchingRoom = availableRooms.stream()
                .filter(roomPredicate)
                .findAny()
                .orElseThrow(NoRoomsAvailableException::new);

        final Meeting meeting = buildMeeting(bookRoomRequest, startDate, endDate, matchingRoom);

        final Meeting bookedMeeting = meetingService.createMeeting(meeting);

        return bookedMeeting.getRoom();
    }

    private Meeting buildMeeting(BookRoomRequest bookRoomRequest, LocalDateTime startDate, LocalDateTime endDate, Room matchingRoom) {
        final Meeting meeting = new Meeting();
        meeting.setMeetingType(bookRoomRequest.getMeetingType().name());
        meeting.setStartDate(startDate);
        meeting.setEndDate(endDate);
        meeting.setRoom(matchingRoom);
        return meeting;
    }

    private List<Equipment> getNeededEquipments(MeetingType meetingType) {

        switch (meetingType) {
            case VC:
                return Arrays.asList(Equipment.ECRAN, Equipment.PIEUVRE, Equipment.WEBCAM);
            case SPEC:
                return Collections.singletonList(Equipment.TABLEAU);
            case RC:
                return Arrays.asList(Equipment.TABLEAU, Equipment.ECRAN, Equipment.PIEUVRE);
            default:
                return Collections.emptyList();
        }
    }
}
