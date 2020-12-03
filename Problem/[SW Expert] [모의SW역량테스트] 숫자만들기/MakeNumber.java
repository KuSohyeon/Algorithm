//[SWEA] [모의 SW 역량테스트] 숫자만들기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class MakeNumber {
    static int N,min,max,result;
    static int [] input, cnt, op;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
         
        for(int tc=1;tc<=T;tc++) {
            result = 0;// tc마다 초기화
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
             
            N = Integer.parseInt(br.readLine());
            input = new int[N];
            cnt = new int[4];
            op = new int[N-1];
             
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<4;i++) {
                int num = Integer.parseInt(st.nextToken());
                cnt [i] = num;
            }
             
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) {
                input[i]=Integer.parseInt(st.nextToken());
            }
             
            perm(0);
            result = max-min;
            System.out.println("#"+tc+" "+result);
        }
         
    }
    // 일반 순열로 생각하면 시간초과 -> 중복까지 생각해주기
    private static void perm(int idx) {
        if(idx==N-1) {
            cal(); // 순열 생성 후 계산하러 가기
            return;
        }
        for(int i=0;i<4;i++) {
            if(cnt[i]<=0) continue;
            cnt[i]--;
            op[idx] = i;
            perm(idx+1);
            cnt[i]++;
        }
    }
 
    private static void cal() {
        String s1 = "";
        String s2 = "";
        int tmp = 0;
        s1 += input[0];
        for(int i=0;i<N-1;i++) {
            s2 = ""+input[i+1];
            switch(op[i]) {
                case 0:
                    tmp = Integer.valueOf(s1)+Integer.valueOf(s2);
                    s1 = ""+tmp;
                    break;
                case 1:
                    tmp = Integer.valueOf(s1)-Integer.valueOf(s2);
                    s1 = ""+tmp;
                    break;
                case 2:
                    tmp = Integer.valueOf(s1)*Integer.valueOf(s2);
                    s1 = ""+tmp;
                    break;
                case 3:
                    tmp = Integer.valueOf(s1)/Integer.valueOf(s2);
                    s1 = ""+tmp;
                    break;
            }
 
        }
         
        max = Math.max(max, tmp);
        min = Math.min(min, tmp);
    }
}