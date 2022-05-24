package uz.pdp.appcardtransfer.main;

import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);
    int id;
    private String username;
    private String email;
    private String password;
    String name;
    String address;

    String password1, newPassword, newPassword1;

    public User(int id, String username, String email, String password, String name, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    /**
     * Qayerda xato bo'lishi mumkin
     */

    public void changePassword(){
        System.out.println("Parolni o'zgartirish uchun amaldagi parolni kiriting: ");
        password1 = scanner.next();
        if (password1.equals(password)){
            System.out.println("O'zgartirmoqchi bo'lgan yangi parolni kiriting ");
            newPassword = scanner.next();
            System.out.println("Takrorlang: ");
            newPassword1 = scanner.next();
            if (newPassword1.equals(newPassword)){
                System.out.println("Parol muvaffaqiyatli o'zgartirildi.");
            }else {
                System.out.println("Parol xato.");
            }
        }else {
            System.out.println("Noto'g'ri parol kiritdingiz.");
        }
    }
}
