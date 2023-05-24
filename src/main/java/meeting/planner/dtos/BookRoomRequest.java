package meeting.planner.dtos;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BookRoomRequest {
		
	private int personsNumber;
	private MeetingType meetingType;
	private String starDate;
	private String endDate;

}
