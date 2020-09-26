//[백준] 쉬운 계단 수(DP)

/*************************************
dp[n]에는 n자리계단수의 총 수가 저장된다.

n자리 계단수의 끝에는 0부터 9까지 올수있기 때문에 dp[n]은 다음과같이 표현할수있다.

dp[n]= dp[n]끝에 0부터9가오는 10개의문제들의합이다.   --->  dp[n] = dp[n][0]~dp[n][9] 의 합

이제 쪼갠 문제들이 어떻게 합쳐지는 지 생각해보자  ((( dp[n][k]  n자리 계단수끝의 수는k이다. )))

dp[n][0]의 경우 dp[n-1][1] 밖에 올수없다.  xxx0 다음에 올수있는수는 1밖에없다.

dp[n][1]의 경우 dp[n-1][0] 과 dp[n-1][2] 두가지가있다.  xxxx1은  xxxx12 또는 xxxx10

.

.

dp[n][8]의 경우 dp[n-1][7] 과 dp[n-1][9] 두가지. (0과 9를 뺀나머지경우는 앞뒤로 1씩작은수가올수있음)

dp[n][9]의 경우 dp[n-1][8] 만 올수있다.

맨 끝자리가 0인경우와 9인경우는 따로 처리해줘야함을 알수있다.
 *****************************************/

import java.util.Scanner;

public class EasyNumOfStairs {
	static int mod=1000000000;
	static long [][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		dp = new long[101][10];
		
		for(int i=1;i<10;i++) {
			dp[1][i]=1; //1자리 수들은 1로 채워주기(단, 0은 시작할 수 없음)
		}
		for(int i=2;i<=N;i++) {
			dp[i][0]=dp[i-1][1]%mod; //0의 경우에는 n-1자리수 중 1 뒤에밖에 못옴
			dp[i][9]=dp[i-1][8]%mod; //9의 경우에는 n-1자리수 중 8 뒤에 밖에 못옴
			
			for(int j=1;j<=8;j++) { // 나머지는 2가지 경우가 있음(큰 수, 작은 수)
				dp[i][j]=(dp[i-1][j-1]+dp[i-1][j+1])%mod;
			}
		}
		
		long res =0;
		for(int i=0;i<=9;i++) {  // dp[n]= dp[n]끝에 0부터9가오는 10개의문제들의합
			res = (res + dp[N][i]) % mod; 
		}
		System.out.println(res);
		
	}
}
