package default_method;

/**
 * @author 夸克
 * @date 10/10/2018 17:17
 */
public interface Parent {
    default String doit() {
        return "Parent";
    }
}
