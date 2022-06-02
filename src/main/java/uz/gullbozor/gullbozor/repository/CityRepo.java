package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.CityEntity;
import uz.gullbozor.gullbozor.entity.RegionEntity;

public interface CityRepo extends JpaRepository<CityEntity, Integer> {

    boolean existsByName(String name);

}
