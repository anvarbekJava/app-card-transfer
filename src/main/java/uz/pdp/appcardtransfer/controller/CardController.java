package uz.pdp.appcardtransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcardtransfer.payload.CardDto;
import uz.pdp.appcardtransfer.payload.UserDto;
import uz.pdp.appcardtransfer.service.CardService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/card")
public class CardController {

    @Autowired
    CardService cardService;

//    CardController(){
//        cardService.settingsCard();
//    }

    @PostMapping("/create")
    public HttpEntity<?> createCard(HttpServletRequest request, @RequestBody CardDto cardDto){
        HttpEntity<?> httpEntity = cardService.addCard(cardDto, request);
        return ResponseEntity.ok(httpEntity);
    }

    @GetMapping(value = "/get/{id}")
    public HttpEntity<?> getByCardId(@PathVariable Integer id, HttpServletRequest request){
        HttpEntity<?> card = cardService.getByUsernameAndId(request, id);
        return ResponseEntity.ok(card);
    }


}
