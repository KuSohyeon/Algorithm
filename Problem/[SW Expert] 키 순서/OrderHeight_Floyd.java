//[SW Expert] [Professional] 키 순서

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OrderHeight_Floyd {
	static int N,M,result;
	static int [][] adj;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			result = 0; // tc마다 초기화
			N = Integer.parseInt(br.readLine()); 
			M = Integer.parseInt(br.readLine()); 
			
			adj = new int[N+1][N+1];
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine().trim());
				int r = Integer.parseInt(st.nextToken()); 
				int c = Integer.parseInt(st.nextToken()); 
				adj[r][c]=1;
			}
			
			// 플로이드-와샬로 구현
			for(int k=1;k<=N;k++) {
				for(int i=1;i<=N;i++) {
					if(i==k) continue;
					for(int j=1;j<=N;j++) {
						if(i==j || j== k || adj[i][j]==1) continue;
						if(adj[i][k]==1 && adj[k][j]==1) {
							adj[i][j]=1;
						}
					}
				}
			}
			
			// 가로 -> 나보다 큰 애들 | 세로 -> 나보다 작은 애들
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					adj[i][0] += adj[i][j];
					adj[0][j] += adj[i][j];
				}
			}
			
			// 나보다 큰 애 + 나보다 작은 애 = 나 빼고 인원 수이면 내 위치를 알 수 있음
			for(int i=1;i<=N;i++) {
				if(adj[i][0] + adj[0][i] == N-1) {
					result++;
				}
			}
			
			// 출력
			System.out.println("#" + tc + " " + result);
		}
	}
}
