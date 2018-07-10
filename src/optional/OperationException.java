package optional;

/**
 * @author 夸克
 * @create 2018/7/10 16:27
 */
public class OperationException extends RuntimeException {

    public OperationException() {

    }

    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable e) {
        super(message, e);
    }

    @Override
    public String getMessage() {
        return "No value present in the Optional instance";
    }
}
