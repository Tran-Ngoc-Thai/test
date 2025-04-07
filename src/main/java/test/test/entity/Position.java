package test.test.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Positions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer positionID;

    @Column(nullable = false, length = 100, unique = true)
    private String positionName;

    @Column(columnDefinition = "TEXT")
    private String description;
}