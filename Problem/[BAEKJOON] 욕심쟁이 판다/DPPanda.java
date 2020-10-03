//[백준] 욕심쟁이 판다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DPPanda {
	static int N,result;
	static int [][] map,dp;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				dfs(i,j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dp[i][j] > result)
					result = dp[i][j];
			}
		}
		
		System.out.println(result);
	}
	private static int dfs(int i, int j) {
		// 이미 탐색된 생존 경로에 대해서는 바로 그 값 리턴
		if(dp[i][j]>0) return dp[i][j];
		
		// 판다는 어떤 위치던 최소 1일은 생존 가능
		dp[i][j]=1;
		
		for(int d=0;d<4;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=N) continue;
			
			if(map[i][j]<map[ni][nj]) {
				dp[i][j]=Math.max(dp[i][j], dfs(ni,nj)+1);
			}
		}
		return dp[i][j];
		
	}
	
}
