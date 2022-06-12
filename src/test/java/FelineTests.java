import com.practicum.Animal;
import com.practicum.Feline;
import com.practicum.Predator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FelineTests {
    @Test
    public void checkGetKittensDefault() {
        Feline feline = new Feline();
        int methodValue = feline.getKittens();
        System.out.println(methodValue);

        Assert.assertEquals(1, methodValue);
    }

    @Test
    public void checkGetKittensWithValue() {
        Feline feline = new Feline();
        int methodValue = feline.getKittens(2);
        System.out.println(methodValue);

        Assert.assertEquals(2, methodValue);
    }

    @Test
    public void checkEatMeat() throws Exception {
        Feline feline = new Feline();
        List<String> arrayListFeline = feline.eatMeat();

        Assert.assertEquals(List.of("Животные", "Птицы", "Рыба"), arrayListFeline);
    }

    @Test
    public void checkGetFamily() {
        Feline feline = new Feline();
        String result = feline.getFamily();

        Assert.assertEquals("Кошачьи", result);
    }
}