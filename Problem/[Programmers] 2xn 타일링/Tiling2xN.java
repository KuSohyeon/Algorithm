import java.util.Scanner;

public class Tiling2xN {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(solution(sc.nextInt()));
	}

	private static int solution(int n) {
		int [] dp = new int[n+1];
        
        dp[0]=0;
        dp[1]=1;
        
        if(n<2) return 1;
        
        dp[2]=2;
        
        for(int i=3;i<=n;i++){
            dp[i] = (dp[i-1]+dp[i-2])%1000000007;
        }
        
        return dp[n];
	}
}
