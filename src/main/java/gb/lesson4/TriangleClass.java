package gb.lesson4;

public class TriangleClass {
    public static double squareTriangle (int a, int b, int c) throws NotValidDataException, NotATriangleException {

        if ((a <= 0) || (b <= 0) || (c <= 0)) {
            throw new NotValidDataException("Некорректные данные: одна из сторон меньше или равна нулю");
        }

        if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
            throw new NotATriangleException("Некорректные данные: не треугольник");
        }

        double p = ((double)a + (double)b + (double)c)/2;
        double square = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        System.out.println("Площадь треугольника со сторонами " + a + "," + b +  "," + c + " равна: " + square);
        return square;
    }
}
