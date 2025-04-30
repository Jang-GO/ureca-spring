package hello.htmllogin.basic;

import java.util.Objects;

public class MyClass {
    int n = 10;

    public boolean getResult(){
        return true;
    }

    public String getString(){
        return null;
    }

    public String getString1(){
        return "hello";
    }

    public int getStringLength(String str){
        return str.length();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyClass myClass = (MyClass) o;
        return n == myClass.n;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(n);
    }

}
