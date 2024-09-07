package com.d288.idris.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "divisions")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Division_ID")
    private Long id;

    @Column(name = "Division")
    private String division_name;
    @Column(name = "Country_ID")
    private Long country_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Country_ID", insertable = false, updatable = false)
    private Country country;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Customer> customers;
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

    public String getDivision_name() {
        return division_name;
    }

    public void setDivision_name(String division_name) {
        this.division_name = division_name;
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

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
        if (country != null) {
            this.country_id = country.getId();
        } else {
            this.country_id = null;
        }
    }

    // Getter for countryName derived from the associated Country entity
    public String getCountryName() {
        return country != null ? country.getCountry_name() : null;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
