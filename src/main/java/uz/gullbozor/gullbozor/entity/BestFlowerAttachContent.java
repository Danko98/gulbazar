package uz.gullbozor.gullbozor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "best_flower_attachment_content")
public class BestFlowerAttachContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] bytes;

    @OneToOne
    private BestFlowerAttach bestFlowerAttach;


 }
