package com.apress.ejb.chapter07.ejb;

import com.apress.ejb.chapter07.entities.Wine;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Local
public interface SearchFacadeLocal {
  /**
   * <code>select object(o) from Wine o</code>
   */
  List<Wine> getWineFindAll();

  /**
   * <code>select object(wine) from Wine wine where wine.country = :country</code>
   */
  List<Wine> getWineFindByCountry(String country);

  /**
   * <code>select object(wine) from Wine wine where wine.varietal = :varietal</code>
   */
  List<Wine> getWineFindByVarietal(String varietal);

  /**
   * <code>select object(wine) from Wine wine where wine.year = :year</code>
   */
  List<Wine> getWineFindByYear(Integer year);

  <T> T mergeEntity(T entity);

  <T> T persistEntity(T entity);

  Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

  void removeWine(Wine wine);
}
