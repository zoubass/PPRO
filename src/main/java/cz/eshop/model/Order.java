package cz.eshop.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "order_info")
public class Order {

    private Long id;

    private List<Produkt> products;

    @NotNull
    @Max(999999)
    private BigDecimal totalPrice;

    @NotNull
    private Integer totalProductCount;

    @NotNull(message = "Zadejte prosím způsob platby")
    private PaymentType orderType;

    @NotNull(message = "Zadejte prosím Email, pro možnost kontaktovat Vás")
    @NotEmpty
    @Email
    private String userEmail;

    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    private String street;
    @NotNull(message = "Pole psc je povinné.")
    private Integer psc;
    @NotNull
    private DeliveryType deliveryType;

    public Order(BigDecimal totalPrice, Integer totalProductCount) {
        this.totalPrice = totalPrice;
        this.totalProductCount = totalProductCount;
    }

    public Order() {

    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducts(List<Produkt> products) {
        this.products = products;
    }

    @Column(name = "totalPrice", nullable = false)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "totalProductCount", nullable = false)
    public Integer getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(Integer totalProductCount) {
        this.totalProductCount = totalProductCount;
    }


    @Column(name = "orderType", nullable = false)
    public PaymentType getOrderType() {
        return orderType;
    }

    public void setOrderType(PaymentType orderType) {
        this.orderType = orderType;
    }

    @Column(name = "userEmail")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Column(name = "deliveryType", nullable = false)
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "street", nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "psc", nullable = false)
    public Integer getPsc() {
        return psc;
    }

    public void setPsc(Integer psc) {
        this.psc = psc;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }
}
