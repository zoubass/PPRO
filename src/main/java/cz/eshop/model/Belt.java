package cz.eshop.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="belts")
public class Belt {
    private long id;
    private String color;
    private int stripe = 0;

    @Id
    @NotNull
    @NotEmpty
    @Column(name ="id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="color")
    public String getColor() {
        return color;
    }

    @Column(name="stripe")
    public int getStripe() {
        return stripe;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setStripe(int stripe) {
        this.stripe = stripe;
    }
}
