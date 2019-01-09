package lt.zilinskas.marius.testio.utils;

import lombok.Getter;

@Getter
public class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String checkable) {
        return checkable == null || checkable.isEmpty();
    }
}
