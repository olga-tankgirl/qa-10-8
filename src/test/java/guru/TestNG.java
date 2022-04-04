package guru;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestNG {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        SimpleTest obj = SimpleTest.class.getDeclaredConstructor().newInstance();
        for (Method declaredMethod : SimpleTest.class.getDeclaredMethods()) {
            Test test = declaredMethod.getAnnotation(Test.class);
            if (test != null) {
                try {
                    declaredMethod.invoke(
                            obj
                    );
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                    System.out.println("Test " + declaredMethod.getName() + " failed: " + e.getCause().getMessage());
                    continue;
                    } else{
                        System.out.println("Test " + declaredMethod.getName() + " broken: " + e.getCause().getMessage());
                        continue;
                    }
                }
                System.out.println(("Test " + declaredMethod.getName() + " passed!"));
            }
        }
    }
}
