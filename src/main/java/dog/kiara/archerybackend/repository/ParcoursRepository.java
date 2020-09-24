package dog.kiara.archerybackend.repository;

import dog.kiara.archerybackend.entity.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParcoursRepository extends JpaRepository<Parcours, String> {
//    boolean existingParcour(int id);
}

