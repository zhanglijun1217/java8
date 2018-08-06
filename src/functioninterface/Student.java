package functioninterface;

/**
 * @author 夸克
 * @create 2018/8/6 19:57
 */
public class Student {

    String name;
    Double grade;
    Double feeDiscount = 0.0;
    Double baseFee = 2000.0;


    public Student() {}
    public Student(String name, Double grade) {
        this.name = name;
        this.grade = grade;
    }

    public void printFee() {
        System.out.println(baseFee - feeDiscount * baseFee /100);
    }
}
