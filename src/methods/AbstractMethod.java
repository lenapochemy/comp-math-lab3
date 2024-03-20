package methods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.DoubleFunction;

public abstract class AbstractMethod {

    public final DoubleFunction<Double> function;
    public final int firstN;
    public final double a, b, eps;
    public double h, delta, sum, x, f_x, result, result_last;
    public int n;
    public int k; // порядок точности квадратурной формулы
    private boolean solveMode;

    public AbstractMethod(DoubleFunction<Double> function, double eps, double a, double b){
        this.function = function;
        this.eps = eps;
        this.a = a;
        this.b = b;
        this.firstN = 4;
        this.result_last = 0;
        this.delta = Double.MAX_VALUE;
//        this.h = (b- a) / firstN;
    }

//    public static boolean checkDivergent(int num, double a, double b){
//        if(num == 5){
//            return a <= 1 && 1 <= b;
//        } else return false;
//
//    }

    public void checkDivergent(){
        if(result_last != 0) {
            if (Math.abs(result - result_last) > delta) {
                System.out.println("Интеграл расходится");
                System.exit(0);
            }
            delta = Math.abs(result - result_last);
//            writeIteration("delta = " + delta);
        }
    }
    public abstract void solve();
    //вернет true, когда условие окончания выполняется, и надо заканчивать итерации
    public boolean checkEndCondition(){
//        writeIteration("result = " + result + " result_last = " + result_last + " s = " + Math.abs((result - result_last) / (Math.pow(2, k) - 1)));
        return (Math.abs((result - result_last) / (Math.pow(2, k) - 1)) < eps);
    }

    public void writeIteration(String string){
        if(solveMode){
            System.out.println(string);
        }
    }

    public void writeResult(String string){
        System.out.println(string);
    }

    public void setSolveMode(boolean solveMode){
        this.solveMode = solveMode;
    }

    public double rounding(double number){
        BigDecimal help = new BigDecimal(number);
        help = help.setScale(20, RoundingMode.HALF_UP);
        return help.doubleValue();
    }
}
