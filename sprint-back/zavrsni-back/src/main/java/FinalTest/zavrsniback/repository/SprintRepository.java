package FinalTest.zavrsniback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FinalTest.zavrsniback.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
