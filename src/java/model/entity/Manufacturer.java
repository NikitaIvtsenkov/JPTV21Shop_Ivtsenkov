/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author pupil
 */
@Entity
public class Manufacturer implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namemanufacturer;
    private String brand;
    @OneToMany
    private List<Product>products;

    public Manufacturer() {
        products = new ArrayList<>();
    }

    public String getFirstname() {
        return namemanufacturer;
    }

    public void setFirstname(String firstname) {
        this.namemanufacturer = namemanufacturer;
    }

    public String getLastname() {
        return brand;
    }

    public void setLastname(String lastname) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Author{" 
                + "id=" + id 
                + ", name=" + namemanufacturer 
                + ", brand=" + brand 
                + '}';
    }

    public List<Product> getBooks() {
        return products;
    }

    public void setBooks(List<Product> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.namemanufacturer);
        hash = 23 * hash + Objects.hashCode(this.brand);
        hash = 23 * hash + Objects.hashCode(this.products);
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
        final Manufacturer other = (Manufacturer) obj;
        if (!Objects.equals(this.namemanufacturer, other.namemanufacturer)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.products, other.products)) {
            return false;
        }
        return true;
    }

   
    
}
