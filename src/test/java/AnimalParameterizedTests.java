import com.practicum.Animal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

    @RunWith(Parameterized.class)
    public class AnimalParameterizedTests {

        private final String input;
        private final List<String> expected;

        public AnimalParameterizedTests(String input, List<String> expected) {
            this.input = input;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static Object[][] sendAnimalType() {
            return new Object[][]{
                    {"Хищник", List.of("Животные", "Птицы", "Рыба")},
                    {"Травоядное", List.of("Трава", "Различные растения")},
            };
        }

        @Test
        public void checkGetFood() throws Exception {
            Animal animal = new Animal();
            List<String> actual = animal.getFood(input);
            Assert.assertEquals(expected, actual);
        }
    }

