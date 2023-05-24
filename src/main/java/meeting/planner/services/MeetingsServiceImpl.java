package meeting.planner.services;

import lombok.AllArgsConstructor;
import meeting.planner.entities.Meeting;
import meeting.planner.repositories.MeetingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MeetingsServiceImpl implements MeetingService{


    private final MeetingsRepository meetingsRepository;
    @Override
    public List<Meeting> allMeetings() {
        return meetingsRepository.findAll();
    }

    @Override
    public Meeting createMeeting(Meeting meeting) {
        return meetingsRepository.save(meeting);
    }
}
