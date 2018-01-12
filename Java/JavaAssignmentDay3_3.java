/**
 * Create three interfaces, each with two methods. Inherit a new interface that combines the three, adding a new method.
 * Create a class by implementing the new interface and also inheriting from a concrete class. Now write four methods,
 * each of which takes one of the four interfaces as an argument. In main( ), create an object of your class and pass
 * it to each of the methods.
 */

/**
 * @author Balraj Singh
 */
interface A {
    void one();

    void two();
}

interface B {
    void three();

    void four();
}

interface C {
    void five();

    void six();
}

interface D extends A, B, C {
    void seven();
}


class ConcreteClass implements A {

    @Override
    public void one() {
        System.out.println("one_concrete_class");
    }

    @Override
    public void two() {
        System.out.println("two_concrete_class");
    }
}

public class JavaAssignmentDay3_3 extends ConcreteClass implements A, B, C, D {
    static void createA(A a) {
    }

    static void createB(B b) {
    }

    static void createC(C c) {
    }

    static void createD(D d) {
    }

    public static void main(String[] args) {
        JavaAssignmentDay3_3 testObject = new JavaAssignmentDay3_3();
        createA(testObject);
        createB(testObject);
        createC(testObject);
        createD(testObject);
    }

    @Override
    public void three() {

    }

    @Override
    public void four() {

    }

    @Override
    public void five() {

    }

    @Override
    public void six() {

    }

    @Override
    public void seven() {

    }
}
