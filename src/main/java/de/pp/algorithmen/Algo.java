package de.pp.algorithmen;

import java.util.ArrayList;
import java.util.List;

public class Algo {

    /**
     * Sorts a String Array in alphabetic order, with the Bucketsort principle. It
     * starts to put all Strings with the same letter at the beginning in different
     * Buckets. And then it sorts every bucket recursively. After that it
     * concatenates the sorted buckets alphabetically.
     *
     * @param array the array to be sorted.
     * @return The array but now sorted alphabetical.
     */
    public static String[] bucketSort(String[] array) {
        return bucketSort(array, 0);
    }

    private static String[] bucketSort(String[] array, int depth) {
        // The Termination Condition
        if (array.length < 2) {
            return array;
        }
        // Sorts all Strings in different buckets based on the starting letter
        List<String>[] buckets = new ArrayList[26];
        List<String> lostBuckets = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() <= depth) {
                lostBuckets.add(array[i]);
                continue;
            }
            int charToIndex = array[i].charAt(depth) - 97;
            if (buckets[charToIndex] == null) {
                buckets[charToIndex] = new ArrayList<>();
            }
            buckets[charToIndex].add(array[i]);
        }
        // Concatenates all buckets
        int pointer = lostBuckets.size();
        String[] result = new String[array.length];
        for (int i = 0; i < lostBuckets.size(); i++) {
            result[i] = lostBuckets.get(i);
        }
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                String[] bucketResult = bucketSort(buckets[i].toArray(new String[0]), depth + 1);
                for (int j = 0; j < bucketResult.length; j++) {
                    result[pointer] = bucketResult[j];
                    pointer++;
                }
            }
        }
        return result;
    }

    /**
     * Sorts an Array from small to big, with the Bubblesort variant. It takes the
     * first element and compares the next one to it. If its bigger it switches
     * both. Then it goes to the next one and do the Same until the array is sorted.
     *
     * @param array The array you want to sort.
     * @return The array sorted from small to big.
     */
    public static int[] bubbleSort(int[] array) {
        int tmp = 0;
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i + 1 < array.length; i++) {
                if (array[i] > array[i + 1]) {
                    tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                }
            }
        }
        return array;
    }

    /**
     * Sorts an Array from small to big, with the Selection Sort variant. It takes
     * the first element and compares every one to that. If one is smaller then it
     * saves it and from now it compares everyone to the newest smallest. Until you
     * are at the end of the array. Then it switches the smallest with the first
     * one. After that its takes the second one and compares that again to every
     * Element except for the first one.
     *
     * @param array An array you want to sort.
     * @return The array sorted from small to big.
     */
    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int smallest = array[i];
            int smallestPointer = i;
            for (int j = i; j < array.length; j++) {
                if (smallest > array[j]) {
                    smallest = array[j];
                    smallestPointer = j;
                }
            }
            int tmp = array[i];
            array[i] = array[smallestPointer];
            array[smallestPointer] = tmp;
        }
        return array;
    }

    /**
     * Sorts an Array from small to big, with the Merge Sort variant. It splits the
     * array in the middle until only one Element is left. Then it starts to merge
     * the Arrays and sorts then into the right order.
     *
     * @param array An array you want to sort.
     * @return The array sorted from small to big.
     */
    public static int[] mergeSort(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int[] left = new int[array.length / 2];
        int[] right = new int[array.length - array.length / 2];
        for (int i = 0; i < array.length; i++) {

            if (i >= array.length / 2) {
                right[i - array.length / 2] = array[i];
            } else {
                left[i] = array[i];
            }
        }
        left = mergeSort(left);
        right = mergeSort(right);
        int pointerLeft = 0;
        int pointerRight = 0;
        int[] result = new int[left.length + right.length];
        for (int i = 0; i < result.length; i++) {
            if (pointerLeft == left.length) {
                result[i] = right[pointerRight];
                pointerRight++;
            } else if (pointerRight == right.length) {
                result[i] = left[pointerLeft];
                pointerLeft++;
            } else if (left[pointerLeft] < right[pointerRight]) {
                result[i] = left[pointerLeft];
                pointerLeft++;
            } else {
                result[i] = right[pointerRight];
                pointerRight++;
            }
        }
        return result;
    }

    /**
     * Sorts an Array from small to big. With the Quick Sort variant. It picks a
     * random pivot in the Array and splits it into two. Then picks in both another
     * random number and splits that again into two. Until both arrays have only one
     * Element left. Then it takes one Pivot and one Array and merges and sorts
     * them. After that it takes the next Array with one element and merges it with
     * the array from before.
     *
     * @param array An array you want to sort.
     * @return The array sorted from small to big.
     */
    public static int[] quickSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int random = (int) (Math.random() * array.length);
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        int pivot = array[random];
        for (int i = 0; i < array.length; i++) {
            if (i == random) {
                continue;
            }
            if (array[i] < pivot) {
                leftList.add(array[i]);
            } else {
                rightList.add(array[i]);
            }
        }
        int[] left = quickSort(leftList.stream().mapToInt(i -> i).toArray());
        int[] right = quickSort(rightList.stream().mapToInt(i -> i).toArray());
        int[] result = new int[array.length];
        int i = 0;
        if (left != null) {
            for (; i < left.length; i++) {
                result[i] = left[i];
            }

        }
        result[i] = pivot;
        i++;
        if (right != null) {
            for (int j = 0; j < right.length; j++) {
                result[i] = right[j];
                i++;
            }
        }
        return result;
    }

    /**
     * Greatest common divisor with the Euklid principle(old Greek). The recursive
     * version.
     *
     * @param a
     * @param b
     * @return The greatest common divisor.
     */
    public static int ggTEuklidR(int a, int b) {
        int rest = a % b;
        if (rest != 0) {
            return ggTEuklidR(b, rest);
        }
        return b;
    }

    /**
     * Greatest common divisor with the Euklid principle(oldGreek).
     *
     * @param a
     * @param b
     * @return The greatest common divisor.
     */
    public static int ggTEuklid(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        int vorherigeZahl = b;
        int temp = 0;
        int rest = a % vorherigeZahl;
        while (rest != 0) {
            temp = rest;
            rest = vorherigeZahl % rest;
            vorherigeZahl = temp;
        }
        return vorherigeZahl;
    }

    /**
     * Greatest common divisor with prime factorization.
     *
     * @param a
     * @param b
     * @return
     */
    public static int ggT(int a, int b) {
        int ggT = 1;
        ArrayList<Integer> aList = primeFactorization(a);
        ArrayList<Integer> bList = primeFactorization(b);
        int pointerA = 0;
        int pointerB = 0;
        while (aList.size() > pointerA && bList.size() > pointerB) {
            if (aList.get(pointerA) < bList.get(pointerB)) {
                pointerA++;
            } else if (aList.get(pointerA) > bList.get(pointerB)) {
                pointerB++;
            } else {
                ggT = ggT * aList.get(pointerA);
                pointerA++;
                pointerB++;
            }
        }
        return ggT;
    }

    /**
     * Prime factorization
     *
     * @param a
     * @return All Prime numbers
     */
    public static ArrayList<Integer> primeFactorization(int a) {
        ArrayList<Integer> temp = new ArrayList<>();
        int tempResult = a;
        int zahl = 2;
        while (!isPrime(tempResult)) {
            if (tempResult % zahl == 0) {
                tempResult = tempResult / zahl;
                temp.add(zahl);
            } else {
                zahl = getNextPrime(zahl);
            }
        }
        if (isPrime(tempResult)) {
            temp.add(tempResult);
        }
        return temp;
    }

    /**
     * Returns the next Prime number.
     *
     * @param prime
     * @return the nearest prime number.
     */
    public static int getNextPrime(int prime) {
        boolean teilbarDurchAllesAndere = true;
        int nextPrime = prime;
        while (teilbarDurchAllesAndere) {
            nextPrime++;
            if (isPrime(nextPrime)) {
                teilbarDurchAllesAndere = false;
            }

        }

        return nextPrime;
    }

    public static int getNextPrimeTwoPointZero(int prime) {
        int nextPrime = ++prime;
        while (true) {
            if (isPrime(nextPrime)) {
                return nextPrime;
            }
            nextPrime++;
        }
    }

    /**
     * Checks if a number is a prime number.
     *
     * @param number the number you want to check.
     * @return true if the number is a prime number.
     */
    public static boolean isPrime(int number) {
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * The fibonacci algorithmus
     *
     * @param n
     * @return
     */
    public static long fib(long n) {
        long temp = 1;
        long temp2 = 1;
        long ergebnis = 1;
        for (int i = 1; i < n - 1; i++) {
            ergebnis = temp + temp2;
            temp2 = temp;
            temp = ergebnis;
        }
        return ergebnis;
    }

    /**
     * The fibonacci algorithmus recursive.
     *
     * @param n
     * @return
     */
    public static long fibr(long n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibr(n - 1) + fibr(n - 2);
    }
}
