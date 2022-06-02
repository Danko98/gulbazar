package uz.gullbozor.gullbozor.repository;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.gullbozor.gullbozor.entity.Announce;

import java.sql.Timestamp;
import java.util.List;

public interface AnnounceRepo extends JpaRepository<Announce, Long> {

    Page<Announce> findAllByFlowerType(Pageable pageable, Integer flowerType);
//
    Page<Announce> findAllByShopId(Long shopId, Pageable pageable);





//
//    Page<Announce> findAllByUserEntity_Id(Long userEntity_id, Pageable pageable);
//
//   // List<Announce> findByFlowerTypeAndOrderByCreateAtDesc(Integer flowerType);
//
//
//    List<Announce> findTop7ByFlowerTypeOrderByCreateAtDesc(Integer flowerType);
//
//    List<Announce> findTop7ByFlowerTypeAndCreateAtGreaterThanEqualOrderByCreateAtDesc(Integer flowerType, Timestamp createAt);
//







}
