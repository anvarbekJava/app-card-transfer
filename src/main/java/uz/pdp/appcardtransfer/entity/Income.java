package uz.pdp.appcardtransfer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Card fromCard;

    @ManyToOne(optional = false)
    private Card toCard;

    @Column(nullable = false)
    private Double amount;

    @Column(columnDefinition = "timestamp default now()")
    private Timestamp date = Timestamp.valueOf(LocalDateTime.now());
}
