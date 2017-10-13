package cz.eshop.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

    public Cart() {
    }

    public Cart(List<Produkt> products) {
        this.products = products;
    }

    private Integer id;
    private List<Produkt> products;
    private Integer idUser;
    private BigDecimal totalValue;

    public void addProduct(Produkt p) {
        products.add(p);
    }

    public void removeProduct(Produkt p) {
        int index = 0;
        for (Produkt prod : products) {
            if (prod.getId().equals(p.getId())){
                index = products.indexOf(prod);
            }
        }
        products.remove(index);
    }

    public BigDecimal calculateTotalValue() {
        BigDecimal total = new BigDecimal(0);
        for (Produkt prod : products) {
            total = total.add(prod.getPrice());
        }
        return total;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Produkt> getProducts() {
        return products;
    }

    public void setProducts(List<Produkt> products) {
        this.products = products;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getTotalValue() {
        return calculateTotalValue();
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

}
