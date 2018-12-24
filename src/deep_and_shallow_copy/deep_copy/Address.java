package deep_and_shallow_copy.deep_copy;

/**
 * User中的内嵌类 test深拷贝
 *
 * @author 夸克
 * @date 2018/12/24 15:11
 */
public class Address implements Cloneable {

    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }


    public Address(String add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "Address{" +
                "add='" + add + '\'' +
                '}';
    }

    @Override
    public Address clone()  {
        // 实现clone方法
        Address address = null;
        try {
            address  = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return address;
    }
}
