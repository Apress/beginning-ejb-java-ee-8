package com.apress.ejb.chapter04.embeddedid;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Person: A concrete entity that uses an @EmbeddedId and an @Embedded field
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Person.findAll", query = "select o from Person o")})
@Table(name = "CH04_EMBID_PERSON")
public class Person implements Serializable {
  @SuppressWarnings("compatibility:-7074714881275658754")
  private static final long serialVersionUID = 5291172566067954515L;
  @EmbeddedId
  private MyIdClass myId = new MyIdClass();
  @Embedded
  private Address homeAddress;
  @Version
  private Integer version;

  public Person() {
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Address getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(Address homeAddress) {
    this.homeAddress = homeAddress;
  }

  /**
   * @return the myId
   */
  public MyIdClass getMyId() {
    return myId;
  }

  /**
   * @param myId the myId to set
   */
  public void setMyId(MyIdClass myId) {
    this.myId = myId;
  }
}