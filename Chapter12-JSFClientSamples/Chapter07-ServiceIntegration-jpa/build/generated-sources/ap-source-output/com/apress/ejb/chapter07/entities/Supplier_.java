package com.apress.ejb.chapter07.entities;

import com.apress.ejb.chapter07.entities.Address;
import com.apress.ejb.chapter07.entities.Wine;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(Supplier.class)
public class Supplier_ extends BusinessContact_ {

    public static volatile SingularAttribute<Supplier, Address> paymentAddress;
    public static volatile ListAttribute<Supplier, Wine> wineList;

}