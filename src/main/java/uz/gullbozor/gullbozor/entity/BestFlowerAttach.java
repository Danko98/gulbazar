package uz.gullbozor.gullbozor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "best_flower_attachment")
public class BestFlowerAttach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fileOriginalName;
    private Long size;
    private String contentType;

    @OneToOne
    @JoinColumn(name = "id")
    private BestFlowerEntity bestFlowerEntity;


}
