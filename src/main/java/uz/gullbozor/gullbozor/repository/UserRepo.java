package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.UserEntity;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    boolean existsByUserNameAndPassword(String userName, String password);
    boolean existsByPhoneNumber(String phoneNumber);



    Optional<UserEntity> findByUserName(String userName);

//    boolean existsByEmail(String email);
}
