package methods;

import data.IntegratedFunction;

public class RectangleMethod extends AbstractMethod{

    private TypeOfRectangleMethod type;
    public RectangleMethod(IntegratedFunction function, double eps, double a, double b, TypeOfRectangleMethod type){
        super(function, eps, a, b);
        this.type = type;
        this.k = 2;
    }


    public void oneTypeSolve(){
        n = firstN;
        this.result_last = 0;
        this.delta = Double.MAX_VALUE;
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
                f_x = function.function().apply(x);
//                writeIteration("x = " + x + " f(x) = " + f_x);
                if(f_x == Double.POSITIVE_INFINITY || f_x == Double.NEGATIVE_INFINITY) {
                    x = x + 0.0000001;
                    f_x = function.function().apply(x);
                }
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
    @Override
    public void solve() {
        if(type == TypeOfRectangleMethod.ALL){
            writeResult("------------------------------");
            writeResult("Метод левых прямоугольников:\n");
            type = TypeOfRectangleMethod.LEFT;
            oneTypeSolve();
            writeResult("------------------------------");
            writeResult("Метод средних прямоугольников:\n");
            type = TypeOfRectangleMethod.MEDIUM;
            oneTypeSolve();
            writeResult("------------------------------");
            writeResult("Метод правых прямоугольников:\n");
            type = TypeOfRectangleMethod.RIGHT;
            oneTypeSolve();
        } else oneTypeSolve();
    }
}
