/**
 * Create three new types of exceptions. Write a class with a method that throws all three.
 * In main( ), call the method but only use a single catch clause that will catch all three
 * types of exceptions. Add a finally clause and verify that your finally clause is executed,
 * even if a NullPointerException is thrown.
  */

/**
 * @author Balraj Singh
 */
class CustomExceptionA extends Exception {
    CustomExceptionA(String message) {
        super(message);
    }
}

class CustomExceptionB extends Exception {
    CustomExceptionB(String message) {
        super(message);
    }
}

class CustomExceptionC extends Exception {
    CustomExceptionC(String message) {
        super(message);
    }
}

public class JavaAssignmentDay4 {

    private static void exceptionTestMethod(int exceptionToThrow) throws Exception{
        switch (exceptionToThrow) {
            case 1:
                throw new CustomExceptionA("CustomExceptionA thrown");
            case 2:
                throw new CustomExceptionB("CustomExceptionB thrown");
            case 3:
                throw new CustomExceptionC("CustomExceptionC thrown");
            default:
        }
    }
    public static void main(String[] args) {
        try {
            //argument can be 1, 2, 3
            exceptionTestMethod(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Finally Clause Executed....");
        }
    }
}
