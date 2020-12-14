//[백준] 계단오르기 (DP)

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClimbingStairs {
	static int N;
	static int [] stairs, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		stairs = new int[301];
		dp = new int[301];
		
		for(int i=0;i<N;i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		// 300이하의 자연수라서 2보다 작을 경우 여기서 런타임에러가 날 수 있음
		dp[0] = stairs[0];
		dp[1] = stairs[0]+stairs[1];
		dp[2] = Math.max(stairs[1]+stairs[2], stairs[0]+stairs[2]);
		
		
		for(int i=3;i<N;i++) {
			// 전전 칸을 밟고 현재칸을 밟는 경우
			// 전전전 칸을 밟고 전칸 밟고 현재칸을 밟는 경우
			dp[i]=Math.max(dp[i-2]+stairs[i], dp[i-3]+stairs[i-1]+stairs[i]);
		}
		
		System.out.println(dp[N-1]);
	
	}
}
