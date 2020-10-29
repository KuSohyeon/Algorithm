import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SupplyRoute_Dijkstra {
	static int N, INF = Integer.MAX_VALUE;
	static int [][] map;
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int tc=1;tc<=T;tc++) {

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];

			for(int i=0;i<N;i++) {
				char [] ch = br.readLine().toCharArray();
				for(int j=0;j<N;j++) {
					map[i][j]=ch[j]-'0';
				}
			}
			
			System.out.println("#"+tc+" "+dijkstra(0,0,N-1,N-1));
		}
	}
	private static int dijkstra(int startX, int startY, int endX, int endY) {

		boolean [][] visited = new boolean[N][N];
		int [][] minTime = new int[N][N];
		for(int i=0;i<N;i++) {
			Arrays.fill(minTime[i], INF);
		}
		
		minTime[startX][startY] = 0;
		
		boolean flag = true;
		int r=0,c=0,cost=0,nr,nc;
		while(flag) {
			//1. 미방문 정점중에 최소비용의 정점 찾기
			cost = INF;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j] && cost>minTime[i][j]) {
						cost=minTime[i][j];
						r = i;
						c = j;
					}
				}
			}
			
			visited[r][c]=true;
			// 선택된 정점이 도착정점이면 끝냄
			if(visited[endX][endY]) break;
			
			// 2. 선택된 정점을 경유지로 하여 미방문 정점들의 최소비용 갱신
			for(int d=0;d<4;d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
				if(minTime[nr][nc]>cost+map[nr][nc]) {
					minTime[nr][nc]=cost+map[nr][nc];
				}
			}
		}
		
		return minTime[endX][endY];
	}
}
