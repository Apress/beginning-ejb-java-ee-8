package com.apress.ejb.chapter03.entities;

import com.apress.ejb.chapter03.entities.Customer;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-13T13:28:00")
@StaticMetamodel(CustomerOrder.class)
public class CustomerOrder_ { 

    public static volatile SingularAttribute<CustomerOrder, BigDecimal> id;
    public static volatile SingularAttribute<CustomerOrder, Date> creationDate;
    public static volatile SingularAttribute<CustomerOrder, Integer> version;
    public static volatile SingularAttribute<CustomerOrder, String> status;
    public static volatile SingularAttribute<CustomerOrder, Customer> customer;

}