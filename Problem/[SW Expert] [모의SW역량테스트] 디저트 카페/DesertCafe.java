//[SWEA] [모의SW역량테스트] 디저트 카페
// dfs 백트래킹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DesertCafe {
	static int N, result,startI, startJ;
	static int [][] map;
	static boolean [] cafe;
	static boolean [][] v;	
	static int [] dy = {1,1,-1,-1}; // 우하 좌하 좌상 우상
	static int [] dx = {1,-1,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			result = -1; //tc마다 초기화

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];

			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			v = new boolean[N][N]; // 방문체크 배열
			cafe = new boolean[101];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					startI=i; // 시작 위치 정해줘야함!!!
					startJ=j; 
					dfs(i,j,0,0); // 점마다 dfs 탐색
				}
			}

			System.out.println("#"+tc+" "+result);
		}
	}
	// dfs + 백트래킹 사용
	private static void dfs(int i, int j, int cnt, int d) {
		if(d==3 && i==startI && j==startJ) { // 원래 자리로 돌아왔다면 result 갱신 후 return
			result = Math.max(result, cnt);
			return;
		}
		i += dy[d];
		j += dx[d];
		
		if(i<0 || i>=N || j<0 || j>=N ) return;
		if(v[i][j] || cafe[map[i][j]]) return;
		
		v[i][j]=true;
		cafe[map[i][j]]=true;
		
		dfs(i,j,cnt+1,d); // 쭉 가는 경우
		if(d!=3) {
			dfs(i,j,cnt+1,d+1); // 방향 전환하는 경우
		}
		// 백트래킹
		v[i][j]=false;
		cafe[map[i][j]]=false;

	}
}


