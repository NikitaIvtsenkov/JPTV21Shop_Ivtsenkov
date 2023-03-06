/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pupil
 */
@Entity
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    @OneToOne
    private Customer customer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date takeOnProductDate;

    public History() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getReader() {
        return customer;
    }

    public void setReader(Customer customer) {
        this.customer = customer;
    }

    public Date getTakeOnBookDate() {
        return takeOnProductDate;
    }

    public void setTakeOnBookDate(Date takeOnProductDate) {
        this.takeOnProductDate = takeOnProductDate;
    }

    @Override
    public String toString() {
        return "History{" 
                + "id=" + id 
                + ", product=" + product.getName()
                + ", customer=" + customer.getFirstname() 
                +" " + customer.getLastname()
                + ", takeOnProductDate=" + takeOnProductDate 
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.product);
        hash = 47 * hash + Objects.hashCode(this.customer);
        hash = 47 * hash + Objects.hashCode(this.takeOnProductDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.takeOnProductDate, other.takeOnProductDate)) {
            return false;
        }
        return true;
    }

    

    
    
    
    
}
