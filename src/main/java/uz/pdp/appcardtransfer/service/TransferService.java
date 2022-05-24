package uz.pdp.appcardtransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcardtransfer.entity.Card;
import uz.pdp.appcardtransfer.entity.Income;
import uz.pdp.appcardtransfer.entity.Outcome;
import uz.pdp.appcardtransfer.payload.TransferDto;
import uz.pdp.appcardtransfer.repository.CardRepository;
import uz.pdp.appcardtransfer.repository.IncomeRepository;
import uz.pdp.appcardtransfer.repository.OutcomeRepository;
import uz.pdp.appcardtransfer.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    JwtProvider jwtProvider;
     static final Double COMMISSION = 0.01;
    public HttpEntity<?> transfer(TransferDto transferDto, HttpServletRequest request){
        String username = getUsername(request);
        Optional<Card> optionalFromCard =
                cardRepository.findByUsernameAndIdAndStatusTrue(username, transferDto.getFromCard());
        if (!optionalFromCard.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Card fromCard = optionalFromCard.get();
        Optional<Card> optionalToCard = cardRepository.findByIdAndStatusTrue(transferDto.getToCard());
        if (!optionalFromCard.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Card toCard = optionalToCard.get();
        Double amount = transferDto.getAmount();
        if (fromCard.getBalanse()<amount*(COMMISSION+1))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
            fromCard.setBalanse(fromCard.getBalanse()-amount*(COMMISSION+1));
            toCard.setBalanse(toCard.getBalanse()+amount);

        Income income = new Income();
        income.setFromCard(fromCard);
        income.setToCard(toCard);
        income.setAmount(amount);
        incomeRepository.save(income);

        Outcome outcome = new Outcome();
        outcome.setFromCard(fromCard);
        outcome.setToCard(toCard);
        outcome.setAmount(amount);
        outcome.setCommisionAmount(amount*(COMMISSION+1));
        outcomeRepository.save(outcome);

        cardRepository.save(fromCard);
        cardRepository.save(toCard);
        return ResponseEntity.ok("Transfer successfly");
    }

    public String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        return username;
    }
}
