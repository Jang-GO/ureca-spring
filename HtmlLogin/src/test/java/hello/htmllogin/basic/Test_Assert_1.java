package hello.htmllogin.basic;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// assertOOO 메소드를 통해서 판단(같다 다르다 null, not null ...)
// assertOOO 메소드의 테스트가 실패하면 세 번째 message 가 Failure Trace 맨 앞에 표시
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Assert_1 {

    @Test
    @Order(1)
    void testAssertEquals(){
//        assertEquals(1, 2, "1==1");
        int legacyNum = LegacySystem.getNum();
        int testNum = TestSystem.getNum();
        assertEquals(legacyNum, testNum, "LegacySystem 과 TestSystem 비교");
    }

    @Test
    @Order(2)
    void testAssertNotEquals(){
        int legacyNum = 2;
        int testNum = 3;
        assertNotEquals(legacyNum, testNum, "LegacySystem 과 TestSystem 비교");
    }

    @Test
    @Order(3)
    void testAssertEqualsObject(){
        // MyClass 에 equals(), hashCode() 재정의 하지 않으면 실패, 재정의 후 성공
        MyClass mc1 = new MyClass();
        MyClass mc2 = new MyClass();
        assertEquals(mc1, mc2,"mc1과 mc2 equals 비교");
    }

    @Test
    @Order(4)
    void testAssertTrue(){
        MyClass mc = new MyClass();
        assertTrue(mc.getResult(), "mc.getResult()의 결과가 true 여야 한다.");
    }

    @Test
    @Order(5)
    void testAssertFalse(){
        MyClass mc = new MyClass();
        assertFalse(!mc.getResult(), "mc.getResult()의 결과의 반대가 false 여야 한다.");
    }

    @Test
    @Order(6)
    void testAssertNull(){
        MyClass mc = new MyClass();
        assertNull(mc.getString(), "mc.getString()의 결과가 null 이어야 한다.");
    }

    @Test
    @Order(7)
    void testAssertNotNull(){
        MyClass mc = new MyClass();
        assertNotNull(mc.getString1(), "mc.getString1()의 결과가 null 이면 안된다.");
    }
}
