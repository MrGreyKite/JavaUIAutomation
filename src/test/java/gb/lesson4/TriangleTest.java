package gb.lesson4;

import gb.lesson4.NotATriangleException;
import gb.lesson4.NotValidDataException;
import gb.lesson4.TriangleClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @DisplayName("Одна из сторон равна нулю")
    @Test
    void oneOfSidesIsNull(){
        NotValidDataException thrown = Assertions.assertThrows(NotValidDataException.class, () -> TriangleClass.squareTriangle(0, 1, 2));
        Assertions.assertEquals("Некорректные данные: одна из сторон меньше или равна нулю", thrown.getMessage());
    }

    @DisplayName("Сумма двух сторон меньше третьей")
    @Test
    void twoSidesIsLesserThanThird() {
        NotATriangleException thrown = Assertions.assertThrows(NotATriangleException.class, () -> TriangleClass.squareTriangle(1, 1, 5));
        Assertions.assertEquals("Некорректные данные: не треугольник", thrown.getMessage());

    }

    @DisplayName("Тест на валидных данных")
    @Test
    void aValidTriangleTest() throws NotATriangleException, NotValidDataException {
        Assertions.assertEquals(TriangleClass.squareTriangle(5,3,4), 6);
    }
}
