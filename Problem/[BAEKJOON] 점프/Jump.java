//[백준] 1890번 : 점프

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jump {
	static int N;
	static int[][] map;
	static long [][] dp;
	static int[] dy = { 1, 0 };// 하 우
	static int[] dx = { 0, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long [N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 1;
		int ni = 0;
		int nj = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(dp[i][j]==0 || map[i][j]==0) continue;
				int num = map[i][j];
				for(int d=0;d<2;d++) {
					ni = i + num * dy[d];
					nj = j + num * dx[d];
					if(ni>=N || nj>=N) continue;
					dp[ni][nj] += dp[i][j]; // 새 도착지에 기존의 경로만큼 더해주기
				}
			}
		}
		
		
		System.out.println(dp[N-1][N-1]);

	}
}
