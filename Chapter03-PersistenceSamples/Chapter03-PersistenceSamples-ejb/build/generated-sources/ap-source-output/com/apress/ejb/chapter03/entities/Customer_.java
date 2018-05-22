package com.apress.ejb.chapter03.entities;

import com.apress.ejb.chapter03.entities.Address;
import com.apress.ejb.chapter03.entities.CustomerOrder;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-13T13:28:00")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile ListAttribute<Customer, CustomerOrder> customerOrders;
    public static volatile SingularAttribute<Customer, Address> shippingAddress;
    public static volatile SingularAttribute<Customer, BigDecimal> id;
    public static volatile SingularAttribute<Customer, Address> billingAddress;
    public static volatile SingularAttribute<Customer, Integer> version;
    public static volatile SingularAttribute<Customer, String> email;

}