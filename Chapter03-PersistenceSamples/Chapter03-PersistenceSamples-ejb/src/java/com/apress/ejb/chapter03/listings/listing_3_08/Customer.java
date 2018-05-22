package com.apress.ejb.chapter03.listings.listing_3_08;

import com.apress.ejb.chapter03.listings.listing_3_09.CustomerPK;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "Listing_3_08_Customer")
@IdClass(CustomerPK.class)
public class Customer implements Serializable {
    @Id
    private Integer customerId;
    @Id
    private String name;

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
}
