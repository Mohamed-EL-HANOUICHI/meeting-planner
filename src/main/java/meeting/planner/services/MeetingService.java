package meeting.planner.services;

import meeting.planner.entities.Meeting;

import java.util.List;

public interface MeetingService {

    List<Meeting> allMeetings();
    Meeting createMeeting(Meeting meeting);
}
