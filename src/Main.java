import data.IntegratedFunction;
import methods.*;

public class Main {
    public static void main(String[] args) {

        ScannerManager scannerManager = new ScannerManager();

        IntegratedFunction func1 = new IntegratedFunction(x -> 1 / Math.sqrt(x), "1 / sqrt(x)", x -> 2 * Math.sqrt(x));
        IntegratedFunction func2 = new IntegratedFunction(x -> x * x * x - 4.5 * x * x - 9.21 * x - 0.383, "x³-4.5x²-9.21x-0.383",
                x -> 0.25 * Math.pow(x, 4)  - 1.5 * Math.pow(x, 3) - 4.605 * x * x  - 0.383 * x);
        IntegratedFunction func3 = new IntegratedFunction(x -> x * x * x + 2 * x * x - 3 * x - 12,
                "x³+2x²-3x-12", x -> 0.25*x*x*x*x  + 0.666667 *x*x*x - 1.5 * x * x - 12 * x);
        IntegratedFunction func4 = new IntegratedFunction(x -> Math.log(x) / Math.sqrt(x), "ln(x) / sqrt(x)",
                x -> 2 * Math.sqrt(x) * Math.log(x) - 4 * Math.sqrt(x));
        IntegratedFunction func5 = new IntegratedFunction(x -> 1 / (1-x), "1 / (1-x)", x -> -Math.log(Math.abs(1-x)));

        IntegratedFunction[] functions = new IntegratedFunction[]{ func1, func2, func3, func4, func5};

        int num = scannerManager.sayFunctionNumber(functions);
        double eps = scannerManager.sayEpsilon();
        double a = scannerManager.sayA(num);
        double b = scannerManager.sayB(a);

        AbstractMethod.checkDivergent(functions[num - 1], a, b);

        AbstractMethod method = scannerManager.sayMethod(functions[num - 1], eps, a, b);

        method.setSolveMode(scannerManager.saySolveMode());
//        method.checkDivergent();
        method.solve();
        method.exactValue();
    }
}