package CalculatorEtc.ArrayCompare;

public class Main {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3};
        System.out.println(compareArrays(arr1, arr2));

        String[] arr3 = {"a", "b", "c"};
        String[] arr4 = {"a", "b", "c"};
        System.out.println(compareArrays(arr3, arr4));

        Integer[] arr5 = {1, 2, 3};
        Integer[] arr6 = {1, 2, 4};
        System.out.println(compareArrays(arr5, arr6));

        String[] arr7 = {"a", "b", "c"};
        String[] arr8 = {"a", "b", "d"};
        System.out.println(compareArrays(arr7, arr8));
    }

    private static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

}
