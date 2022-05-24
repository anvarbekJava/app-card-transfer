package uz.pdp.appcardtransfer.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CardDto {

    @NotNull
    @Size(min = 16, max = 16)
    private String number;

    private Double balanse;

}
