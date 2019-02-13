package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java8中 Map接口新增的computeIfAbsent方法
 *  HashMap ConcurrentHashMap HashTable都实现了这个方法
 *    此方法首先判断缓存MAP中是否存在指定key的值，如果不存在，会自动调用mappingFunction(key)计算key的value，
 *    然后将key = value放入到缓存Map
 *    如果MAP中存在指定key的值，但value是null，则也会计算value 存入缓存中
 *
 *    如果mappingFunction(key)就是第二个Function函数参数 返回的值为null或抛出异常，则不会有记录存入map
 *
 * @author 夸克
 * @date 2019/2/13 23:23
 */
public class ComputeIfAbsentTest {

    /**
     * 构造的本地缓存
     */
    static final ConcurrentHashMap<Integer, Integer> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
//        cache.put(0, 0);
//        cache.put(1, 1);
        // 普通方式
//        System.out.println("普通方式：" + fibonacci(70));
        // java7方式
//        System.out.println("java7方式" + fibonacciJava7( 80));
        // java8方式
//        System.out.println("java8方式" + fibonacciJava8(80));


        /**
         * 一个简单测试
         */
        Map<String, String>  stringMap = new HashMap<>();
        // value是null值
        stringMap.put("wang",null);

        // 这时会放入map中，因为 map中没有wangsheng 这个key
        stringMap.computeIfAbsent("wangsheng", K -> new String("wang") );

        // 这时这个会将 wang sheng 放入map中
        System.out.println("第一次放入\"wang\"这个key的结果是：" + stringMap.computeIfAbsent("wang", K -> new String("sheng") ));

        // 这里会直接返回上边放入的sheng 不会返回这个string 因为map中存在且value不为null
        System.out.println("第二次放入\"wang\"这个key的结果是：" + stringMap.computeIfAbsent("wang", K ->"string"));


        for(Map.Entry n: stringMap.entrySet()){
            System.out.println(n);
        }
    }

    /**
     * 普通方法去计算 没有使用缓存 在n很大时存在问题
     *
     * @param n
     * @return
     */
    static int fibonacci(int n) {
        System.out.println("calculating Fibonacci(" + n + ")");
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return fibonacci(n -1) + fibonacci(n - 2);
        }
    }

    /**
     * java8之前 java7的做法 检查缓存是否存在key对应的值，如果不存在才进行计算并放入缓存中
     * 这个缓存要是线程安全的 通过synchronized进行线程同步 同时使用double check提供了更好的性能
     * @param n
     * @return
     */
    static int fibonacciJava7(int n) {
            // 先从cache中取结果
            Integer result = cache.get(n);

            // double check
            if (result == null) {
                synchronized (cache) {
                    // 再取一次
                    result = cache.get(n);

                    // 再判断一次
                    if (result == null) {
                        result = fibonacciJava7(n -2) + fibonacciJava7(n - 1);
                        // 放入缓存中
                        cache.put(n, result);
                    }
                }
            }
            return result;
    }

    /**
     * 使用java8的方式去实现 缓存计算的逻辑
     * 如果缓存中不存在，会调用后面的Function接口参数计算key 对应的value 并且将key->value存入缓存中
     * java8会使用thread-safe的方式存取
     * @param n
     * @return
     */
    static int fibonacciJava8(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return cache.computeIfAbsent(n, key -> {
                System.out.println("calculating FibonacciJava8 " + key + ")");
                return fibonacciJava8(key - 2) + fibonacciJava8(key - 1);
            });
        }
    }
}
