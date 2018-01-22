package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="tariffs")
public class Tariff {
    private String type;
    private int price;
    private Date validity;
    private int entrysCount;
    private boolean timeTicket;

    @Id
    @NotNull
    @NotEmpty
    @Column(name = "type")
    public String getType() {
        return type;
    }

    @NotEmpty
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    @NotEmpty
    @Column(name = "validity")
    public Date getValidity() {
        return validity;
    }

    @NotEmpty
    @Column(name = "entrysCount")
    public int getEntrysCount() {
        return entrysCount;
    }

    @NotEmpty
    @NotNull
    @Column(name = "timeTicket")
    public boolean isTimeTicket() {
        return timeTicket;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public void setEntrysCount(int entrysCount) {
        this.entrysCount = entrysCount;
    }

    public void setTimeTicket(boolean timeTicket) {
        this.timeTicket = timeTicket;
    }
}
