import com.practicum.Cat;
import com.practicum.Feline;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.atLeast;

@RunWith(MockitoJUnitRunner.class)
public class CatTests {
    @Mock
    Feline felineMock;

    @Test
    public void checkGetFoodMethod() throws Exception {
        Cat cat = new Cat(felineMock);
        Mockito.when(cat.getFood()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> arrayListCat = cat.getFood();
        Assert.assertEquals(List.of("Животные", "Птицы", "Рыба"), arrayListCat);

        Mockito.verify(felineMock, atLeast(1)).eatMeat();
    }
    @Test
    public void checkGetSoundMethod() {
        Feline actualFeline = new Feline();
        Cat cat = new Cat(actualFeline);
        Assert.assertEquals("Мяу", cat.getSound());
    }
    @Test
    public void checkCatConstructor() throws Exception {
        Cat cat = new Cat(felineMock);
        cat.getFood();
        Mockito.verify(felineMock, atLeast(1)).eatMeat();
    }
}










