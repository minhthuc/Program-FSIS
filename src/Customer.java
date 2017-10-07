/**
 * @overview : Customers are people or organizations  who has some personal information such as id,
 *             name, phone, address
 * @attributes
 *  id           Integer
 *  name         String
 *  phoneNumber  String
 *  address      String
 *
 * @object
 *  A typical Customer is <pre>v = < id,name,phoneNumber,address ></pre>, where
 *    <pre>id(i), name(n), phoneNumber(n), address(a)</pre>
 * @abstract_properties
 *    mutable(id)=false /\ optional(id)=false /\min(id)=1/\max(id)=10^6
 *    mutable(name)= true/\ optional(name)=false/\length(name)= 50
 *    mutable(phoneNumber)= true/\ optional= false/\length(phoneNumber)=10
 *    mutable(address)= true/\ optional =false/\length(address)=100
 */
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
import utils.NotPossibleException;
public class Customer implements Comparable<Customer>{
  @DomainConstraint(type = "Integer", mutable = false, optional = false,
    min=1, max= 1000000)
  protected int id;

  @DomainConstraint(type = "String", mutable = true, optional = false,
    length = 50)
  protected String name;

  @DomainConstraint(type = "String", mutable = true, optional = false,
    length = 50)
  protected String phoneNumber;

  @DomainConstraint(type = "String", mutable = true, optional = false,
    length = 100)
  protected String address;

  /**
   * Constructor
   * @effect <pre>
   *            if id, name, phoneNumber, address are valid
   *              initialise this as Customer:<i,n,p,a>
   *            else
   *              throw Exception
   *          </pre>
   */
  public Customer(@AttrRef("id") int id, @AttrRef("address") String name,
    @AttrRef("phoneNumber") String phoneNumber,
    @AttrRef("address") String address)
    throws NotPossibleException {
      setId(id);
      setName(name);
      setAddress(address);
      setPhoneNumber(phoneNumber);
  }
  /** Constructor
   *
   * @effect <pre>
   *         initialise this as Customer:<>
   *         </pre>
   */
  public Customer() {
  }

  /** method
   *
   * @effect return <tt>this.id</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("id")
  public int getId() {
    return id;
  }

  /**
   *
   * @param id
   * @effect <pre>
   *           valid(id) ? this.id = id : @throws NotPossibleException
   *        </pre>
   *
   */
  @DOpt(type=OptType.Mutator) @AttrRef("id")
  public void setId(int id) throws NotPossibleException {
    if (validateID(id)) {
      this.id = id;
    } else throw new NotPossibleException("Your ID is" + id +
      "! The validate one must < 10^6 and > 1");
  }

  /** method
   *
   * @effect return <tt>this.name</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("name")
  public String getName() {
    return name;
  }

  /** method
   *
   * @param name
   * @effect <pre>
   *         valid(name) ? this.name = name : @throws NotPossibleException
   *        </pre>
   */
  @DOpt(type=OptType.Mutator) @AttrRef("name")
  public void setName(String name) throws NotPossibleException {
    if (validateName(name)) {
      this.name = name;
    } else
      throw new NotPossibleException("Your name length is" + name.length() + " is not valid" +
        "the valid one's length maximum is 50");
  }

  /** method
   *
   * @effect return <tt>this.phoneNumber</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("phoneNumber")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /** method
   *
   * @param phoneNumber
   * @effect <pre>
   *  valid(name) ? this.phoneNumber = phoneNumber : @throws NotPossibleException
   *        </pre>
   */
  @DOpt(type=OptType.Mutator) @AttrRef("phoneNumber")
  public void setPhoneNumber(String phoneNumber) throws NotPossibleException {
    if (validatephoneNumber(phoneNumber)) {
      this.phoneNumber = phoneNumber;
    } else
      throw new NotPossibleException("Your phone number's length is " + phoneNumber.length() +
        "is not valid! The valid one's length is 10");
  }

  /** method
   *
   * @effect return <tt>this.address</tt>
   */
  @DOpt(type=OptType.Observer) @AttrRef("address")
  public String getAddress() {
    return address;
  }

  /**
   *
   * @param address
   * @effect
   * <pre>
   *   valid(address) ? this.address = address : @throws NotPossibleException
   * </pre>
   */
  @DOpt(type=OptType.Mutator) @AttrRef("adrress")
  public void setAddress(String address) throws NotPossibleException {
    if (validateAddress(address)) {
      this.address = address;
    } else throw new NotPossibleException("Your address's length is " +
      address.length() + "not valid! The valid one's length maximum is 100");
  }

  /**
   *
   * @param id
   * @return valid(id) ? true : false
   */
  private boolean validateID(int id) {
    return id >= 1 && id <= 1000000;
  }

  /**
   *
   * @param name
   * @return valid(name) ? true : false
   */
  private boolean validateName(String name) {
    return name.length() <= 50;
  }

  /**
   *
   * @param number
   * @return valid(number) ? true : false
   */
  private boolean validatephoneNumber(String number) {
    return number != null || number.length() <= 10;
  }

  /**
   *
   * @param address
   * @return valid(address) ? true : false
   */
  private boolean validateAddress(String address) {
    return address.length() <= 100;
  }

  /**
   *
   * @param id
   * @param name
   * @param address
   * @param number
   * @return valid (id, name, address, number) ? true : false
   */
  private boolean validate(int id, String name, String address, String number) {
    return validateAddress(address) && validateID(id) &&
      validateName(name) && validatephoneNumber(number);
  }

  /**
   *
   * @return valid (this.id, this.name, this.address, this.phoneNumber) ?
   *            true : false
   */
  public boolean repOk(){
    return validate(id, name, address, phoneNumber);
  }

  /**
   *
   * @effect Customer's Description
   * @return Customer's id, Customer's name, Customer's phoneNumber and
   * Customer's address
   */
  public String toString() {
    return "Customer: ID:" + id + " Name: " + name + " phone Number: 0"
      + phoneNumber + " Address:" + address + "\n";
  }

  /**
   *
   * @param customer
   * @return this.name > customer.name ? 1 : -1
   */
  @Override
  public int compareTo(Customer customer) {
    return this.name.compareTo(customer.name);
  }
}
