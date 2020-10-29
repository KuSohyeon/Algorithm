import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SupplyRoute_Dijkstra_PQ {
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
		
		// int [] : {r,c,cost}
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2]; // 오름차순의 결과
			}
		});
		
		minTime[startX][startY] = 0;
		
		pq.offer(new int[] {startX,startY,0});
		
		boolean flag = true;
		int r=0,c=0,cost=0,nr,nc,current [];
		while(flag) {
			//1. 미방문 정점중에 최소비용의 정점 찾기
			current = pq.poll();
			r = current[0];
			c = current[1];
			cost = current[2];
			
			if(visited[r][c]) continue;
			
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
					// 갱신된 최소비용을 PQ에 넣는다.
					pq.offer(new int[] {nr,nc,minTime[nr][nc]});
				}
			}
		}
		
		return minTime[endX][endY];
	}
}
