package java琐碎知识;

import java.util.concurrent.TimeUnit;

/**
 * Created by zlj on 2020/3/15.
 */
public class 多线程设置变量 {

    static boolean flag = true;

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println(flag);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(false);
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
                flag = false;
                System.out.println("2 end");}).start();

    }
}
