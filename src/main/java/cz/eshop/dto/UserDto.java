package cz.eshop.dto;

import cz.eshop.model.Authorities;
import cz.eshop.model.Types.BeltTypes;
import cz.eshop.model.Types.StripeTypes;
import cz.eshop.model.User;

import javax.validation.constraints.NotNull;

//mohu posílat jen jeden model attribute -> sdružuji několik tabulek
public class UserDto {
    @NotNull
    private User user;
    private Authorities authorities;

    public UserDto() {}

    public UserDto(User user, Authorities authorities) {
        this.user = user;
        this.authorities = authorities;
        //BeltTypes.BLUE.setStripes(StripeTypes.ZERO.Number());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }
}
