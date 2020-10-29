//[SW Expert] 보급로
// BFS(우선수위큐)사용

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SupplyRoute_BFS {
	static int N,result;
	static int [][] map;
	static int [][] v; 
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static class Data implements Comparable<Data>{
		int i;
		int j;
		int cnt;
		int cost;
		public Data(int i, int j, int cnt, int cost) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.cost = cost;
		}
		@Override
		public int compareTo(Data o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			result = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			v = new int[N][N];
			
			for(int i=0;i<N;i++) {
				char [] ch = br.readLine().toCharArray();
				for(int j=0;j<N;j++) {
					map[i][j]=ch[j]-'0';
					v[i][j]=Integer.MAX_VALUE; //int형 방문체크 사용하기 위해 max값으로 초기화
				}
			}
			
			bfs();
			
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void bfs() {
		PriorityQueue<Data> pq = new PriorityQueue<Data>();
		
		pq.offer(new Data(0,0,1,0));
		v[0][0]++;
		
		while(!pq.isEmpty()) {
			Data cur = pq.poll();
			
			if(cur.i == N-1 && cur.j ==N-1) {
				result = cur.cost;
			}
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || v[ni][nj] <=cur.cnt ) continue; //이전에 지나왔던 길의 갯수보다 v가 더 작다면 이미 지나왔을 것이니까 못가게 막기
				v[ni][nj]++; // 방문체크
				pq.offer(new Data(ni,nj,cur.cnt+1,cur.cost+map[ni][nj]));
			}
		}
		
	}
}
