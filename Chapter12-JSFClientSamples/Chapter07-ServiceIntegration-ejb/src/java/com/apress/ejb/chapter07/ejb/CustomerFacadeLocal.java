package com.apress.ejb.chapter07.ejb;

import com.apress.ejb.chapter07.entities.Customer;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CustomerFacadeLocal
{
  public Object queryByRange(String jpqlStmt, int firstResult, int maxResults);
  public <T> T persistEntity(T entity);
  public <T> T mergeEntity(T entity);
  public void removeCustomer(Customer customer);
  public List<Customer> getCustomerFindAll();
  public Customer getCustomerFindById(BigDecimal id);
  public Customer getCustomerFindByEmail(String email);
  public Customer registerCustomer(Customer customer);
}
