import java.util.Arrays;
import java.util.HashSet;

class ConstructorReferenceUtil {

    ConstructorReferenceUtil(String message) {
        System.out.println(message);
    }
}
public class JavaAssignmentDay2 {
    private static class VampireNumberUtil {
        private static int numDigits(long num){
            return Long.toString(Math.abs(num)).length();
        }

        /**
         *
         * @param orig Original Number to be checked for vampire number
         * @param fang1 left half of Original Number
         * @param fang2 Right half of Original Number
         * @return true if left half and right half are fangs and permutation of original number
         */
        private static boolean fangCheck(long orig, long fang1, long fang2){
            if(Long.toString(fang1).endsWith("0") && Long.toString(fang2).endsWith("0")) return false;

            int origLen = numDigits(orig);
            if(numDigits(fang1) != origLen / 2 || numDigits(fang2) != origLen / 2) return false;

            byte[] origBytes = Long.toString(orig).getBytes();
            byte[] fangBytes = (Long.toString(fang1) + Long.toString(fang2)).getBytes();
            Arrays.sort(origBytes);
            Arrays.sort(fangBytes);
            return Arrays.equals(origBytes, fangBytes);
        }

        static void printFirstHundredVampireNumbers() {
            //store unique vampire numbers
            HashSet<Long> vampireNumbersSet = new HashSet<Long>();

            for (long i = 10; vampireNumbersSet.size() < 100; i++) {
                //skip odd number range from i to i * 100
                if ((numDigits(i) % 2) != 0) {
                    i = i * 10 - 1;
                    continue;
                }

                //check and print vampire number
                for (long fang1 = 2; fang1 <= Math.sqrt(i) + 1; fang1++) {
                    if (i % fang1 == 0) {
                        long fang2 = i / fang1;
                        if (fangCheck(i, fang1, fang2) && fang1 <= fang2) {
                            vampireNumbersSet.add(i);
                            System.out.println(i + ": [" + fang1 + ", " + fang2 + "]");
                        }
                    }
                }
            }
        }
    }

    private static class ConstructorOverloadUtil {
        private int width;
        private int height;

        ConstructorOverloadUtil() {
            this(0, 0);
        }

        ConstructorOverloadUtil(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        // Setters //
        /*public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }*/
    }

    public static void main(String[] args) {
        //Question 1
        VampireNumberUtil.printFirstHundredVampireNumbers();

        //Question 2
        ConstructorOverloadUtil util = new ConstructorOverloadUtil();
        System.out.println(util.getWidth() + " " + util.getHeight());

        //Question 3
        String message = "Constructor Created";

        //No message is printed
        ConstructorReferenceUtil[] constructorReferenceUtils = new
                ConstructorReferenceUtil[9];

        //Question 4
        //message is printed for all objects created here
        for (int i = 0; i < 9; i++)
            constructorReferenceUtils[i] = new ConstructorReferenceUtil(message);
    }
}
