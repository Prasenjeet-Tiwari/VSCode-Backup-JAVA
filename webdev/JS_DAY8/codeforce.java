import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class codeforce{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());  // test cases

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            long target = Long.parseLong(st.nextToken());

            ArrayList<Long> list = new ArrayList<>();

            st = new StringTokenizer(br.readLine()); // read array
            for (int i = 0; i < len; i++) {
                list.add(Long.parseLong(st.nextToken()));
            }

            // sort list
            Collections.sort(list);

            long count = 0;
            int start = 0, end = len - 1;

            while (start <= end) {
                long sum = list.get(start) + list.get(end);

                if (start == end) {
                    if (sum == target) count++;
                    break;
                } else if (sum == target) {
                    count++;
                    start++;
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }

            System.out.println(count);
        }
    }
}
