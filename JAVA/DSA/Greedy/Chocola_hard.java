import java.util.Arrays;
import java.util.Collections;

public class Chocola_hard {
    public static void main(String[] args) {
        
        int horLen = 6;
        int verLen = 4;

        Integer[] costHor = {4, 1, 2, 8, 3};       // horLen - 1 = 5
        Integer[] costVer = {2, 1, 3};             // verLen - 1 = 3

        Arrays.sort(costHor, Collections.reverseOrder());
        Arrays.sort(costVer, Collections.reverseOrder());

        int rowPieces = 1;
        int colPieces = 1;
        int i = 0, j = 0;
        int cost = 0;

        while (i < costHor.length && j < costVer.length) {
            if (costHor[i] >= costVer[j]) {
                cost += costHor[i] * colPieces;
                rowPieces++;
                i++;
            } else {
                cost += costVer[j] * rowPieces;
                colPieces++;
                j++;
            }
        }

        // Add remaining horizontal cuts
        while (i < costHor.length) {
            cost += costHor[i] * colPieces;
            i++;
        }

        // Add remaining vertical cuts
        while (j < costVer.length) {
            cost += costVer[j] * rowPieces;
            j++;
        }

        System.out.println("Minimum cost to cut chocolate: " + cost);
    }
}
