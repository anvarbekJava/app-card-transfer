package uz.pdp.appcardtransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcardtransfer.entity.Card;
import uz.pdp.appcardtransfer.payload.CardDto;
import uz.pdp.appcardtransfer.repository.CardRepository;
import uz.pdp.appcardtransfer.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    JwtProvider jwtProvider;

    public HttpEntity<?> addCard(CardDto cardDto, HttpServletRequest request){
        if (cardRepository.existsByNumberAndUsername(cardDto.getNumber(), getUsername(request)))
            return ResponseEntity.ok("Bunday card mavjud!");
        Card card = new Card();
        card.setUsername(getUsername(request));
        card.setNumber(cardDto.getNumber());
        card.setExpireDate(new Date(System.currentTimeMillis()+(1000*60*60*24*365*4L)));
        card.setBalanse(cardDto.getBalanse());
        card.setStatus(true);
        cardRepository.save(card);
        return ResponseEntity.ok("Saqlandi");
    }


    public HttpEntity<?> getByUsernameAndId(HttpServletRequest request, Integer id){
        String username = getUsername(request);
        return ResponseEntity.ok(cardRepository.findByUsernameAndId(username, id));
    }
    public void settingsCard(){
        List<Card> cardList = cardRepository.findAll();
        for (Card card : cardList) {
            if (card.getExpireDate().before(new Date())){
                card.setStatus(false);
                cardRepository.save(card);
            }
        }

    }
    public  String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        return username;
    }

}
