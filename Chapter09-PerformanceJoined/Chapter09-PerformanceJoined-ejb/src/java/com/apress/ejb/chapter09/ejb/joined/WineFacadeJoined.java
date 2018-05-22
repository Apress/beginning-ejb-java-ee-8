/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter09.ejb.joined;

import com.apress.ejb.chapter09.joined.Wine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class WineFacadeJoined extends AbstractFacadeJoined<Wine> {
  @PersistenceContext(unitName = "Chapter09-Joined-JTA")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public WineFacadeJoined() {
    super(Wine.class);
  }
}
