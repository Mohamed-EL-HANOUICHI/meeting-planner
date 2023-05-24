package meeting.planner.requests;

import java.time.LocalDateTime;

public class RoomRequest {
		
	private int personsNumber;
	private MeetingType meetingType;
	private LocalDateTime starDate;
	private LocalDateTime endDate;
	
	public int getPersonsNumber() {
		return personsNumber;
	}
	public void setPersonsNumber(int personsNumber) {
		this.personsNumber = personsNumber;
	}
	public MeetingType getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(MeetingType meetingType) {
		this.meetingType = meetingType;
	}
	public LocalDateTime getStarDate() {
		return starDate;
	}
	public void setStarDate(LocalDateTime starDate) {
		this.starDate = starDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	
	
}
