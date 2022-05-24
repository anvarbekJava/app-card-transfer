package uz.pdp.appcardtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcardtransfer.entity.Income;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<Income> findAllById(Integer id);
}
