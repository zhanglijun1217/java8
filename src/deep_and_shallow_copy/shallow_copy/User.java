package deep_and_shallow_copy.shallow_copy;

import java.util.ArrayList;
import java.util.List;

/**
 * 浅拷贝
 *
 * @author 夸克
 * @date 2018/12/24 14:32
 */
public class User implements Cloneable{

    // Cloneable是一个标记型接口，不实现在进行拷贝的时候会报错

    private int number;

    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 重写Object的clone方法
     *
     *      改修饰权限符为public
     *
     *      返回的Object类改为返回User类
     *
     *      直接调用super.clone() 这个native方法进行拷贝对象
     */

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    /**
     * test方法
     */
    public static void main(String[] args) {

        User user1 = new User();

        user1.setNumber(1111);
        user1.setName("1111");

        User user2 = null;
        // 实现浅拷贝
        try {
            user2 = user1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (null != user2) {
            // 变更浅拷贝出的对象中的成员变量
            user2.setNumber(2222);
            user2.setName("2222");
        }

        System.out.println(user1);// 输出1111，浅拷贝
        System.out.println(user2);


    }
}

