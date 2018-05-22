package com.apress.ejb.chapter07.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(BusinessContact.class)
public class BusinessContact_ { 

    public static volatile SingularAttribute<BusinessContact, String> firstName;
    public static volatile SingularAttribute<BusinessContact, String> lastName;
    public static volatile SingularAttribute<BusinessContact, String> phone;
    public static volatile SingularAttribute<BusinessContact, Integer> id;
    public static volatile SingularAttribute<BusinessContact, Integer> version;

}