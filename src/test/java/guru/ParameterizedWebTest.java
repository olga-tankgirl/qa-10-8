package guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class ParameterizedWebTest {

    static Stream<Arguments> myDataProvider() {
        return Stream.of(
                Arguments.of("Selenide"),
                Arguments.of("JUnit")
        );
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @DisplayName("Тест поиска")
    @MethodSource("myDataProvider")
//    @ValueSource(strings = {"Selenide", "Allure", "JUnit"})
    @ParameterizedTest(name = "Тест {0}")
    void commonYaTest(String testData) {
        Selenide.open("https://ya.ru");
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item")
                .first()
                .shouldHave(Condition.text(testData));


    }



}
