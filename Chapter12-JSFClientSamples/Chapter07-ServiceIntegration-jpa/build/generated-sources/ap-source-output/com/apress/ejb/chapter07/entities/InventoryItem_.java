package com.apress.ejb.chapter07.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(InventoryItem.class)
public class InventoryItem_ extends WineItem_ {

    public static volatile SingularAttribute<InventoryItem, Date> stockDate;
    public static volatile SingularAttribute<InventoryItem, Float> wholesalePrice;

}