package default_method;

/**
 * @author 夸克
 * @date 10/10/2018 17:52
 */
public class Main {

    public static void main(String[] args) {
        /**
         * 测试实现类可以直接调用接口中的default方法
         */
        Parent parentImpl = new ParentImpl();
        // 将输出Parent
        System.out.println(parentImpl.doit());

        /**
         * 测试Child接口重写了Parent接口的default方法
         */
        Child child = new ChildImpl();
        // 将输出Child
        System.out.println(child.doit());

        /**
         * 测试ParentImpl2重写了Parent接口中default方法
         */
        Parent parentImpl2 = new ParentImpl2();
        // 将输出ParentImpl2
        System.out.println(parentImpl2.doit());

        /**
         * 测试ChildImpl2父类和实现的接口都有default方法，优先使用父类中定义的方法
         */
        Child childImpl2 = new ChildImpl2();
        // 将输出ParentImpl2
        System.out.println(childImpl2.doit());

        /**
         * 从上述测试结果可以看出：
         *      1.实现类可以直接使用父接口中定义的default方法。
         *      2.接口可以重写父接口中定义的default方法。
         *      3.实现类可以重写父接口中定义的方法、
         *      4.当父类和父接口都存在default方法时，使用父类中重写的default方法
         * 特别的，如果一个类实现了两个接口，这两个接口中有同名的default方法签名时，此时会编译不通过，必须在子类中重写这个default方法
         */
    }
}
