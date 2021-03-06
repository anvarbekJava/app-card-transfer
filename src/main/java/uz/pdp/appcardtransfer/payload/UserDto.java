package uz.pdp.appcardtransfer.payload;

import lombok.Data;
/*Pul o'tkazmalarini amalga oshiruvchi dastur tuzing.
        User sistemaga authentication orqali kirib, o'ziga tegishli CARD orqali boshqa CARD ga pul transfer qilish jarayonini amalga oshirsin.
        Transfer jarayonida CARD da o'tkazilayotgan va transfer uchun kommisiya miqdoridagi mablag' yetarli ekanligi va CARD shu user ga tegishli ekanligi tekshirilsin.
        Kartadagi kirimlarni va chiqimlarni alohida yozib boring.
        Foydalanuvchi o'ziga tegishli card tarixini (output, income) ko'rsatuvchi method yozing.
        Blank diagram.pdf*/
@Data
public class UserDto {
    private String username;
    private String password;
}
