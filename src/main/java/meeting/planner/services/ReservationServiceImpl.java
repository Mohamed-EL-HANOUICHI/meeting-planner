package meeting.planner.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meeting.planner.dao.Equipment;
import meeting.planner.dao.Meeting;
import meeting.planner.dao.MeetingServiceDao;
import meeting.planner.dao.Room;
import meeting.planner.dao.RoomServiceDao;
import meeting.planner.requests.MeetingType;
import meeting.planner.requests.RoomRequest;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private RoomServiceDao roomServiceDao;
	
	@Autowired
	private MeetingServiceDao meetingDoa;
	
	public ReservationServiceImpl(RoomServiceDao roomServiceDao, MeetingServiceDao meetingDoa) {
		this.roomServiceDao = roomServiceDao;
		this.meetingDoa = meetingDoa;
	}
	
	@Override
	public Room reservateRoom(RoomRequest roomRequest) {
		
		return roomServiceDao.findAll().stream()
				                       .filter(room -> roomRequest.getPersonsNumber() <= room.getCapacity() && isAvailable(room, roomRequest.getStarDate(), roomRequest.getEndDate()) && isEquiped(room, roomRequest.getMeetingType()))
				                       .collect(Collectors.toList())
				                       .get(0);
	}
	
	private boolean isAvailable(Room room, LocalDateTime start, LocalDateTime end) {
		List<Meeting> meetings = meetingDoa.getAvailableRooms(room.getName(), start, end);
		
		return meetings.isEmpty();
		
	}
	
   private boolean isEquiped(Room room, MeetingType meetingType) {
		
		List<Equipment> equipments = room.getEquipments();
		
		switch (meetingType) {
		case VC:
			
			return equipments.contains("Ecran") && equipments.contains("Pieuvre") && equipments.contains("Webcam");
        case SPEC:
			
			return equipments.contains("Tableau");
        case RS:
			
			return room.getCapacity() >= 3;

       case RC:
	
			return equipments.contains("Tableau") && equipments.contains("Ecran") && equipments.contains("Pieuvre");

		default:
			return false;
		}				
	}
	
}
