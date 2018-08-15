package default_method;

/**
 * @author 夸克
 * @date 10/10/2018 17:21
 */
public interface Child extends Parent {
    /**
     * 重写父接口中的default方法
     * @return
     */
    @Override
    default String doit() {
        return "Child";
    }
}
