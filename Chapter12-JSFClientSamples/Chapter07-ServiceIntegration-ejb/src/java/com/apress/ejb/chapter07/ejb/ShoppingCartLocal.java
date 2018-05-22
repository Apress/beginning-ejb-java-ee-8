package com.apress.ejb.chapter07.ejb;

import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.Wine;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Local
public interface ShoppingCartLocal {
  void addWineItem(Wine wine, int quantity);

  void addWineItem(Wine wine);

  Customer findCustomer(String email);

  String sendOrderToOPC();

  List<CartItem> getAllCartItems(Customer customer);

  /**
   * <
   * code>select o from CartItem o</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<CartItem> getCartItemFindAll();

  Customer getCustomer();

  /**
   * <
   * code>select object(o) from Wine o</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<Wine> getWineFindAll();

  /**
   * <
   * code>select object(wine) from Wine wine where wine.country = :country</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<Wine> getWineFindByCountry(String country);

  /**
   * <
   * code>select object(wine) from Wine wine where wine.varietal = :varietal</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<Wine> getWineFindByVarietal(String varietal);

  /**
   * <
   * code>select object(wine) from Wine wine where wine.year = :year</code>
   */
  @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
  List<Wine> getWineFindByYear(Integer year);

  <T> T mergeEntity(T entity);

  <T> T persistEntity(T entity);

  void removeCartItem(CartItem cartItem);

  void removeWine(Wine wine);

  void removeWineItem(CartItem cartItem);

  void setCustomer(Customer customer);
}
