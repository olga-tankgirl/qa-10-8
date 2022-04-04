package guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

public class ParameterizedWebTest {

    @BeforeEach
    void beforeEach() {
        Selenide.open("https://imdb.com");
    }

    @DisplayName("ValueSource")
    @ValueSource(strings = {"Wes Anderson", "Bill Murray", "Austin Powers"})
    @ParameterizedTest(name = "Тест {0}")
    void valueTest(String testData) {
        Selenide.$("#suggestion-search").setValue(testData);
        Selenide.$("button[type='submit']").click();
        Selenide.$$("div.findSection").first().shouldHave(Condition.text(testData));
    }

    @DisplayName("CsvSource")
    @CsvSource(value = {
            "Wes Anderson,Producer",
            "Bill Murray,Actor",
            "Austin Powers,TV Episode"
    })
    @ParameterizedTest(name = "Тест {0}")
    void csvTest(String testData, String testResult) {
        Selenide.$("#suggestion-search").setValue(testData);
        Selenide.$("button[type='submit']").click();
        Selenide.$$("div.findSection").first().shouldHave(Condition.text(testResult));
    }


    static Stream<Arguments> myDataProvider() {
        return Stream.of(
                Arguments.of("Wes Anderson", List.of("Writer","Producer")),
                Arguments.of("Bill Murray", List.of("Actor","Writer")),
                Arguments.of("Austin Powers", List.of("TV Episode","TV Series")));
    }

    @DisplayName("MethodSource")
    @MethodSource("myDataProvider")
    @ParameterizedTest(name = "Тест {0}")
    void methodTest(String testData, List<String> testResult) {
        Selenide.$("#suggestion-search").setValue(testData);
        Selenide.$("button[type='submit']").click();
        for (String s:testResult) {
            Selenide.$$("div.findSection").first().shouldHave(Condition.text(s));
        }
    }
}
