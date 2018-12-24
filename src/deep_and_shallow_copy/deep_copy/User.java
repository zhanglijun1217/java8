package deep_and_shallow_copy.deep_copy;

/**
 * 浅拷贝
 *
 * @author 夸克
 * @date 2018/12/24 14:32
 */
public class User implements Cloneable{

    // Cloneable是一个标记型接口，不实现在进行拷贝的时候会报错

    private int number;

    /**
     * 引用对象
     */
    private Address address;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User(int number, Address address) {
        this.number = number;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "number=" + number +
                ", address=" + address +
                '}';
    }

    /**
     * 覆盖clone方法
     *     改为public
     *
     *     返回值为user
     *
     * @return
     */
    @Override
    public User clone() {
        User user = null;

        try {
            user = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // 对引用类型的成员变量进行clone
        user.address = address.clone();
        return user;
    }

    /**
     * test方法
     */
    public static void main(String[] args) {

        Address address = new Address("1111");
        User user1 = new User(1111, address);
        User user2 = user1.clone();

        System.out.println(user1);
        System.out.println(user2);

        // 修改user2的成员变量
        address.setAdd("2222");

        System.out.println("====================变更了address====================");

        // 当没有实现引用类型address的深拷贝时 user1 和 user2中的值还指向堆内存中同一个address对象
        System.out.println(user1);
        System.out.println(user2);




    }
}

