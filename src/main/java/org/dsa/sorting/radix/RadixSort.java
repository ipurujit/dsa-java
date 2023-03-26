package org.dsa.sorting.radix;

import org.dsa.sorting.IBaseSort;

import java.util.*;
import java.util.logging.Logger;

public class RadixSort<T extends Number> implements IBaseSort<T> {
    private static final Logger log = Logger.getLogger(RadixSort.class.getName());
    private static final Byte MIN_MAP_KEY = (byte) -1;
    private static final Byte MAX_MAP_KEY = (byte) 1;
    private static final Byte DIGIT_SAMPLE_SET_SIZE = 10;

    // Formatter docs: https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax
    private String numberToSignedStringWithPaddedZero(Long number, int digitCount) {
        String format = "%0" + digitCount + "d"; // pad zeros, negative numbers get -
        return String.format(format, number);
    }

    private String numberToSignedString(Long number) {
        return String.format("%+d", number);
    }

    private Map<Character, List<String>> generateDigitMapper() {
        Map<Character, List<String>> map = new HashMap<>(DIGIT_SAMPLE_SET_SIZE);
        map.put('-', new LinkedList<>()); // for negative numbers!
        for(byte idx = 0; idx < DIGIT_SAMPLE_SET_SIZE; idx++) {
            // linked list because we won't try to get at particular index
            map.put((char)('0' + idx), new LinkedList<>());
        }
        return map;
    }

    private List<String> processDigitMapperAfterIteration(Map<Character, List<String>> map) {
        // handle negative numbers first
        Collections.reverse(map.get('-'));
        List<String> result = new LinkedList<>(map.get('-'));
        for(byte idx = 0; idx < DIGIT_SAMPLE_SET_SIZE; idx++) {
            List<String> list = map.get((char)('0' + idx));
            if (!list.isEmpty()) {
                result.addAll(list);
            }
        }
        return result;
    }

    private List<String> sortByIndex(int currentDigitIndex, List<String> numbers) {
        Map<Character, List<String>> map = generateDigitMapper();
        for(String number: numbers) {
            if (number.length() - currentDigitIndex > 0) {
                // we can consume digit in this string
                char c = number.charAt(number.length() - 1 - currentDigitIndex);
                map.get(c).add(number);
            } else {
                // we can't consume this string - it is over already!
                log.warning(() -> "Unable to process number = " + number);
            }
        }
        return processDigitMapperAfterIteration(map);
    }

    private Map<Byte, String> findMinMax(List<T> listToSort) {
        Map<Byte, Long> minMax = new HashMap<>();
        listToSort.stream()
                .map(Number::longValue)
                .forEach(number -> {
                    if (!minMax.containsKey(MIN_MAP_KEY) || minMax.get(MIN_MAP_KEY) > number) {
                        minMax.put(MIN_MAP_KEY, number);
                    }
                    if (!minMax.containsKey(MAX_MAP_KEY) || minMax.get(MAX_MAP_KEY) < number) {
                        minMax.put(MAX_MAP_KEY, number);
                    }
                });
        return Map.of(
                MIN_MAP_KEY, numberToSignedString(minMax.getOrDefault(MIN_MAP_KEY, 0L)),
                MAX_MAP_KEY, numberToSignedString(minMax.getOrDefault(MAX_MAP_KEY, 0L))
        );
    }

    private void radixSort(List<T> listToSort) {
        if (listToSort.isEmpty()) {
            return;
        }
        // use the long value to sort
        Map<Byte, String> minMax = findMinMax(listToSort);
        // find num of digits
        int digitCount = Math.max(minMax.get(MIN_MAP_KEY).length(), minMax.get(MAX_MAP_KEY).length());
        // for each digit, sort for each digit
        List<String> result = listToSort.stream()
                .map(Number::longValue)
                .map(number -> numberToSignedStringWithPaddedZero(number, digitCount))
                .toList();
        for(int currentDigitIndex = 0; currentDigitIndex < digitCount; currentDigitIndex++) {
            result = sortByIndex(currentDigitIndex, result);
        }
        // update the list received in place
        listToSort.clear();
        result.stream().map(Long::valueOf).forEach(number -> listToSort.add((T) number));
    }

    @Override
    public List<T> sort(List<T> input, Comparator<T> comparator) {
        // comparator not in use!
        log.entering(this.getClass().getName(), "sort");
        // clone input
        List<T> listToSort = new ArrayList<>(input);
        radixSort(listToSort);
        log.exiting(this.getClass().getName(), "sort");
        return listToSort;
    }
}
