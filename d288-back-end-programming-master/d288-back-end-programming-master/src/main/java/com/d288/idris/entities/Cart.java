package com.d288.idris.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cart_ID")
    private Long id;

    @Column(name = "Order_Tracking_Number")
    private String orderTrackingNumber;

    @Column(name = "Package_Price")
    private BigDecimal package_price;

    @Column(name = "Party_Size")
    private int party_size;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private StatusType status;

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
    @ManyToOne
    @JoinColumn(name = "Customer_ID")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItem = new HashSet<>();;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public BigDecimal getPackage_price() {
        return package_price;
    }

    public void setPackage_price(BigDecimal package_price) {
        this.package_price = package_price;
    }

    public int getParty_size() {
        return party_size;
    }

    public void setParty_size(int party_size) {
        this.party_size = party_size;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItems(Set<CartItem> cartItem) {
        this.cartItem = cartItem;
    }
    //Method
    // Method to add a CartItem and update the cart
    public void addCartItem(CartItem item) {
        this.cartItem.add(item);
        item.setCart(this);

    }
    public void printCart() {
        System.out.println("Cart{" +
                "id=" + id +
                ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
                ", package_price=" + package_price +
                ", party_size=" + party_size +
                ", status=" + status +
                ", create_date=" + create_date +
                ", last_update=" + last_update +
                ", customer=" + customer +
                ", cartItem=" + cartItem +
                '}');
    }
}
