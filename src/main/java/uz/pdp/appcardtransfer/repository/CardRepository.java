package uz.pdp.appcardtransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcardtransfer.entity.Card;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Boolean existsByNumberAndUsername(String number, String username);

    Optional<Card> findByUsername(String username);

    Card findByUsernameAndId(String username, Integer id);

    Optional<Card> findByUsernameAndIdAndStatusTrue(String username, Integer id);

    Optional<Card> findByIdAndStatusTrue(Integer id);

    Optional<Card> findByUsernameAndStatusTrue(String username);
}
