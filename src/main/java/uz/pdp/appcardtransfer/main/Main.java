package uz.pdp.appcardtransfer.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Path source = Paths.get("D://A.txt");
        Path dest = Paths.get("D://B.txt");

        try {
            Files.copy(source,dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
