package guru;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleTest {

    private String str = "Junit";

    @Test
    @DisplayName("surely will fail")
    void test() {
        Assertions.assertTrue(3<2);

    }

    @Test
    @DisplayName("surely will pass")
    void test1() {
        Assertions.assertTrue(3>2);
    }

    @Test
    @DisplayName("surely will be broken")
    void test2() {
        throw new NullPointerException();
    }
}
