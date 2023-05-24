package meeting.planner.repositories;

import meeting.planner.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomsRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.capacity >= :personsNumber " +
            "AND r NOT IN (SELECT m.room FROM Meeting m " +
            "WHERE (:startDate < m.endDate) AND (:endDate > m.startDate))")
    Optional<List<Room>> findAvailableRoomsForMeeting(LocalDateTime startDate, LocalDateTime endDate, int personsNumber);
}

