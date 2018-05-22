package com.apress.ejb.chapter07.entities;

import com.apress.ejb.chapter07.entities.Customer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(CartItem.class)
public class CartItem_ extends WineItem_ {

    public static volatile SingularAttribute<CartItem, Date> createdDate;
    public static volatile SingularAttribute<CartItem, Customer> customer;

}