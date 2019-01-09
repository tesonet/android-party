package lt.zilinskas.marius.testio.utils;

import java.util.Collection;

import lombok.Getter;

@Getter
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection checkable) {
        return checkable == null || checkable.isEmpty();
    }
}
