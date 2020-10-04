//[SWEA] 팩토리얼과 최대공약수
import java.util.Scanner;
 
public class FacNGcd {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
             
            int result = 1;
            for (int l = Math.min(N, K); l >= 2; l--) {
                if (K % l == 0) {
                    result *= l;
                    K /= l;
                     
                    if (K == 1) {
                        break;
                    }
                }
            }
            System.out.println("#" + t + " " + result);
        }
    }
}