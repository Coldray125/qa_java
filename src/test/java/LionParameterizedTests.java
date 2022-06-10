import com.practicum.Feline;
import com.practicum.Lion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@RunWith(Parameterized.class)
    public class LionParameterizedTests {

    @Before
    public void initMocks() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }
        private final String input;
        private final Boolean expected;

        public LionParameterizedTests(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static Object[][] getSumData() {
            return new Object[][]{
                    {"Самка", false},
                    {"Самец", true},

            };
        }

        @Test
        public void checkLion() throws Exception {
            Lion lion = new Lion(input, Mockito.mock(Feline.class));
            boolean actual = lion.doesHaveMane();
            Assert.assertEquals(expected, actual);
        }
    }
