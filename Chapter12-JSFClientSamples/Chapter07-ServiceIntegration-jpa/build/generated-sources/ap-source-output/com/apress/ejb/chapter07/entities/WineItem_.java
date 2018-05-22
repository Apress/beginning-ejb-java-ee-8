package com.apress.ejb.chapter07.entities;

import com.apress.ejb.chapter07.entities.Wine;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(WineItem.class)
public abstract class WineItem_ { 

    public static volatile SingularAttribute<WineItem, Integer> quantity;
    public static volatile SingularAttribute<WineItem, Integer> id;
    public static volatile SingularAttribute<WineItem, Integer> version;
    public static volatile SingularAttribute<WineItem, Wine> wine;

}