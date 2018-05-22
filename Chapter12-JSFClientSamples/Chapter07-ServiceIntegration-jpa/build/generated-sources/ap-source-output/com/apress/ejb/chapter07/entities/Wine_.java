package com.apress.ejb.chapter07.entities;

import com.apress.ejb.chapter07.entities.Supplier;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-16T11:06:36")
@StaticMetamodel(Wine.class)
public class Wine_ { 

    public static volatile SingularAttribute<Wine, String> country;
    public static volatile SingularAttribute<Wine, Integer> year;
    public static volatile SingularAttribute<Wine, String> name;
    public static volatile SingularAttribute<Wine, Integer> rating;
    public static volatile SingularAttribute<Wine, String> description;
    public static volatile SingularAttribute<Wine, String> varietal;
    public static volatile SingularAttribute<Wine, Integer> id;
    public static volatile SingularAttribute<Wine, String> region;
    public static volatile ListAttribute<Wine, Supplier> supplierList;
    public static volatile SingularAttribute<Wine, Float> retailPrice;
    public static volatile SingularAttribute<Wine, Integer> version;

}