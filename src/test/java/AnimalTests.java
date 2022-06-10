import com.practicum.Animal;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class AnimalTests {
    @Test
    public void checkGetFoodExceptionText() {
        Animal animal = new Animal();

        Exception exception = null;
        try {
            animal.getFood("Птица");
            Assert.fail("My method didn't throw when I expected it to");
        } catch (Exception e) {
            exception = e;
            assertNotNull(exception);
        }
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }

    @Test
    public void checkGetFamily() {
        Animal animal = new Animal();
        String actual = animal.getFamily();
        Assert.assertEquals("Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи", actual);
    }
}