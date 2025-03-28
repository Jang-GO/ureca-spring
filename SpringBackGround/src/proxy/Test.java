package proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        MyIF myIf = new MyIFImpl();

        String param1 = "abc";
        String param2 = null;
//        String param2 = "def";

        // proxy 없이 객체의 메소드를 직접 호출하는 것 (@CheckNotNull 적용 X)
//        myIf.m(param1, param2);
//        myIf.m2(param1, param2);
//        myIf.m3(param1, param2);
//        myIf.m4(param1, param2);

        // myIF 객체의 proxy 를 이용해서 점검하고 호출
        // 같은 타입 으로
        // InvocationHandler : 객체의 메소드가 호출될 때, 대신 점검하고 호출하는 역할
        //                  * 모든 proxy 객체는 반드시 InvocationHandler 객체를 가져야 한다.
        MyIF proxy = (MyIF) Proxy.newProxyInstance(
                myIf.getClass().getClassLoader(),
                myIf.getClass().getInterfaces(),
                new CheckNotNullInvocationHandler(myIf)
        );

        proxy.m(param1, param2);
        proxy.m2(param1, param2);
        proxy.m3(param1, param2);
        proxy.m4(param1, param2);
    }
}
