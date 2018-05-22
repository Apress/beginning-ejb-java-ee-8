package com.apress.ejb.chapter07.entities;

import com.apress.ejb.chapter07.entities.Address;
import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.CustomerOrder;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(Customer.class)
public abstract class Customer_ extends BusinessContact_ {

    public static volatile ListAttribute<Customer, Address> shippingAddressList;
    public static volatile SingularAttribute<Customer, Address> defaultShippingAddress;
    public static volatile ListAttribute<Customer, CartItem> cartItemList;
    public static volatile ListAttribute<Customer, CustomerOrder> customerOrderList;
    public static volatile ListAttribute<Customer, Address> billingAddressList;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, Address> defaultBillingAddress;

}