package uz.pdp.appcardtransfer.security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    static long expireTime = 36000000;
    static String kalitSoz = "BuKalitMaxfiySozHechKimBilmasin";

    public String generateToken(String username){
        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, kalitSoz)
                .compact();
        return token;
    }
    public Boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(kalitSoz)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public String getUsernameFromToken(String token){
        String username = Jwts
                .parser()
                .setSigningKey(kalitSoz)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }
}
