
class Kodeanalyse {
    public static void main(String[] args) {
        X x1 = (X) new Y();
        Y y1 = new Y();
        // Y y2 = new X();
        Y y3 = new Z();
        Z z1 = new Z();
        // Z z2 = (Z) new X();


        S q = new G();
        Q b = new Q();
        B s = new Q();
        B g = new B();
        S p = new B();
        
        System.out.println(q instanceof B); // flase
        System.out.println(q instanceof G); // false -
        System.out.println(q instanceof S); // true
        System.out.println(b instanceof B); // true
        System.out.println(b instanceof S); // true
        System.out.println(s instanceof B); // true
        System.out.println(g instanceof Q); // false
        System.out.println(g instanceof S); // true
        System.out.println(p instanceof Q); // false
        System.out.println(p instanceof S); // true
        System.out.println(p instanceof G); // false
    }
}