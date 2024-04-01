package data;

import java.util.function.DoubleFunction;

public record IntegratedFunction(DoubleFunction<Double> function, String funcString,
                                 DoubleFunction<Double> primitiveFunc) {

}
