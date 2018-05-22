package com.apress.ejb.chapter07.entities;

import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.OrderItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(CustomerOrder.class)
public class CustomerOrder_ { 

    public static volatile ListAttribute<CustomerOrder, OrderItem> orderItemList;
    public static volatile SingularAttribute<CustomerOrder, Integer> id;
    public static volatile SingularAttribute<CustomerOrder, Date> creationDate;
    public static volatile SingularAttribute<CustomerOrder, Integer> version;
    public static volatile SingularAttribute<CustomerOrder, String> status;
    public static volatile SingularAttribute<CustomerOrder, Customer> customer;

}