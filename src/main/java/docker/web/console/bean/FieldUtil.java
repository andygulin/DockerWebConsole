package docker.web.console.bean;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldUtil {

    private FieldUtil() {
        throw new UnsupportedOperationException();
    }

    public static List<String> getHeader(Class<?> clazz) {
        List<String> headers = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Header header = field.getAnnotation(Header.class);
            if (header != null) {
                String name = header.name();
                if (StringUtils.isEmpty(name)) {
                    name = field.getName();
                }
                headers.add(name);
            } else {
                headers.add(field.getName());
            }
        }
        return headers;
    }

    public static String[] getIgnoreHeader(Class<?> clazz) {
        List<String> headers = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            IgnoreProperty ignoreProperty = field.getAnnotation(IgnoreProperty.class);
            if (ignoreProperty != null) {
                headers.add(field.getName());
            }
        }
        return headers.toArray(new String[0]);
    }
}