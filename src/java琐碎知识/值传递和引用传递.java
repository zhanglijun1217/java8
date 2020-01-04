package java琐碎知识;

import java.util.Arrays;

/**
 * Java 应用程序有且仅有的一种参数传递机制，即按值传递。
 * 对象：传引用地址值。
 * 基本数据类型：传值的副本。
 * String、integer等对象没有提供改变自身的方法所有可以把它们当成基本数据类型对待。
 */
@SuppressWarnings("all")
public class 值传递和引用传递 {
    public static void main(String[] args) {
        /**
         * 来自知乎 Java 到底是值传递还是引用传递？ - Intopass的回答 - 知乎
         * https://www.zhihu.com/question/31203609/answer/50992895
         * https://www.zhihu.com/question/35014775/answer/60785988
         */
        int a = 10;
        change(10);
        System.out.println(a);

        String str = "2321";
        change(str);
        System.out.println(str);

        StringBuilder sb = new StringBuilder("222");
        change(sb);
        System.out.println(sb.toString());

        changeNewSb(sb);
        System.out.println(sb.toString());


        int[] arr1 = new int[2];
        change(arr1);
        System.out.println(Arrays.toString(arr1));

        boolean flag = false;
        change(flag);
        System.out.println(flag);


        /**
         * 字符串的操作
         */

        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";

        String str4 = "a" + "b";
        System.out.println(str3 == str4); // true  字符串常量相加会被编译器优化 可以javap一下看看反编译之后的结果

        String str5 = str1 +str2;
        System.out.println(str5 == str3);// false 非final的字符串变量相加 会等价于 StringBuilder去append再toString生成新的String对象

        // 如果str1 和 str2都是final的
        final String finalStr1 = "a";
        final String finalStr2 = "b";

        String str6 = finalStr1 + finalStr2;
        System.out.println(str6 == str3); // true 这里还是可以走编译器常量的优化 注意：如果是final finalString = new String("a") 就肯定不行了 因为new String("a")就不属于常量了 也不会进行编译器优化
    }

    static void change(int value) {
        value = 100;
    }

    static void change(boolean flag )  {
        flag = true;
    }

    static void change(String str) {
        str = "32213";
    }

    static void change(StringBuilder sb) {
        sb.append("aaa"); // 可以正确的拼接aaa
    }

    static void changeNewSb(StringBuilder sb) {
        sb = new StringBuilder("aaa"); // 这里就不行 其实参数传递就和 = 赋值一样  都是当前方法栈中的一个变量 方法关闭就回收了。 而方法参数传递 等同于 赋值操作
    }

    static void change(int[] arr) {
        arr[1] =3; // 虽然传入的是 MAIN中的arr1地址的副本，但是在change中改变了 堆空间中数组对象的值，而main中的arr1也是指向这个堆中的数组对象，所以看上去是改变了。
        // 但其实传递参数 等同于一个赋值操作，这里永远都不是把Main中的arr1指向了新的对象，而是把原来的对象改变了。
    }
}
