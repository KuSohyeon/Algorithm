//[백준] 이동하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MoveDP {
	static int N,M;
	static int [][] map, candy;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		candy = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				
				int tmp = 0;
				if(i==0 && j==0) { // (0,0)인 경우는 따로 넣어주기
					tmp=map[i][j];
				}
				// 각 경로로 올때 비교해서 최대값으로 넣어주기
				if(i-1>=0) {
					tmp = Math.max(tmp, map[i][j]+candy[i-1][j]) ; 
				}
				if(j-1>=0) {
					tmp =  Math.max(tmp, map[i][j]+candy[i][j-1]) ;
				}
				if(i-1>=0 && j-1>=0) {
					tmp =  Math.max(tmp, map[i][j]+candy[i-1][j-1]) ;
				}
				
				candy[i][j] = tmp;
			}
		}
		
		// 출력
		System.out.println(candy[N-1][M-1]);
	}
}
