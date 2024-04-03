package methods;

import data.IntegratedFunction;

public class TrapezoidMethod extends AbstractMethod{

    public TrapezoidMethod(IntegratedFunction function, double eps, double a, double b){
        super(function, eps, a, b);
        this.k = 2;
    }

    @Override
    public void solve(){
        n = firstN;
        delta = Double.MAX_VALUE;

        while (true) {
            h = (b - a) / n;
            sum = 0;
//            sum = (function.apply(a) + function.apply(b)) / 2;

            for (double i = a ; i <= b ; i = rounding(i + h)) {
                f_x = function.function.apply(i);
                if(f_x == Double.POSITIVE_INFINITY || f_x == Double.NEGATIVE_INFINITY) {
                    x = x + 0.0000001;
                    f_x = function.function.apply(x);
                }
                if(i == a || i == b) sum += (f_x / 2);
                else sum += f_x;
//                writeIteration("x = " + i + " f(x) = " + f_x + " sum = " + sum);
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
