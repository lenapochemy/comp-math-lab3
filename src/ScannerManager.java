import exception.IncorrectValueException;
import methods.*;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.DoubleFunction;

public class ScannerManager {
    private Scanner scanner;
    public ScannerManager(Scanner scanner){
        this.scanner = scanner;
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
                System.out.println("Данные не найдены в файле");
                System.exit(0);
            }
        }
        return flag;
    }

    public AbstractMethod sayMethod(DoubleFunction<Double> function, double eps, double a, double b){
        boolean flag = false;
        while(!flag) {
            try {
                System.out.print("Выберите метод решения уравнения: Метод прямоугольников(r), Метод трапеций(t), Метод Симпсона(s) ");
                String ans = scanner.nextLine().trim();
                switch (ans) {
                    case "" ->
                            throw new NullPointerException();
                    case "r" -> {
                        flag = true;
                        TypeOfRectangleMethod type = sayRectangleType();
                        return new RectangleMethod(function, eps, a, b, type);
                    }
                    case "t" -> {
                        flag = true;
                        return new TrapezoidMethod(function, eps, a, b);
                    }
                    case "s" -> {
                        flag = true;
                        return new SimpsonMethod(function, eps, a, b);
                    }
                    default -> throw new IncorrectValueException();
                }
            } catch (IncorrectValueException | NullPointerException e){
                System.out.println("Ответ должен быть \"r\" или \"t\" или \"s\"");
            } catch (NoSuchElementException e){
                System.out.println("Данные не найдены в файле");
                System.exit(0);
            }
        }
        return null;
    }

    public int sayFunctionNumber(String[] functionStrings){
        int n = functionStrings.length;
        int num = 0;
        String sNum;
        while (num <= 0 || num > n){
            try {
                System.out.println("Выберите функцию для решения: ");
                for(int i = 0; i < n; i++){
                    System.out.println("\t" + (i+1) + ". "+ functionStrings[i]);
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
            }
        }
        return num;
    }


    public TypeOfRectangleMethod sayRectangleType(){
        boolean flag = false;
        while(!flag) {
            try {
                System.out.print("Выберите тип метода прямоугольников: левые(l), правые(r), средние(m): ");
                String ans = scanner.nextLine().trim();
                switch (ans) {
                    case "" ->
                            throw new NullPointerException();
                    case "l" -> {
                        flag = true;
                        return TypeOfRectangleMethod.LEFT;
                    }
                    case "r" -> {
                        flag = true;
                        return TypeOfRectangleMethod.RIGHT;
                    }
                    case "m" -> {
                        flag = true;
                        return TypeOfRectangleMethod.MEDIUM;
                    }
                    default -> throw new IncorrectValueException();
                }
            } catch (IncorrectValueException | NullPointerException e){
                System.out.println("Ответ должен быть \"l\" или \"r\" или \"m\"");
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
                System.out.println("Данные не найдены в файле");
                System.exit(0);
            }
        }
        return num;
    }

    public double sayA(int num){
        if(num == 1){
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
                System.out.println("Данные не найдены в файле");
                System.exit(0);
            }
        }
        return num;
    }

}
