package com.konradzadroga.drivingschool.rest_api.picture;

import com.konradzadroga.drivingschool.rest_api.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="pictures")
@NoArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String url;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Picture(@NotBlank String url, User user) {
        this.url = url;
        this.user = user;
    }
}
