import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class aa {
    static void func(long arr[], long cad, long vad, AtomicLong count, ArrayList<Long> arrlist) {
        if (arr[0] == cad && arr[1] == vad) return;

        if (arr[0] == arr[1]) {
            if (cad > vad) vgreatc(arr, count, arrlist);
            else cgreatv(arr, count, arrlist);
        } else if (arr[0] > cad) {
            cgreatv(arr, count, arrlist);
        } else if (arr[1] > vad) {
            vgreatc(arr, count, arrlist);
        }

        func(arr, cad, vad, count, arrlist);
    }

    static void cgreatv(long arr[], AtomicLong count, ArrayList<Long> arrlist) {
        long temp = arr[0] / 2;
        arr[0] = temp;
        arr[1] += temp;
        count.incrementAndGet();
        arrlist.add(1L);
    }

    static void vgreatc(long arr[], AtomicLong count, ArrayList<Long> arrlist) {
        long temp = arr[1] / 2;
        arr[1] = temp;
        arr[0] += temp;
        count.incrementAndGet();
        arrlist.add(2L);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();
        while (t-- > 0) {
            long k = sc.nextLong();
            long cad = sc.nextLong();

            long total = 1L << (k + 1);   // total cakes = 2^(k+1)
            long vad = total - cad;

            ArrayList<Long> arrlist = new ArrayList<>();
            long[] arr = {1L << k, 1L << k}; // initial distribution

            AtomicLong count = new AtomicLong(0);
            func(arr, cad, vad, count, arrlist);

            System.out.println(count.get());
            for (Long i : arrlist) System.out.print(i + " ");
            System.out.println();
        }
    }
}
