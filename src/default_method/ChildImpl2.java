package default_method;

/**
 * ChildImpl既实现了Child接口又继承了ParentImpl2类
 * 为了验证 父接口和父类中都存在default方法时的执行 （优先使用父类中重写的default方法）
 * @author 夸克
 * @date 10/10/2018 17:24
 */
public class ChildImpl2 extends ParentImpl2 implements Child {
}
