package meeting.planner.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MeetingServiceDao extends CrudRepository<Meeting, Long> {
	
	@Query("Select * from meeting left join room on room.id = meeting.id where room.name = ?1 and startDate >= ?2 and endDate <= ?3 ")
	List<Meeting> getAvailableRooms(String name, LocalDateTime startDate, LocalDateTime endDate);
		
}
