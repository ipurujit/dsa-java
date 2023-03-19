package org.dsa.sorting;

import java.util.Comparator;
import java.util.List;

public interface IBaseSort<T> {

    List<T> sort(List<T> input, Comparator<T> comparator);

}
