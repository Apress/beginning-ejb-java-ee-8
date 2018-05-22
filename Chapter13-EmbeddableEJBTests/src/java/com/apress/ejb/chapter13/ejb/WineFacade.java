package com.apress.ejb.chapter13.ejb;

import com.apress.ejb.chapter07.entities.Wine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class WineFacade extends AbstractFacade<Wine> {
  @PersistenceContext(unitName = "Chapter13-EmbeddableEJBTests-JTA")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public WineFacade() {
    super(Wine.class);
  }
  
  public List<Wine> getWineFindByYear(int year) {
    return em.createNamedQuery("Wine.findByYear").setParameter("year", year).getResultList();
  }
}
