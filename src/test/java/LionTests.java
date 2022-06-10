import com.practicum.Feline;
import com.practicum.Lion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeast;

@RunWith(MockitoJUnitRunner.class)
public class LionTests {

    @Mock
    Feline felineMock;

    @Test
    public void checkGetFood() throws Exception {
        Lion lion = new Lion("Самец", felineMock);
        Mockito.when(felineMock.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> arrayListCat = lion.getFood();
        Assert.assertEquals(List.of("Животные", "Птицы", "Рыба"), arrayListCat);

        Mockito.verify(felineMock, atLeast(1)).getFood("Хищник");
    }

    @Test
    public void checkGetKittens() throws Exception {
        Lion lion = new Lion("Самец", felineMock);
        Mockito.when(felineMock.getKittens()).thenReturn(1);
        int actual = lion.getKittens();

        Assert.assertEquals(1, actual);
    }

    @Test
    public void checkGetFoodExceptionText() {
        Exception exception = null;
        try {
            Lion lion = new Lion("Тест", felineMock);
            Assert.fail("My method didn't throw when I expected it to");
        } catch (Exception e) {
            exception = e;
            assertNotNull(exception);
        }

        assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
    }
}