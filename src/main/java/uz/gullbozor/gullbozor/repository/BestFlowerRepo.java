package uz.gullbozor.gullbozor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.gullbozor.gullbozor.entity.BestFlowerEntity;

public interface BestFlowerRepo extends JpaRepository<BestFlowerEntity, Long> {
    BestFlowerEntity findByFlowerValue(Long flowerValue);
    BestFlowerEntity findByFlowerValueIsBetween(Long view, Long view2);

    @Query(value = "SELECT * FROM best_flower ORDER BY view ASC LIMIT 1", nativeQuery = true)
    BestFlowerEntity getLastStudentDetails();

}
