package com.apress.ejb.chapter07.entities;

import com.apress.ejb.chapter07.entities.CustomerOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(OrderItem.class)
public class OrderItem_ extends WineItem_ {

    public static volatile SingularAttribute<OrderItem, Float> price;
    public static volatile SingularAttribute<OrderItem, CustomerOrder> customerOrder;
    public static volatile SingularAttribute<OrderItem, Date> shipDate;
    public static volatile SingularAttribute<OrderItem, Date> orderDate;
    public static volatile SingularAttribute<OrderItem, String> status;

}