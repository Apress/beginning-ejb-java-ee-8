/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter09.ejb.singletable;

import com.apress.ejb.chapter09.singletable.Wine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class WineFacadeSingleTable extends AbstractFacadeSingleTable<Wine> {
  @PersistenceContext(unitName = "Chapter09-SingleTable-JTA")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public WineFacadeSingleTable() {
    super(Wine.class);
  }
}
