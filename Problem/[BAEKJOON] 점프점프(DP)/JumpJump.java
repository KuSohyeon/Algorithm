//[백준] 점프점프(DP)

import java.util.Arrays;
import java.util.Scanner;

public class JumpJump {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		if(N==1) { // 만약 미로가 1*1이면 바로 탈출
			System.out.println(0);
			return;
		}
		int [] arr = new int[N];
		int [] dp = new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
		}
		
		if(arr[0]==0) { // 만약 첫번째 칸이 점프할 수 없는 곳이면 -1 출력하고 종료
			System.out.println(-1); 
			return;
		}
		Arrays.fill(dp, Integer.MAX_VALUE);

		for(int i=1;i<=arr[0];i++) {
			dp[i]=1;
		}
		
		if(dp[N-1]!=Integer.MAX_VALUE) { // 한번에 갈 수 있는 곳이면 바로 종료
			System.out.println(dp[N-1]);
			return;
		}
		
		// DP
		for(int i=1;i<N;i++) {
			if(dp[i]!=Integer.MAX_VALUE && arr[i]!=0) {
				for(int j=1;j<=arr[i];j++) {
					if(i+j>=N) break;
					dp[i+j]=Math.min(dp[i+j], dp[i]+1);
				}
			}
		}
		
		// 출력
		System.out.println(dp[N-1]==Integer.MAX_VALUE?-1:dp[N-1]); 
	}
}
