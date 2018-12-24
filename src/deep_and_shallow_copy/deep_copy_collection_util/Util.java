package deep_and_shallow_copy.deep_copy_collection_util;

import com.sun.istack.internal.Nullable;

import java.io.*;
import java.util.Collection;

/**
 * @author 夸克
 * @date 2018/12/24 20:32
 */
public class Util {

    /**
     * 对集合进行深拷贝
     *
     * @param src
     * @param <T>
     * @return
     */
    public @Nullable
    static <T> Collection<T> deepCopy(Collection<T> src) {

        if (null== src || src.isEmpty()) {
            return null;
        }

        try {

            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

            ObjectOutputStream out = new ObjectOutputStream(byteOut);

            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());

            ObjectInputStream in = new ObjectInputStream(byteIn);

            return  (Collection<T>) in.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
