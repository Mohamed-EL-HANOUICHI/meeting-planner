package meeting.planner.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int capacity;

    @ElementCollection(targetClass = Equipment.class)
    @JoinTable(name = "equipements", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Equipment> equipments;

    @OneToMany
    private List<Meeting> meetings;

}
