import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;

import java.util.*;

/**
 * sorted sets of Comparable objects
 * the objects are sorted in the ascending order based on the result of comparing them using the method Comparable.compareTo
 * s: SortedSet
 * o1, o2: Comparable
 * y = min(o1, o2)
 * x = max(o1, o2): o1
 * o2 ∈ s ↔ x ∉ subset(s, first(s), y)
 */
public class SortedSet {

  @DomainConstraint(type = "ArrayList<Comparable>", mutable = true, optional = false)
  private List<Comparable> elements;

  /**
   * Constructor
   *  initialise this as SortedSet
   */
  public SortedSet(){
    elements = new ArrayList<>();
  }

  /**
   * Constructor
   * @param customer
   * @effect
   *  <pre>
   *    if SortedSet contain that Comparable
   *      throw error
   *    else
   *      add that Customer to SortedSet
   *    then
   *      Sort them base on which implemented
   *  </pre>
   */
  public void insert(@AttrRef("Customer") Comparable customer){
    if (lookup(customer)) {
      System.err.print("Customer is exist");
    } else {
      elements.add((Customer)customer);
    }
    Collections.sort(elements);
  }

  /**
   *
   * @return SortedSet's size
   */
  public int size(){
    return elements.size();
  }

  /**
   *
   * @param customer
   * @effect
   *  <pre>
   *   Is Comparable exists in SortedSet?
   *  </pre>
   *  @return boolean
   */
  public boolean lookup(@AttrRef("Customer") Comparable customer){
    return elements.contains(customer);
  }

  /**
   *
   * @return List of elements in SortedSet that sorted
   */
  public String toString(){
    String rs = "";
    for (Comparable a : elements){
      rs += a.toString();
    }
    return rs;
  }

  /**
   * @effect
   *  <pre>
   *    if SortedSet does not contain any element
   *      throw Exception that is empty
   *    else @return new class SortedGen
   *  </pre>
   */
  @DOpt(type= OptType.ObserverIterator)
  public Iterator<Customer> iterator(){
    if (this.size()==0){
      throw new EmptyStackException();
    }else return new SortedSetGen();
  }

  /**
   * @effects   <pre>
   *              if this is validated
   *                return true
   *              else
   *                return false
   *            </pre>
   */
  public boolean repOK() {
    if (elements == null)
      return false;

    for (int i = 0; i < elements.size(); i++) {
      Object c = elements.get(i);

      if (!(c instanceof Comparable)) return false;

      for (int j = i + 1; j < elements.size(); j++) {
        if (elements.get(j).equals(c))
          return false;
      }
    }
    return true;
  }

  /**
   * @effects <pre>
   *            if no element is found
   *            else
   *              returns the found object
   * </pre>
   **/
  public boolean isIn(Comparable c) {
    if (elements.indexOf(c) > 0) {
      return true;
    }
    return false;
  }

  private class SortedSetGen implements Iterator<Customer>{
    private int index;

    @Override
    public boolean hasNext() {
      if (index < elements.size()){
        return true;
      }else return false;
    }

    @Override
    public Customer next() {
      if (hasNext()){
        Customer next = (Customer) elements.get(index);
        index++;
        return next;
      }else throw new NoSuchElementException();
    }
  }
}
