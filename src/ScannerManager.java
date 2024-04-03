import data.IntegratedFunction;
import exception.IncorrectValueException;
import methods.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScannerManager {
    private Scanner scanner;
    public ScannerManager(){
        this.scanner = new Scanner(System.in);
    }

    public boolean saySolveMode(){
        boolean flag = false;
        while(!flag) {
            try {
                System.out.print("Нужно выводить результат каждой итерации решения? (y/n) ");
                String ans = scanner.nextLine().trim();
                switch (ans) {
                    case "" ->
                        throw new NullPointerException();
                    case "y" -> {
                        flag = true;
                        return true;
                    }
                    case "n" -> {
                        flag = true;
                        return false;
                    }
                    default -> throw new IncorrectValueException();
                }
            } catch (IncorrectValueException | NullPointerException e){
                System.out.println("Ответ должен быть \"y\" или \"n\"");
            } catch (NoSuchElementException e){
                System.out.println("Введите значение");
            }
        }
        return flag;
    }

    public AbstractMethod sayMethod(IntegratedFunction function, double eps, double a, double b){
        boolean flag = false;
        while(!flag) {
            try {
                System.out.println("Выберите метод решения уравнения:\n\t1. Метод прямоугольников\n\t2. Метод трапеций\n\t3. Метод Симпсона");
                String ans = scanner.nextLine().trim();
                switch (ans) {
                    case "" ->
                            throw new NullPointerException();
                    case "1" -> {
                        flag = true;
                        TypeOfRectangleMethod type = sayRectangleType();
                        return new RectangleMethod(function, eps, a, b, type);
                    }
                    case "2" -> {
                        flag = true;
                        return new TrapezoidMethod(function, eps, a, b);
                    }
                    case "3" -> {
                        flag = true;
                        return new SimpsonMethod(function, eps, a, b);
                    }
                    default -> throw new IncorrectValueException();
                }
            } catch (IncorrectValueException | NullPointerException e){
                System.out.println("Ответ должен быть положительным числом, не большим 3");
            } catch (NoSuchElementException e){
                System.out.println("Введите значение");
            }
        }
        return null;
    }

    public int sayFunctionNumber(IntegratedFunction[] functions){
        int n = functions.length;
        int num = 0;
        String sNum;
        while (num <= 0 || num > n){
            try {
                System.out.println("Выберите функцию для решения: ");
                for(int i = 0; i < n; i++){
                    System.out.println("\t" + (i+1) + ". "+ functions[i].funcString);
                }
                sNum = scanner.nextLine().trim();
                if(sNum.isEmpty()) throw new NullPointerException();
                num = Integer.parseInt(sNum);
                if(num <= 0 || num > n) throw new IncorrectValueException();
            } catch (IncorrectValueException e){
                System.out.println("Номер функции должен быть положительным числом, не большим " + n );
            } catch (NullPointerException e){
                System.out.println("Номер функции не может быть пустым");
            }  catch (NumberFormatException e){
                System.out.println("Номер функции должен быть целым числом");
            } catch (NoSuchElementException e){
                System.out.println("Введите значение");
            }
        }
        return num;
    }


    public TypeOfRectangleMethod sayRectangleType(){
        boolean flag = false;
        while(!flag) {
            try {
                System.out.println("Выберите тип метода прямоугольников:\n\t1. Левые\n\t2. Правые\n\t3. Средние\n\t4. Все типы сразу");
                String ans = scanner.nextLine().trim();
                switch (ans) {
                    case "" ->
                            throw new NullPointerException();
                    case "1" -> {
                        flag = true;
                        return TypeOfRectangleMethod.LEFT;
                    }
                    case "2" -> {
                        flag = true;
                        return TypeOfRectangleMethod.RIGHT;
                    }
                    case "3" -> {
                        flag = true;
                        return TypeOfRectangleMethod.MEDIUM;
                    }
                    case "4" -> {
                        flag = true;
                        return TypeOfRectangleMethod.ALL;
                    }
                    default -> throw new IncorrectValueException();
                }
            } catch (IncorrectValueException | NullPointerException e){
                System.out.println("Ответ должен быть положительным числом, не большим 4");
            } catch (NoSuchElementException e){
                System.out.println("Введите значение");
            }
        }
        return TypeOfRectangleMethod.MEDIUM;
    }



    public double sayEpsilon(){
        double num = 0;
        String sNum;
        while (num < 0.000001 || num > 1){
            try {
                System.out.print("Введите значение точности [0.000001; 1]: ");
                sNum = scanner.nextLine().trim();
                if(sNum.isEmpty()) throw new NullPointerException();
                num = Double.parseDouble(sNum);
                if(num < 0.000001 || num > 1) throw new IncorrectValueException();
            } catch (IncorrectValueException e){
                System.out.println("Значение точности должно быть положительным числом из промежутка [0.000001; 1]");
            } catch (NullPointerException e){
                System.out.println("Значение точности не может быть пустым");
            }   catch (NumberFormatException e){
                System.out.println("Количество итераций должно быть числом");
            } catch (NoSuchElementException e){
                System.out.println("Введите значение");
            }
        }
        return num;
    }

    public double sayA(int num){
        if(num == 1 || num == 4){
            double a = -1;
            while (a < 0){
                a = sayDoubleNumber("левой границы интервала");
                if(a < 0) System.out.println("Функция неопределена на отрицательных числах, выберите неотрицательное значение левой границы интервала");
            }
            return a;
        } else return sayDoubleNumber("левой границы интервала");
    }

    public double sayB(double a) {
        double b = a;
        while(b <= a) {
            b = sayDoubleNumber("правой границы интервала");
            if(b <= a) System.out.println("Значение правой границы интервала должно быть больше левой");
        }
        return b;
    }

    public double sayDoubleNumber(String name){
        double num = 0;
        String sNum;
        boolean flag = true;
        while (flag){
            try {
                System.out.print("Введите " + name +  ": ");
                sNum = scanner.nextLine().trim();
                if(sNum.isEmpty()) throw new NullPointerException();
                num = Double.parseDouble(sNum);
                flag = false;
            } catch (NullPointerException e){
                System.out.println("Значение " + name +" не может быть пустым");
            }  catch (NumberFormatException e){
                System.out.println("Значение " + name + " должно быть числом");
            } catch (NoSuchElementException e){
                System.out.println("Введите значение");
            }
        }
        return num;
    }

}
