package cz.eshop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Produkt {
    private Long id;
    private String type;
    @NotNull
    @Min(0)
    @Column(name = "price")
    private BigDecimal price;
    private String category;
    private String image;
    private Long orderId;

    public Produkt(Long id, String type, BigDecimal price, String category) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.category = category;
    }

    public Produkt(Long id, String type, BigDecimal price, String category, String image, Long orderId) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.category = category;
        this.image = image;
        this.orderId = orderId;
    }

    public Produkt() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "idOrder", nullable = true)
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    @NotNull
    @Size(max = 100)
    @Column(name = "type", nullable = false)
    @NotEmpty
    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @NotNull
    @Column(name = "image", nullable = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setCategory(String category) {
        this.category = category;
    }

}
