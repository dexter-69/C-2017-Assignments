/**
 * Create an inheritance hierarchy of Rodent: Mouse, Gerbil, Hamster,etc. In the base class, provide methods that are
 * common to all Rodents, and override these in the derived classes to perform different behaviors depending on the
 * specific type of Rodent. Create an array of Rodent, fill it with different specific types of Rodents, and call your
 * base-class methods to see what happens. Make the methods of Rodent abstract whenever possible and all classes should
 * have default constructors that print a message about that class.
 */

/**
 * @author Balraj Singh
 */




abstract class Rodent {
    Rodent() {
        System.out.println("I'm rodent class..base of all");
    }
    abstract String getRodentName();
    abstract String getFeedingType();
}

class Mouse extends Rodent {
    Mouse() {
        System.out.println("Hi there!..I'm a mouse");
    }
    @Override
    String getRodentName() {
        return "Mouse";
    }
    @Override
    String getFeedingType() {
        return "Herbivores";
    }
}

class Hamster extends Rodent {
    Hamster() {
        System.out.println("Hi there!..I'm a hamster");
    }
    @Override
    String getRodentName() {
        return "Hamster";
    }

    @Override
    String getFeedingType() {
        return "Omnivores";
    }
}

class Gerbil extends Rodent {
    Gerbil() {
        System.out.println("Hi there!..I'm a Gerbil");
    }
    @Override
    String getRodentName() {
        return "Gerbil";
    }

    @Override
    String getFeedingType() {
        return "Omnivores";
    }
}
public class JavaAssignmentDay3_1 {
    public static void main(String[] args) {
        Rodent[] rodents = new Rodent[3];
        rodents[0] = new Mouse();
        rodents[1] = new Hamster();
        rodents[2] = new Gerbil();
        for(int i = 0; i < 3; i++) {
            System.out.println(rodents[i].getRodentName());
            System.out.println(rodents[i].getFeedingType());
        }
    }
}
