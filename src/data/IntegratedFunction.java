package data;

import java.util.function.DoubleFunction;

public class IntegratedFunction {
    public DoubleFunction<Double> function;
    public String funcString;
    public DoubleFunction<Double> primitiveFunc;

    public IntegratedFunction(DoubleFunction<Double> function, String funcString,
                              DoubleFunction<Double> primitiveFunc){
        this.function = function;
        this.funcString = funcString;
        this.primitiveFunc = primitiveFunc;
    }

//    public String getFuncString() {
//        return funcString;
//    }
//
//    public DoubleFunction<Double> getFunction() {
//        return function;
//    }
//
//    public DoubleFunction<Double> getPrimitiveFunc() {
//        return primitiveFunc;
//    }


}
