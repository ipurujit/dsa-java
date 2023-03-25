package org.dsa.util;

import java.util.List;

public class SwapUtil {
    private SwapUtil() {}
    public static <T> void swap(List<T> list, int idx1, int idx2) {
        T temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }
}
