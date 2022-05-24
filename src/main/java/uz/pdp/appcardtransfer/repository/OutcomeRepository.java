package uz.pdp.appcardtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcardtransfer.entity.Outcome;

import java.util.List;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {
    List<Outcome> findAllById(Integer id);
}
