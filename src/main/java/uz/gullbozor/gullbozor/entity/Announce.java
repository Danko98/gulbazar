package uz.gullbozor.gullbozor.entity;
import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "announce")
public class Announce extends BaseEntity {

//    @ManyToOne
//    private UserEntity userEntity;
//
//    @ManyToOne
//    private Category category;

    @Column(name = "price")
    private double price;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "title")
    private String title;




    private String imageIds;
    private Integer flowerType;
    private Long shopId;
    private double height;
    private double diameter;
    private double weight;
    private boolean isWithPot;
    private boolean isWithFertilizer;
    private boolean isAllowed;

    private String description;


}
