//[백준] 파이프 옮기기2(DP)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MovePipe2 {
	static long [][][] dp;
	static int [][] map;
	static int N;
	static long res;
	static int [] dy = {0,1,1};// 가로 세로 대각선
	static int [] dx = {1,0,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		
		map = new int[N][N];
		dp = new long[N][N][3];

		// 입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
//		초기화 작업 진행 -> 0,0은 넘겨도 됨
		dp[0][1][0] = 1;
//		상향식으로 모든 좌표에 최대 이동횟수 누적하기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
//				가로 방향
				if(j + 1 < N && map[i][j+1] == 0) { // 조건 처리
					// 이동할 수 있는 방법의 모든 수를 출력하는 거니까 이전에 방향 모두 누적
					dp[i][j+1][0] += dp[i][j][0] + dp[i][j][2]; // 가로 방향일 경우 세로는 제외
				}
//				세로방향
				if(i + 1 < N && map[i+1][j] == 0) {
					dp[i+1][j][1] += dp[i][j][1] + dp[i][j][2] ; // 세로 방향일 경우 가로는 제외
				}
//				대각선 방향 이면서 회전이 가능하면
				if(i+1 < N && j+1 < N && map[i+1][j+1] == 0 && map[i][j+1] == 0 && map[i+1][j] == 0) {
					dp[i+1][j+1][2] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2] ;
				}
			}
		}
		// 마지막 지점에 도착했을때 모든 방향고려 해야하므로 다 더해주기
		res = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2] ; 
		System.out.println(res);
	}
}
