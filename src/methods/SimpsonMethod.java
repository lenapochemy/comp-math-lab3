package methods;

import java.util.function.DoubleFunction;

public class SimpsonMethod extends AbstractMethod{


    public SimpsonMethod(DoubleFunction<Double> function, double eps, double a, double b){
        super(function, eps, a, b);
        this.k = 4;
    }
    @Override
    public void solve() {
        n = firstN;

        while (true) {
            h = (b - a) / n;
//            sum = function.apply(a) + function.apply(b);
            sum = 0;

            int j = 0;
            for (double i = a; i <= b; i = rounding(i + h), j++) {
                f_x = function.apply(i);
                if(f_x == Double.POSITIVE_INFINITY || f_x == Double.NEGATIVE_INFINITY) {
                    x = x + 0.0000001;
                    f_x = function.apply(x);
                }
//                writeIteration("x = " + i + " f(x) = " + f_x);
                if(i == a || i == b){
                    sum += f_x;
                } else {
                    if (j % 2 == 0) {
                        sum += 2 * f_x;
                    } else sum += 4 * f_x;
                }
            }

            result = (h / 3) * sum;
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
