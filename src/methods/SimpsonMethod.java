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
            sum = function.apply(a) + function.apply(b);

            int j = 1;
            for (double i = a + h; i <= b - h; i = rounding(i + h), j++) {
                f_x = function.apply(i);
//                writeIteration("x = " + i + " f(x) = " + f_x);
                if (j % 2 == 0) {
                    sum += 2 * f_x;
                } else sum += 4 * f_x;
            }

            result = (h / 3) * sum;
            writeIteration("Новое значение интеграла: " + result + " при числе разбиений: " + n);
            writeIteration("------------------------------");
            if(checkEndCondition()) break;
            result_last = result;
            n = n * 2;
        }
        writeResult("Результат:\nЗначение интеграла: " + result + " при числе разбиений: " + n);

    }
}
