package FinalTest.zavrsniback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import FinalTest.zavrsniback.model.Stanje;

@Repository
public interface StanjeRepository extends JpaRepository<Stanje, Long> {

}
