package se.yrgo;

import java.util.List;

public final class Utils {
    private Utils() {}
    
    public static <T> void inplaceReverse(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            T temp = list.get(i);
            list.set(i, list.get(size - i - 1));
            list.set(size - i - 1, temp);
        }
    }
}
