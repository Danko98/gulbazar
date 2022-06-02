package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.ShopEntity;

public interface ShopRepo extends JpaRepository<ShopEntity, Long> {

    boolean existsByShopName(String shopName);


}
