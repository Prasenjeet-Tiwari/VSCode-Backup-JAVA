package DSA.Array;

public class Trapping_water {
    public static int water(int array[]) {
        int n = array.length;

        int left_max[] = new int[n];
        left_max[0] = array[0];
        for (int i = 1; i < n; i++) {
            left_max[i] = Math.max(array[i], left_max[i - 1]);
        }

        int right_max[] = new int[n];
        right_max[n - 1] = array[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            right_max[j] = Math.max(array[j], right_max[j + 1]);
        }
        for(int a : right_max)
         System.out.print(a);
         System.out.println();
        for(int b : left_max){
            System.out.print(b);
        }

        int trapped_water = 0;
        for (int k = 0; k < n; k++) {
            int calculative_height = Math.min(left_max[k], right_max[k]);
            int final_height = calculative_height - array[k];
            trapped_water += final_height > 0 ? final_height : 0;
        }
        return trapped_water;
    }

    public static void main(String args[]) {
        int array_height_of_tower[] = {4, 2, 0, 6, 3, 2, 5};
        System.out.println("Maximum water that can be stored is: " + water(array_height_of_tower));
    }
}
