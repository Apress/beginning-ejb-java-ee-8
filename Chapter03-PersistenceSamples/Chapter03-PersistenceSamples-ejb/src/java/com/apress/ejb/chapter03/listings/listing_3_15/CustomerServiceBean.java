package com.apress.ejb.chapter03.listings.listing_3_15;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@PersistenceContext(name="Chapter03PersistenceUnit", 
                    unitName="Chapter03PersistenceUnit")
public class CustomerServiceBean {
    @Resource 
    SessionContext ctx;
    
    public void performService() {
        EntityManager em = (EntityManager)ctx.lookup("Chapter03PersistenceUnit");
        // ...
    }  
}
