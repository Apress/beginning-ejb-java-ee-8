package com.apress.ejb.chapter07.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> zipCode;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> street1;
    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, String> state;
    public static volatile SingularAttribute<Address, String> street2;
    public static volatile SingularAttribute<Address, Integer> version;

}