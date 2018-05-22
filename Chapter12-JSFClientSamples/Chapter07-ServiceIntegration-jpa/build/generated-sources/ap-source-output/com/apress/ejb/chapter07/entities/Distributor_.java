package com.apress.ejb.chapter07.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(Distributor.class)
public class Distributor_ extends Customer_ {

    public static volatile SingularAttribute<Distributor, String> companyName;
    public static volatile SingularAttribute<Distributor, Integer> discount;
    public static volatile SingularAttribute<Distributor, String> memberStatus;

}