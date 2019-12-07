package default_method.默认方法彩蛋;

/**
 * Created by zlj on 2019/12/7.
 *
 * 实现两个接口中有同名的默认方法时 得指定实现的是哪个接口中的方法，这里用特殊的语法
 */
public class 同名默认方法测试 implements MyInterface1, MyInterface2 {

    @Override
    public void myMethod() {
        // 指定实现MyInterface1中的myMethod方法
        MyInterface1.super.myMethod();
    }

    public static void main(String[] args) {
        同名默认方法测试 m = new 同名默认方法测试();

        m.myMethod();
    }

}
