package meeting.planner.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import meeting.planner.entities.Room;

@Repository
public interface RoomServiceDao extends JpaRepository<Room, Long> {
	
	List<Room> findAll();

}
