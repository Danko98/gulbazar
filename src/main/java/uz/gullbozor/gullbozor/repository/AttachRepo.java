package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.Announce;
import uz.gullbozor.gullbozor.entity.Attach;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttachRepo extends JpaRepository<Attach, Long> {
    List<Attach> findAllByMainAttachId(Long mainAttachId);


}
