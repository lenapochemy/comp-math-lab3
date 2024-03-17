package methods;

import java.util.function.DoubleFunction;

public class TrapezoidMethod extends AbstractMethod{

    public TrapezoidMethod(DoubleFunction<Double> function, double eps, double a, double b){
        super(function, eps, a, b);
        this.k = 2;
    }

    @Override
    public void solve(){
        n = firstN;

        while (true) {
            h = (b - a) / n;
            sum = (function.apply(a) + function.apply(b)) / 2;

            for (double i = a + h; i <= b - h; i = rounding(i + h)) {
                f_x = rounding(function.apply(i));
//                writeIteration("x = " + i + " f(x) = " + f_x);
                sum += f_x;
            }
            result = h * sum;
            writeIteration("Новое значение интеграла: " + result + " при числе разбиений: " + n);
            writeIteration("------------------------------");
            if(checkEndCondition()) break;
            result_last = result;
            n = n * 2;
        }
        writeResult("Результат:\nЗначение интеграла: " + result + " при числе разбиений: " + n);

    }
}
