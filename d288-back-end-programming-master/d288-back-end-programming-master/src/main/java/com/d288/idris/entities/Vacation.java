package com.d288.idris.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "vacations")
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vacation_ID")
    private Long id;

    @Column(name = "Vacation_Title")
    private String vacation_title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Travel_Fare_Price")
    private BigDecimal travel_price;

    @Column(name = "Image_URL")
    private String image_URL;
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

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Excursion> excursions;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVacation_title() {
        return vacation_title;
    }

    public void setVacation_title(String vacation_title) {
        this.vacation_title = vacation_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTravel_price() {
        return travel_price;
    }

    public void setTravel_price(BigDecimal travel_price) {
        this.travel_price = travel_price;
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

    public Set<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(Set<Excursion> excursions) {
        this.excursions = excursions;
    }
}

