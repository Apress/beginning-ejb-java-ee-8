
package com.apress.ejb.chapter06.services.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.apress.ejb.chapter06.services.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreditCheck_QNAME = new QName("http://www.apress.com/ejb/credit", "CreditCheck");
    private final static QName _CreditCheckResponse_QNAME = new QName("http://www.apress.com/ejb/credit", "CreditCheckResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.apress.ejb.chapter06.services.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreditCheck }
     * 
     */
    public CreditCheck createCreditCheck() {
        return new CreditCheck();
    }

    /**
     * Create an instance of {@link CreditCheckResponse }
     * 
     */
    public CreditCheckResponse createCreditCheckResponse() {
        return new CreditCheckResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCheck }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.apress.com/ejb/credit", name = "CreditCheck")
    public JAXBElement<CreditCheck> createCreditCheck(CreditCheck value) {
        return new JAXBElement<CreditCheck>(_CreditCheck_QNAME, CreditCheck.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreditCheckResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.apress.com/ejb/credit", name = "CreditCheckResponse")
    public JAXBElement<CreditCheckResponse> createCreditCheckResponse(CreditCheckResponse value) {
        return new JAXBElement<CreditCheckResponse>(_CreditCheckResponse_QNAME, CreditCheckResponse.class, null, value);
    }

}
