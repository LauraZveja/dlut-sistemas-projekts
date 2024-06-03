package eu.virac.dlut.models;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity(name = "UserToken")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "idUserToken")
    private int idUserToken;


    @NotNull
    @Column(name = "dn")
    private String dn;

    @NotNull
    @Column(name = "token")
    private String token;

    @NotNull
    @Column(name = "expired")
    private Date expired;

    public UserToken(@NotNull String dn, @NotNull String token, @NotNull Date expired) {
        this.dn = dn;
        this.token = token;
        this.expired = expired;
    }


}
