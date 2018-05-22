package com.apress.ejb.chapter07.ejb;

import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Remote
public interface SearchFacade
{
  /**
   * <
   * code>select object(o) from Wine o</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<com.apress.ejb.chapter07.entities.Wine> getWineFindAll();

  /**
   * <
   * code>select object(wine) from Wine wine where wine.country = :country</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<com.apress.ejb.chapter07.entities.Wine> getWineFindByCountry(String country);

  /**
   * <
   * code>select object(wine) from Wine wine where wine.varietal = :varietal</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<com.apress.ejb.chapter07.entities.Wine> getWineFindByVarietal(String varietal);

  /**
   * <
   * code>select object(wine) from Wine wine where wine.year = :year</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<com.apress.ejb.chapter07.entities.Wine> getWineFindByYear(Integer year);

  <T> T mergeEntity(T entity);

  <T> T persistEntity(T entity);

  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

  void removeWine(com.apress.ejb.chapter07.entities.Wine wine);

  String sendOrderToOPC();
}
