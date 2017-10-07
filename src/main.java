public class main {
  public static void main(String[] args) {
    Comparable a = new Customer(4, "Mai lee", "0123456", "Langvong Hanoi");
    Comparable b = new Customer(4, "Aai lee", "0123456", "Langvong Hanoi");
    Comparable c = new Customer(4, "Bai lee", "0123456", "Langvong Hanoi");
    SortedSet s = new SortedSet();
    s.insert(a);
    s.insert(b);
    s.insert(c);
    System.out.printf(s.toString());
  }
}