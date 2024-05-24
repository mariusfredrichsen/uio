
class Kodeanalyse {
    public static void main(String[] args) {
        X x1 = (X) new Y();
        Y y1 = new Y();
        // Y y2 = new X();
        Y y3 = new Z();
        Z z1 = new Z();
        // Z z2 = (Z) new X();


        S s = new B();

        B b = (B) s;

        System.out.println(s instanceof S);
        
        // System.out.println(q instanceof B); // false
        // System.out.println(q instanceof G); // true
        // System.out.println(q instanceof S); // true
        // System.out.println(b instanceof B); // true
        // System.out.println(b instanceof S); // true
        // System.out.println(s instanceof B); // true
        // System.out.println(g instanceof Q); // false
        // System.out.println(g instanceof S); // true
        // System.out.println(p instanceof Q); // false
        // System.out.println(p instanceof S); // true
        // System.out.println(p instanceof G); // false
    }

    // false
    // false
    // true
    // false
    // false
    // false
    // true
    // false
    // false
    // false
}