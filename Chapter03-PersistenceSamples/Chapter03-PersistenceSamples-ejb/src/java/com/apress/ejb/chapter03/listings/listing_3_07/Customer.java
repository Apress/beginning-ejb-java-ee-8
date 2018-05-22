package com.apress.ejb.chapter03.listings.listing_3_07;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Listing_3_07_Customer")
@SequenceGenerator(name = "Listing_3_07_CustomerSequence", 
                   sequenceName = "LISTING_3_07_CUSTOMER_SEQ",
                   initialValue = 100, allocationSize = 20)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, 
                    generator = "Listing_3_07_CustomerSequence")
    private Integer id;
    private String name;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name;}
}
