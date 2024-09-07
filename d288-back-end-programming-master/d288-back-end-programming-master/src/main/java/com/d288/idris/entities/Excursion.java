package com.d288.idris.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "excursions")
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Excursion_ID")
    private Long id;

    @Column(name = "Excursion_Title")
    private String excursion_title;

    @Column(name = "Excursion_Price")
    private BigDecimal excursion_price;

    @Column(name = "Image_URL")
    private String image_URL;
    @ManyToOne
    @JoinColumn(name = "Vacation_ID")
    private Vacation vacation;

    @ManyToMany(mappedBy = "excursions")
    private Set<CartItem> cartItems;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", updatable = false)
    private Date create_date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Last_Update")
    private Date last_update;

    @PrePersist
    protected void onCreate() {
        create_date = new Date();
        last_update = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        last_update = new Date();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExcursion_title() {
        return excursion_title;
    }

    public void setExcursion_title(String excursion_title) {
        this.excursion_title = excursion_title;
    }

    public BigDecimal getExcursion_price() {
        return excursion_price;
    }

    public void setExcursion_price(BigDecimal excursion_price) {
        this.excursion_price = excursion_price;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
