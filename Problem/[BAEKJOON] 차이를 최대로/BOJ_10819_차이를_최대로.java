import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10819_차이를_최대로 {
    static int N, max;
    static int [] arr, select;
    static boolean [] isSelected;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        select = new int[N];
        isSelected = new boolean[N];
        max = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);

        System.out.println(max);

    }

    private static void perm(int cnt) {
        if(cnt == N) {
            max = Math.max(max, cal());
            return;
        }
        for(int i=0;i<N;i++){
            if(isSelected[i]) continue;
            isSelected[i] = true;
            select[cnt] = arr[i];
            perm(cnt+1);
            isSelected[i] = false;
        }
    }

    private static int cal() {
        int result = 0;
        for(int i=0;i<N-1;i++) {
            result += Math.abs(select[i] - select[i+1]);
        }
        return result;
    }
}
