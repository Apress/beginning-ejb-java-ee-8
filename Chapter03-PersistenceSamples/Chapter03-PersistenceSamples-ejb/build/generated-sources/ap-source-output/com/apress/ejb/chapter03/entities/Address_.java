package com.apress.ejb.chapter03.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-13T13:28:00")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, Integer> zipCode;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> street1;
    public static volatile SingularAttribute<Address, BigDecimal> id;
    public static volatile SingularAttribute<Address, String> state;
    public static volatile SingularAttribute<Address, String> street2;
    public static volatile SingularAttribute<Address, Integer> version;

}