package by.itechart.model.refreshToken;


import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @Column
    @SequenceGenerator(name = "token_seq", sequenceName = "token_seq",
                        initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "token_seq")
    private Long id;

    @Column
    private String subject;

    @Column
    private String token;

    @Column(name = "expirydate")
    private Instant expiryDate;

}
