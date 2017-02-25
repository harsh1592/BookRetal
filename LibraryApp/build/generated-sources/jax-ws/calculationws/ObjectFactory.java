
package calculationws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the calculationws package. 
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

    private final static QName _GetInfoByYear_QNAME = new QName("http://CalculationWS/", "getInfoByYear");
    private final static QName _RentBookResponse_QNAME = new QName("http://CalculationWS/", "rentBookResponse");
    private final static QName _GetInfoByYearResponse_QNAME = new QName("http://CalculationWS/", "getInfoByYearResponse");
    private final static QName _RentBook_QNAME = new QName("http://CalculationWS/", "rentBook");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: calculationws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RentBook }
     * 
     */
    public RentBook createRentBook() {
        return new RentBook();
    }

    /**
     * Create an instance of {@link GetInfoByYearResponse }
     * 
     */
    public GetInfoByYearResponse createGetInfoByYearResponse() {
        return new GetInfoByYearResponse();
    }

    /**
     * Create an instance of {@link RentBookResponse }
     * 
     */
    public RentBookResponse createRentBookResponse() {
        return new RentBookResponse();
    }

    /**
     * Create an instance of {@link GetInfoByYear }
     * 
     */
    public GetInfoByYear createGetInfoByYear() {
        return new GetInfoByYear();
    }

    /**
     * Create an instance of {@link BookInfo }
     * 
     */
    public BookInfo createBookInfo() {
        return new BookInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoByYear }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CalculationWS/", name = "getInfoByYear")
    public JAXBElement<GetInfoByYear> createGetInfoByYear(GetInfoByYear value) {
        return new JAXBElement<GetInfoByYear>(_GetInfoByYear_QNAME, GetInfoByYear.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CalculationWS/", name = "rentBookResponse")
    public JAXBElement<RentBookResponse> createRentBookResponse(RentBookResponse value) {
        return new JAXBElement<RentBookResponse>(_RentBookResponse_QNAME, RentBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoByYearResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CalculationWS/", name = "getInfoByYearResponse")
    public JAXBElement<GetInfoByYearResponse> createGetInfoByYearResponse(GetInfoByYearResponse value) {
        return new JAXBElement<GetInfoByYearResponse>(_GetInfoByYearResponse_QNAME, GetInfoByYearResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RentBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CalculationWS/", name = "rentBook")
    public JAXBElement<RentBook> createRentBook(RentBook value) {
        return new JAXBElement<RentBook>(_RentBook_QNAME, RentBook.class, null, value);
    }

}
