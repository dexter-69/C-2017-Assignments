/**
 * Create a class with an inner class that has a non-default constructor (one that takes arguments). Create a second
 * class with an inner class that inherits from the first inner class.
 */

/**
 * @author Balraj Singh
 */
class TestClassA {
    static class InnerTestClassA{
        InnerTestClassA(String someInput){}
    }
}

class TestClassB {
    class InnerTestClassB extends TestClassA.InnerTestClassA {
        public InnerTestClassB(String someInput) {
            super(someInput);
        }
    }
}

public class JavaAssignmentDay3_5 {

    //do nothing
    public static void main(String[] args) {}
}
