package methods;

import java.util.function.DoubleFunction;

public class RectangleMethod extends AbstractMethod{

    private final TypeOfRectangleMethod type;
    public RectangleMethod(DoubleFunction<Double> function, double eps, double a, double b, TypeOfRectangleMethod type){
        super(function, eps, a, b);
        this.type = type;
        this.k = 2;
    }

    @Override
    public void solve() {

        n = firstN;
//        result_last = 0;
        while (true) {
            h = (b - a) / n;
//            System.out.println(a + " " + b + " " + h);
            sum = 0;
            for (double i = a; i <= b; i = rounding(i + h)) {
                switch (type) {
                    case MEDIUM -> {
//                    System.out.println(i);
                        x = i + h / 2;
                        if (x > b) continue;
                    }
                    case LEFT -> {
                        if (i == b) continue;
                        x = i;
                    }
                    default -> {
                        if (i == a) continue;
                        x = i;
                    }
                }
                f_x = function.apply(x);
//                writeIteration("x = " + x + " f(x) = " + f_x);
                if(f_x == Double.POSITIVE_INFINITY || f_x == Double.NEGATIVE_INFINITY) {
                    x = x + 0.0000001;
                    f_x = function.apply(x);
                }
                sum += f_x;
            }
            result = h * sum;
            writeIteration("Новое значение интеграла: " + result + " при числе разбиений: " + n);
            writeIteration("------------------------------");
            checkDivergent();
            if(checkEndCondition()) break;
            result_last = result;
            n = n * 2;
        }
        writeResult("Результат:\nЗначение интеграла: " + result + " при числе разбиений: " + n);

    }
}
