/**
 * Create a Cycle class, with subclasses Unicycle, Bicycle and Tricycle. Add a balance( ) method to Unicycle and
 * Bicycle, but not to Tricycle. Create instances of all three types and upcast them to an array of Cycle. Try to call
 * balance( ) on each element of the array and observe the results. Downcast and call balance( ) and observe what
 * happens.
 */

/**
 * @author Balraj Singh
 */
class Cycle {

}
class Unicycle extends Cycle {
    public void balance() {
        System.out.println("Unicycle Balance");
    }
}
class Bicycle extends Cycle {
    public void balance() {
        System.out.println("Bicycle Balance");
    }
}
class Tricycle extends Cycle {

}

public class JavaAssignmentDay3_2 {
    public static void main(String[] args) {
        Unicycle unicycle = new Unicycle();
        Bicycle bicycle = new Bicycle();
        Tricycle tricycle = new Tricycle();
        Cycle[] cycles = new Cycle[3];
        cycles[0] = unicycle;
        cycles[1] = bicycle;
        cycles[2] = tricycle;
        /*
        *method balance() not declared in parent class
        for(Cycle cycle : cycles)
            cycle.balance();
            */
        ((Unicycle) cycles[0]).balance();
        ((Bicycle) cycles[1]).balance();
        /*
        ** Method not found
        ((Tricycle) cycles[3]).balance();
        */
    }
}
