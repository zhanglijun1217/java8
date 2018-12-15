package Arrays_asList;

import java.util.Arrays;
import java.util.List;

/**
 * 测试Arrays.asList方法
 *  该方法有三个特点：
 *      （1）不适用于基本类型：int,long,double,float等
 *      （2）该方法将数组和链表链接起来，当一个更新后，另一个也会更新
 *      （3）不支持add和remove方法
 * @author 夸克
 * @date 2018/12/16 00:00
 */
public class TestArraysAsList {

    public static void main(String[] args) {
        int[] int_array = {1, 2, 3};
        List<int[]> ints = Arrays.asList(int_array);
        // 对于基本类型，会打印一个元素结果
        System.out.println(ints);

        // 基本类型生成的list的class 肯定不会打出array，因为array类型属于java.lang.reflect包，通过反射访问数组这个类编译生成的
        System.out.println("基本类型转换数组的class类型：" + ints.get(0).getClass());

        String[] string_array = {"aa", "bb", "cc"};
        List<String> stringList = Arrays.asList(string_array);

        System.out.println("============改变数组前============");
        System.out.println(stringList);

        // 改变数组
        string_array[1] = "change";
        System.out.println("============改变数组后============");
        System.out.println(stringList);

        // 改变list
        stringList.set(1, "change2");
        System.out.println("============改变list后============");
        System.out.println(string_array[1]);

        // Arrays.asList方法不支持remove
        Integer[] integer_array = {1, 2, 3};
        List<Integer> integerList = Arrays.asList(integer_array);

        System.out.println("============调用了add和remove方法============");
        // 这时候会抛出 java.lang.UnsupportedOperationException 这个异常
        integerList.add(4);

    }
}
