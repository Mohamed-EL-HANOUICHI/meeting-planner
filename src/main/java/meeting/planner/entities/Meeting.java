package meeting.planner.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class Meeting {

    @Id
    @GeneratedValue
    private Long id;
    private String meetingType;
    private String personsNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    private Room room;

}
