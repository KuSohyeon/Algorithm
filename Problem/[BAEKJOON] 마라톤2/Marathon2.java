//[백준] 마라톤2
// https://train-validation-test.tistory.com/entry/%EB%B0%B1%EC%A4%80-10653-%EB%B2%88-Gold-V-%EB%A7%88%EB%9D%BC%ED%86%A42-Solved-By-Ja

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Marathon2 {
	static int N, K;
	static int [][] checkPoint,arr, memo;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		checkPoint = new int[N+1][2];
		
		for(int i=1;i<=N;i++) { // 1부터 N-1번까지 반복
			st = new StringTokenizer(br.readLine());
			checkPoint[i][0] = Integer.parseInt(st.nextToken());
			checkPoint[i][1] = Integer.parseInt(st.nextToken());
		}
		
		arr = new int[N+1][N+1];
		
		for(int i=1;i<N;i++) {
			for(int j=i+1;j<=N;j++) {
				arr[i][j] = Math.abs(checkPoint[i][0]-checkPoint[j][0]) + Math.abs(checkPoint[i][1]-checkPoint[j][1]);
			}
		}
		
		memo = new int[N+1][K+1];
		
		System.out.println(check(N,K));
		
	}
	private static int check(int n, int k) {
		if(memo[n][k]!=0) return memo[n][k];
		if(n==1) return 0;
		memo[n][k]=Integer.MAX_VALUE;
		for(int i=0;i<=k;i++) {
			if(n-i>1) memo[n][k]=Math.min(check(n-i-1,k-i)+arr[n-i-1][n], memo[n][k]);
		}
		return memo[n][k];
	}
}
