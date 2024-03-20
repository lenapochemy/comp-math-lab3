import methods.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.DoubleFunction;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ScannerManager scannerManager = new ScannerManager(scanner);


        String[] functions = new String[]{
                "1 / sqrt(x)",
                "x³-4.5x²-9.21x-0.383",
                "x³+2x²-3x-12",
                "1 / sqrt(x³)",
                "1 / (1-x)"
        };
        Map<Integer, DoubleFunction<Double>> map = new HashMap<>();
        map.put(1, x -> 1 / Math.sqrt(x));
        map.put(2, x -> x * x * x - 4.5 * x * x - 9.21 * x - 0.383);
        map.put(3, x -> x * x * x + 2 * x * x - 3 * x - 12);
        map.put(4, x -> 1 / Math.sqrt(x * x * x));
        map.put(5, x -> 1 / (1-x));

        int num = scannerManager.sayFunctionNumber(functions);
        double eps = scannerManager.sayEpsilon();
        double a = scannerManager.sayA(num);
        double b = scannerManager.sayB(a);
//        if(AbstractMethod.checkDivergent(num, a, b)) {
//            System.out.println("Интеграл не существует");
////            System.exit(0);
//        } else System.out.println("Интеграл сходится, можем решать");
        AbstractMethod method = scannerManager.sayMethod(map.get(num), eps, a, b);

        method.setSolveMode(scannerManager.saySolveMode());
        method.solve();
//        DoubleFunction<Double> F = x -> x* x* x* x /4 - 4.5 * x *x * x / 3 - 9.21 * x *x /2 - 0.383 * x;
//        method.writeResult("Значение функции = " + (F.apply(b) - F.apply(a)));
    }
}