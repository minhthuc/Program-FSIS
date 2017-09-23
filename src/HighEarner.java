import utils.NotPossibleException;
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
/**
 * @overview : The HighEarner represents wealthy customers whose income are
 *             above a given threshold.
 * @abstract_properties :
 *           mutable(id)= false /\ optional(id)=false/\min(id)=10^7
 *           mutable(income)=true /\ optional(income)=false/\min(income)=10^7

 */
public class HighEarner extends Customer {
  @DomainConstraint(type = "float", mutable = true, optional = false,
    min = 10000000)
  private Float income;

  /**
   * Constructor
   * @effect <pre>
   *            if id, name, phoneNumber, address, income are valid
   *              initialise this as HighEarner:<id, name, phoneNumber,
   *              address, income>
   *            else
   *              throw Exception
   *          </pre>
   */
  public HighEarner(@AttrRef("id") int id, @AttrRef("name") String name,
                    @AttrRef("phoneNumber") String phoneNumber,
                    @AttrRef("address") String address,
                    @AttrRef("income") float income) throws
    NotPossibleException {
    super(id, name, phoneNumber, address);
    setIncome(income);
  }

  /** Constructor
   *
   * @effect <pre>
   *         initialise this as HighEarner:<>
   *         </pre>
   */
  public HighEarner() {
  }

  /** method
   *
   * @return this.income
   */
  @DOpt(type=OptType.Observer) @AttrRef("income")
  public Float getIncome() {
    return income;
  }

  /**
   *
   * @param income
   * @return valid(income) ? true : false
   */
  private boolean validateIncome(float income){
    return income > 10000000;
  }

  /**
   *
   * @param income
   * @effect
   *  <pre>
   *    valid(income) ? this.income = income : @throws NotPossibleException
   *  </pre>
   */
  @DOpt(type=OptType.Mutator) @AttrRef("income")
  public void setIncome(Float income) throws NotPossibleException {
    if (validateIncome(income)){this.income = income;}
    else throw new NotPossibleException("Your income is "+ income+" not valid! The valid"
      +"one must greater than 10.000.000");
  }

  /**
   *
   * @effect HighEarner's Description
   * @return HighEarner's id, HighEarner's name, HighEarner's phoneNumber and
   * HighEarner's address
   */
  public String toString(){
    return ": ID:" + id + " Name: " + name + " phone Number: "
      + phoneNumber + " Address:" + address + "and Income is " + income;
  }
}
