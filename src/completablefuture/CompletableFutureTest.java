package completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * https://www.ibm.com/developerworks/cn/java/j-cf-of-jdk8/index.html
 */
@SuppressWarnings("all")
public class CompletableFutureTest {

    public static void main(String[] args) {

        /**
         * CompletableFuture的基本使用
         */
        try {
//            completedFutureExample();
//        runSimpleAsyncExample();
//        thenApplyExample();
//        thenApplyAsyncExample();
//        thenApplyAsyncWithExecutor();
//        thenAcceptExample();
//        thenAcceptAsyncExample();
//            getAndJoinExample();
//            completeExceptionallyExample();
//            cancelExample();
//            applyToEitherExample();
//            runAfterBothExample();
//            thenCombineExample();
//            thenComposeExample();
//            anyOfAndAllOfExample();
            whenCompleteExample();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 构建完成的 CompletableFuture
     */
    static void completedFutureExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("completableFutureTest");
        System.out.println("cf是否完成:" + cf.isDone());
        // getNow(null) 就是返回计算结果或者null
        System.out.println("completableFutureTest".equals(cf.getNow(null)));
    }

    /**
     * 运行简单的异步场景
     */
    static void runSimpleAsyncExample() {
            CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                System.out.println("当前线程：" + Thread.currentThread().getName()
                        // 内部采用ForkJoinPool实现异步，这种方式使用了daemon线程执行task
                        + " 是否Daemon：" + Thread.currentThread().isDaemon());
                try {
                    // 足够长
                    TimeUnit.SECONDS.sleep(3);
                    System.out.printf("当前线程：%s完成了\n", Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // 异步执行，因为执行逻辑中sleep 所以这里判断isDone是false
        System.out.println("当前cf是否完成：" + cf.isDone());
        // 当这里sleep足够多 可以看到是异步完成
        try {
            // 足够长
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("当前cf是否完成：" + cf.isDone());
    }

    /**
     * thenApply的例子
     * thenApply在异步计算正常完成的前提下 同步进行执行
     */
    static void thenApplyExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
                .thenApplyAsync(s -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("如果下面不用join阻塞获取值，这是一段不会输出的代码"); // 这里也会导致下边thenApply代码不运行，
                    // 下面的thenApply是在thenApplyAsync的同步，而applyAsync是daemon线程执行的，里面slepp了3s 所以下面不会执行
                    return s.toUpperCase();
                })
                .thenApply(s -> {
                    //                              这里会输出main线程在执行 thenApply中的内容
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "正在计算，线程是否为daemon:" +
                    Thread.currentThread().isDaemon());
                    return s.toString();
                });

        System.out.println(cf.getNow(""));
        // 当然如果用join去获取值，这里也会阻塞等到thenApply运行完成之后再结束main线程
        System.out.println(cf.join());
    }

    /**
     * thenApplyAsync例子
     *
     */
    static void thenApplyAsyncExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
                // thenAppplyAsync是异步执行的
                .thenApplyAsync(s -> {
                    //                              这里会输出异步线程在执行 用的是ForkJoinPool.commonPool执行
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "正在计算，线程是否为daemon:" +
                            Thread.currentThread().isDaemon());
                    // 运行足够长时间
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return s.toUpperCase();
                });

        System.out.println("cf是否完成：" + cf.isDone());
        // 这里用join来等待
        System.out.println("阻塞等待结果：" + cf.join());

    }

    /**
     * 基于一个自定义线程池去完成CompletableFuture执行task
     */
    static Executor executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        AtomicInteger count = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            String name = "custom-executor-" + count.getAndIncrement();
            final Thread thread = new Thread(r, name);
            thread.setDaemon(true);
            return thread;
        }
    });

    static void thenApplyAsyncWithExecutor () {
        CompletableFuture<String> message = CompletableFuture.completedFuture("message")
                .thenApplyAsync(s-> {
                    return "当前线程：" + Thread.currentThread().getName();
                }, executor);

        // join获取
        System.out.println(message.join());
        executor.execute(() -> {
            try {
//                TimeUnit.SECONDS.sleep(2);
                System.out.println("当前线程哈哈哈哈：" + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * thenAccept 接收一个Consumer接口
     */
    static void thenAcceptExample() {
        StringBuilder stringBuilder = new StringBuilder();
        final CompletableFuture<Void> voidCompletableFuture = CompletableFuture.completedFuture("开心、健康")
                .thenAccept(s -> stringBuilder.append(s).append("当前线程：").append(Thread.currentThread().getName()));

        System.out.println(stringBuilder.toString());
    }

    /**
     * thenAcceptAsync
     */
    static void thenAcceptAsyncExample() {
        StringBuilder stringBuilder = new StringBuilder();
        final CompletableFuture<Void> 开心健康 = CompletableFuture.completedFuture("开心健康")
                .thenAcceptAsync(s -> stringBuilder.append(s).append(Thread.currentThread().getName()));

        开心健康.join();
        System.out.println(stringBuilder.toString());
    }

    /**
     * get和join的区别
     * @throws Exception
     */
    static void getAndJoinExample() throws Exception{
//        final CompletableFuture<Integer> cp1 = CompletableFuture.supplyAsync(() -> 1 / 0);
//        final CompletableFuture<Integer> cp2 = CompletableFuture.supplyAsync(() -> 1 / 0);
//
//        cp1.get(); // get方法抛出来受检异常，外层代码必须处理
//        cp2.join();// join都转换为对应的非受检异常 你可以比较优雅的处理 比如下面的代码

        final CompletableFuture<Integer> cp3 = CompletableFuture.supplyAsync(() -> 1 / 0)
                .exceptionally(e -> {
                    System.out.println("发生了异常"+ e.getMessage());
                    return 212; // 异常之后的兜底值
                });
        System.out.println(cp3.join());
    }


    /**
     * completeExceptionally 方法
     * @throws Exception
     */
    static void completeExceptionallyExample() throws Exception {
        final CompletableFuture<String> message = CompletableFuture.completedFuture("message") // 如果这里 completedFuture方法 将返回值覆给了一个变量 然后再用这个变量再thenApply...  和直接这样写不一样 （分开写相当于message这个completableFuture是一个完成的带有"message"的cf）
                .thenApplyAsync(s -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
//                .exceptionally(e -> "message exception" + e.getMessage());

        message.completeExceptionally(new RuntimeException("dadad")); // 以一个给定异常返回 如果completableFuture没结束的话

        System.out.println(message.get());

    }


    /**
     * 我们可以通过调用 cancel(boolean mayInterruptIfRunning)方法取消计算任务。此外，cancel()方法与 completeExceptionally(new CancellationException())等价。
     */
    static void cancelExample() {
        final CompletableFuture<String> cf1 = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });

        final CompletableFuture<String> cf2 = cf1.exceptionally(e -> "canceledMessage");

        cf1.cancel(true); // interrupt 这在running的任务
        // cancel与completeExceptionally等价 因为等价 所以在调用join之后 cancel也会走 exceptionally里的处理 （这里联想completeExceptionally）
        System.out.println("cf1.isCompletedExceptionally: " + cf1.isCompletedExceptionally());
        System.out.println(cf2.join());
    }

    /**
     * applyToEither
     * 当任意一个CompletableFuture完成的时候，fn会被执行，它的返回值会当作新的CompletableFuture<U>的计算结果。
     *
     * 类似的applyToEither 有 acceptEither 只不过任意一个CompletableFuture运行完之后，执行的都是一个consumer
     */
    static void applyToEitherExample() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase);

        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toLowerCase);

        CompletableFuture<String> finalCf = cf1.applyToEither(cf2, s -> s + " applyToEither make"); // 这里cf1 cf2任意一个完成之后 就会执行定义的func
        System.out.println(finalCf.join());
    }

    /**
     * 这里的runAfterBoth 和 thenAcceptBoth是指 两个CompletableFuture 都执行完成之后再执行后面的
     * runnable或者comsumer
     */
    static void runAfterBothExample() {
        String s1 = "MESSAGE";
        StringBuilder stringBuilder = new StringBuilder();

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.completedFuture(s1)
                .thenAcceptAsync(stringBuilder::append);

        CompletableFuture<Void> message = CompletableFuture.completedFuture("message")
                .thenAcceptAsync(stringBuilder::append);

        voidCompletableFuture.runAfterBoth(message, () -> stringBuilder.append("  afterBoth append")); //runAfterBoth 因为最后执行一个action 所以返回的都是void
        System.out.println(stringBuilder.toString());
    }

    /**
     * thenCombine 来整合两个异步计算的结果
     */
    static void thenCombineExample() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture("hello ")
                .thenCombine(CompletableFuture.completedFuture("world"), (s1, s2) -> s1 + s2);

        // thenCombien 内部连接的是同步的 可以直接getNow
        System.out.println(stringCompletableFuture.getNow(null));

        /**
         * 连接两个异步结果
         */
        String original = "meSSage";
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toLowerCase);

        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase);
        CompletableFuture<String> finalCf = stringCompletableFuture1.thenCombine(stringCompletableFuture2, (s1, s2) -> s1 + s2);

        String join = finalCf.join();// 因为是两个异步结果，所以这里要join去等待
        System.out.println(join);

    }

    /**
     * thenCompose 方法示例
     */
    static void thenComposeExample() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture("message")
                .thenApplyAsync(String::toUpperCase)
                .thenComposeAsync(upper -> CompletableFuture.completedFuture("messAge")
                        .thenApplyAsync(String::toLowerCase).thenApply(s -> {
                            try {
                                Thread.sleep(2999);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return upper + s;}));

        System.out.println(stringCompletableFuture.join()); // join阻塞等待
    }

    /**
     * anyOf 和 allOf
      */
    static void anyOfAndAllOfExample() {
        CompletableFuture<Void> a = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("a结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("b结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("c结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<Void> collect = Arrays.asList(a, b, c)
                .stream()
                .map(CompletableFuture::join).collect(Collectors.toList()); // 注意这里要有一个终端操作 流才会执行 流是惰性的。

        // allOf 所有执行完才会返回
        CompletableFuture<String> c1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); //
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "1";});
        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> "2");
        CompletableFuture<String> c3 = CompletableFuture.supplyAsync(() -> "3");

        CompletableFuture.allOf(new CompletableFuture[]{c1, c2, c3}).join(); // allOf返回的是CompletableFuture<Void>

        // 也要注意因为anyOf是多个CompletableFuture 任一返回的，并不确定返回值 所以返回了 CompletableFuture<Object>
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;});
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "1");

        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(integerCompletableFuture, stringCompletableFuture);

        System.out.println(objectCompletableFuture.join().getClass() == String.class);
    }

    /**
     * whenComplete demo
     */
    static void whenCompleteExample() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "hello world")
                .thenApplyAsync(s -> {
                    s = null;
                    int length = s.length();
                    return s + "：" + length;
                }).whenCompleteAsync((value, ex) -> {
                    try {
                        Thread.sleep(2000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (ex != null) {
                        System.out.println(Thread.currentThread().getName() + "遇到异常" + ex);
                    } else {
                        System.out.println(value);
                    }
                });
        stringCompletableFuture.join();
    }
}
