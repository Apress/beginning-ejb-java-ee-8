package com.apress.ejb.chapter13.ejb;

import com.apress.ejb.chapter07.entities.Address;
import com.apress.ejb.chapter07.entities.CartItem;
import com.apress.ejb.chapter07.entities.Customer;
import com.apress.ejb.chapter07.entities.CustomerOrder;
import com.apress.ejb.chapter07.entities.Individual;
import com.apress.ejb.chapter07.entities.OrderItem;
import com.apress.ejb.chapter07.entities.Wine;
import java.io.PrintWriter;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import org.apache.derby.drda.NetworkServerControl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class WineAppServiceTest {
  private static EJBContainer ejbContainer;
  private static NetworkServerControl derbyServer;

  public WineAppServiceTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
    PrintWriter pw = new PrintWriter(System.out);

    //  Start the Derby Database server, waiting until it is responsive
    //  before continuing
    try {
      derbyServer = new org.apache.derby.drda.NetworkServerControl();
      derbyServer.start(pw);
      int i = 50;
      while (--i > 0) {
        try {
          derbyServer.ping();
          break;
        } catch (Exception ex) {
          System.out.println("Derby Server started; waiting for response...");
        }
        Thread.sleep(100);
      }
    } finally {
      pw.close();
    }

    //  Instantiate an Embeddable EJB Container
    ejbContainer = javax.ejb.embeddable.EJBContainer.createEJBContainer();
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
    //  Close the Embeddable EJB Container, releasing all resources
    ejbContainer.close();

    //  Shutdown the Derby Database server
    derbyServer.shutdown();
  }

  @Before
  public void setUp() {
    //  Inititalize the data in the domain model
    PopulateDemoData.resetData("Chapter13-EmbeddableEJBTests-ResourceLocal", System.out);
  }

  @After
  public void tearDown() {
  }

  /**
   * Test findCustomerByEmail on WineAppService.
   *
   * Assert that the Customer returned is named "James Brown".
   *
   * @throws Exception
   */
  @Test
  public void testFindCustomerByEmail() throws Exception {
    System.out.println("findCustomerByEmail");
    WineAppService wineAppSvcFacade = 
        (WineAppService) ejbContainer.getContext().lookup("java:global/classes/WineAppService");
    Customer customer = 
        wineAppSvcFacade.findCustomerByEmail(PopulateDemoData.TO_EMAIL_ADDRESS);
    assertEquals("WineAppServiceFacade.findCustomerByEmail(): Checking customer name", 
        "James Brown", 
        customer.getFirstName() + " " + customer.getLastName());
  }

  /**
   * Test createIndividual() on WineAppService and findCustomerByEmail() on
   * CustomerFacade.
   *
   * Assert that the Individual instance created in createIndividual() has the
   * expected email property. 
   * Assert that the Customer retrieved in
   * findCustomerByEmail() has the expected name.
   * Assert that the shippingAddress property is in a managed state after merge
   */
  @Test
  public void testCreateIndividual() throws Exception {
    System.out.println("createIndividual");
    WineAppService wineAppSvcFacade = 
        (WineAppService) ejbContainer.getContext().lookup("java:global/classes/WineAppService");
    String email = "drwho@yahoo.com";
    Individual individual = 
        wineAppSvcFacade.createIndividual("Adam", "Beyda", email);
    assertEquals("WineAppServiceFacade.createIndividual(): Checking Individual.email prop",
        email, individual.getEmail());
    
    CustomerFacade custFacade = 
        (CustomerFacade) ejbContainer.getContext().lookup("java:global/classes/CustomerFacade");
    Customer customer = custFacade.findCustomerByEmail(email);
    assertEquals("CustomerFacade.findCustomerByEmail(): Checking Customer.email prop",
        "Adam Beyda", customer.getFirstName() + " " + customer.getLastName());

    //  Managed/detached entity state check
    Address shippingAddress = new Address("San Mateo", null, null, null, null);
    customer.setDefaultShippingAddress(shippingAddress);
    customer = custFacade.merge(customer);
    assertNotNull("customer.getDefaultShippingAddress().getId() is null",
        customer.getDefaultShippingAddress().getId());
    assertNotNull("shippingAddress.getId() is null",
        shippingAddress.getId());
  }

  /**
   * Test createIndividual() and createCustomerOrder() on WineAppService,
   * getWineFindByYear() on WineFacade, and merge() on CustomerFacade.
   *
   * Assert that the total value of the created order is 110.
   * Assert that the customerOrder and customer objects are in a managed state
   */
  @Test
  public void testCreateCustomerOrder() throws Exception {
    System.out.println("createCustomerOrder");
    Context context = ejbContainer.getContext();
    WineAppService wineAppSvcFacade = 
        (WineAppService) context.lookup("java:global/classes/WineAppService");
    WineFacade wineFacade = 
        (WineFacade) context.lookup("java:global/classes/WineFacade");
    CustomerFacade custFacade = 
        (CustomerFacade) context.lookup("java:global/classes/CustomerFacade");

    //  Add CartItems to the Customer's cart and merge the customer changes
    final String email = "drwho@yahoo.com";
    Customer customer = wineAppSvcFacade.createIndividual("Adam", "Beyda", email);
    for (Wine wine : wineFacade.getWineFindByYear(2005)) {
      customer.addCartItem(new CartItem(10, wine));
    }
    customer = custFacade.merge(customer);

    CustomerOrder customerOrder = wineAppSvcFacade.createCustomerOrder(customer);
    Float total = new Float(0);
    for (OrderItem orderItem : customerOrder.getOrderItemList()) {
      total += orderItem.getQuantity() * orderItem.getPrice();
    }
    assertEquals("Checking that customer order totals $270", total, new Float(270));

    //  Query the latest state of our customer from the persistence context
    //  (using a new transaction, courtesy CMT) and check whether it contains a 
    //  customer order with a populated 'id' field
    CustomerOrder customerOrder1 = 
        wineAppSvcFacade.findCustomerByEmail(email).getCustomerOrderList().get(0);
    assertNotNull("customerOrder1.getId() is null", customerOrder1.getId());

    //  Check whether our original customer order has had its 'id' field auto-populated
    assertNotNull("customerOrder.getId() is null", customerOrder.getId());

    //  Check whether the customer order referenced by our customer
    //  has had its 'id' field auto-populated
    CustomerOrder customerOrder2 = customer.getCustomerOrderList().get(0);
    assertNotNull("customerOrder2.getId() is null", customerOrder2.getId());
  }
}