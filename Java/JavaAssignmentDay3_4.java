/**
 * Create a Cycle interface, with implementations Unicycle, Bicycle and Tricycle. Create factories for each type of
 * Cycle, and code that uses these factories.
 */

/**
 * @author Balraj Singh
 */
interface Cycle {}
class Unicycle implements Cycle{}
class Bicycle implements Cycle{}
class Tricycle implements Cycle{}

class CycleFactory {
    static final String UNICYCLE = "UNICYCLE", BICYCLE = "BICYCLE", TRICYCLE = "TRICYCLE";
    public Cycle getCycle(String cycleType) {
        Cycle cycle;
        switch (cycleType) {
            case UNICYCLE:
                cycle =  new Unicycle();
                break;
            case BICYCLE:
                cycle = new Bicycle();
                break;
            case TRICYCLE:
                cycle = new Tricycle();
                break;
            default:
                cycle = null;
                break;
        }
        return cycle;
    }
}
public class JavaAssignmentDay3_4 {
    public static void main(String[] args) {
        CycleFactory cycleFactory = new CycleFactory();
        Bicycle bicycle = (Bicycle) cycleFactory.getCycle(CycleFactory.BICYCLE);
        Tricycle tricycle = (Tricycle) cycleFactory.getCycle(CycleFactory.TRICYCLE);
        Unicycle unicycle = (Unicycle) cycleFactory.getCycle(CycleFactory.UNICYCLE);
    }
}
