package ua.edu.sumdu.ta.pr1;
import ua.edu.sumdu.ta.pr1.operations.Operation;

public class MainClass {
    public static void main(String[] args) {
        for (int x = 1; x < 10; x++) {
            for (int y = 1; y < 10; y++) {
                Operation operation = new Operation(x,y);
                System.out.printf("%3d", operation.getResult());
            }
            System.out.println();
        }
    }
}