package com.d288.idris.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cart_Item_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Vacation_ID")
    private Vacation vacation;

    @ManyToMany
    @JoinTable(
            name = "excursion_cartitem",
            joinColumns = @JoinColumn(name = "Cart_Item_ID"),
            inverseJoinColumns = @JoinColumn(name = "Excursion_ID")
    )
    private Set<Excursion> excursions;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

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

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public Set<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(Set<Excursion> excursions) {
        this.excursions = excursions;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
    public void printCartItem() {
        System.out.println("CartItem{" +
                "id=" + id +
                ", vacation=" + vacation +
                ", excursions=" + excursions +
                ", cart=" + cart +
                ", create_date=" + create_date +
                ", last_update=" + last_update +
                '}');
    }
}
