import java.util.ArrayList;

public class Array1 {
    static ArrayList<Integer> contains(int[] arr1, int[] arr2) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int x : arr1) {
            for (int y : arr2) {
                if (x == y) tmp.add(x);
            }
        }
        return tmp;
    }
    public static void main(String[] args) {
        int[] array1 = {2, 3, 4, 5};
        int[] array2 = {1, 3, 5, 7};

        System.out.println(contains(array1,array2));
    }
}