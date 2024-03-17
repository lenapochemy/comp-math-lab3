import methods.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.DoubleFunction;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        DoubleFunction<Double> function = x -> x * x * x + 2 * x * x - 3 * x - 12;
        DoubleFunction<Double> f2 = x -> x * x;
//        RectangleMethod rectangleMethod = new RectangleMethod(f2, 1.0, 2.0, 5, TypeOfRectangleMethod.RIGHT);
//        rectangleMethod.setSolveMode(true);
//        rectangleMethod.solve();
//        TrapezoidMethod trapezoidMethod = new TrapezoidMethod(f2, 1, 2, 10);
//        trapezoidMethod.setSolveMode(true);
//        trapezoidMethod.solve();
//        SimpsonMethod simpsonMethod = new SimpsonMethod(f2, 1, 2);
//        simpsonMethod.setSolveMode(true);
//        simpsonMethod.solve();

        Scanner scanner = new Scanner(System.in);
        ScannerManager scannerManager = new ScannerManager(scanner);


        String[] functions = new String[]{
                "x³-x+4",
                "x³-4.5x²-9.21x-0.383",
                "x³+2x²-3x-12",
                "x³+2x²-4x",
                "x²"
        };
        Map<Integer, DoubleFunction<Double>> map = new HashMap<>();
        map.put(1, x -> x * x * x - x + 4);
        map.put(2, x -> x * x * x - 4.5 * x * x - 9.21 * x - 0.383);
        map.put(3, x -> x * x * x + 2 * x * x - 3 * x - 12);
        map.put(4, x -> x * x * x + 2 * x * x - 4 * x);
        map.put(5, x -> x * x);

        int num = scannerManager.sayFunctionNumber(functions);
        double eps = scannerManager.sayEpsilon();
        double a = scannerManager.sayA();
        double b = scannerManager.sayB(a);
        AbstractMethod method = scannerManager.sayMethod(map.get(num), eps, a, b);

        method.setSolveMode(scannerManager.saySolveMode());
        method.solve();
    }
}