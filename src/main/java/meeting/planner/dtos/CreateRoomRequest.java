package meeting.planner.dtos;

import lombok.Data;
import meeting.planner.entities.Equipment;

import java.util.List;

@Data
public class CreateRoomRequest {

    private String name;
    private int capacity;
    private List<Equipment> equipments;


}
