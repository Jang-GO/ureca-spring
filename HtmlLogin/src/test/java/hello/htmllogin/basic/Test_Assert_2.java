package hello.htmllogin.basic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Assert_2 {

    @Test
    @Order(1)
    void testAssertThrows(){
        MyClass mc =  new MyClass();
//        String str = "hello";
        String str = null;

        assertThrows(NullPointerException.class, () -> {
            mc.getStringLength(str);
        }, "mc.getStringLength() 는 NullPointerException 을 발생시켜야 한다.");
    }

    int m1() {return 1;}
    boolean m2() {return true;};
    String m3() {return "hello";}

    @Test
    @Order(2)
    void testAssertAll(){
        assertAll(
                () -> assertEquals(1, m1()),
                () -> assertTrue(m2()),
                () -> assertNotNull(m3())
        );
    }

    int[] expectedArr = {1,2,3};
    int[] actualArr = {1,2,3};
    @Test
    @Order(3)
    void testAssertArrayEquals(){
        assertArrayEquals(expectedArr, actualArr, "두 배열은 같아야 한다");
    }

    List<String> expectedList = Arrays.asList("aaa","bbb","ccc");
    List<String> actualList = Arrays.asList("aaa","bbb","ccc");
    @Test
    @Order(4)
    void testAssertIterableEquals(){
        assertIterableEquals(expectedList, actualList, "두 컬렉션은 같아야 한다");
    }

}
