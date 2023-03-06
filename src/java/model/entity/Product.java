/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pupil
 */
@Entity
public class Product implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   @OneToMany
   private List<Manufacturer>manufacturers;
   private int expirationDate;
   private int quantity;
   private int count;

    public Product() {
        manufacturers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Manufacturer> getAuthors() {
        return manufacturers;
    }

    public void setmanufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public int getPublishedYear() {
        return expirationDate;
    }

    public void setPublishedYear(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" 
                + "id=" + id 
                + ", name=" + name 
                + ", manufacturers=" + Arrays.toString(manufacturers.toArray())
                + ", expirationDate=" + expirationDate 
                + ", quantity=" + quantity 
                + ", count=" + count 
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.manufacturers);
        hash = 13 * hash + this.expirationDate;
        hash = 13 * hash + this.quantity;
        hash = 13 * hash + this.count;
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
        final Product other = (Product) obj;
        if (this.expirationDate != other.expirationDate) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.manufacturers, other.manufacturers)) {
            return false;
        }
        return true;
    }
   
}
