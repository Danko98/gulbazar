package uz.gullbozor.gullbozor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "best_flower")
public class BestFlowerEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "view")
    private Long view;

    @Column(name = "like")
    private Long like;

    @Column(name = "flowerValue")
    private Long flowerValue;

}
