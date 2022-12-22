import java.util.Arrays;

public class Array2 {
    static void sort(int[] input) {
        int n = input.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (input[j] < input[min])
                    min = j;

            int tmp = input[min];
            input[min] = input[i];
            input[i] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array1 = {3, 2, 4, 1, 5};
        System.out.println(Arrays.toString(array1));
        sort(array1);
        System.out.println(Arrays.toString(array1));
    }
}