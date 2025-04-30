package hello.htmllogin.basic;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // @Order 에 필요
public class Test_Basic {

    @BeforeAll
    @DisplayName("전체 테스트 메소드 수행 전 한번 실행")
    static void beforeAll() {
        System.out.println("Test_Basic.beforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Test_Basic.afterAll");
    }

    @BeforeEach
    @DisplayName("개별 테스트 메소드 수행 전 매번 실행")
    void beforeEach() {
        System.out.println("Test_Basic.beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Test_Basic.afterEach");
    }

    @Test
    @Order(4)
    void test() {
        System.out.println("test1()");
    }

    @Test
    @Order(3)
    @DisplayName("test2 메소드를 테스트 합니다.")
    void test2(){
        System.out.println("Test_Basic.test2");
    }

    @Test
    @Order(1)
    void test3(){
        System.out.println("Test_Basic.test3");
    }

    @Test
    @Order(2)
    @DisplayName("RuntimeException 발생")
    void test4(){
        System.out.println("test4()");
        String str = null;
        str.length();
    }
}
