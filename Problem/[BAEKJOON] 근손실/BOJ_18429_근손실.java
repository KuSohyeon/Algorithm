import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18429_근손실 {
    public static int N, K, count;
    public static int[] kits;
    public static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        kits = new int[N];
        v = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        perm(0, 500);

        System.out.println(count);
    }

    private static void perm(int cnt, int weight) {
        if (cnt == N) {
            count++;
        }
        for (int i = 0; i < N; i++) {
            if (v[i] || weight - K + kits[i] < 500) continue;
            v[i] = true;
            perm(cnt+1, weight - K + kits[i]);
            v[i] = false;
        }
    }
}
