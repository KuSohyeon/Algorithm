//[백준] - 벽부수고 이동하기2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BreakWall2 {
	static int [][] map;
	static int [][] v;
	static int N,M,K;
	static int result;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int [N][M];
		v = new int[N][M];

		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(str.substring(j, j+1));
				v[i][j]=Integer.MAX_VALUE;
			}
		}

		result = -1;

		bfs(new Data(0,0,1,0));

		System.out.println(result);



	}
	private static void bfs(Data data) {
		Queue<Data> q = new LinkedList<>();

		q.offer(data);

		Data cur;
		int ni,nj;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			
			if(cur.i==N-1 && cur.j == M-1) {//도착하면 cnt 전달해주고 return;
				result = cur.cnt;
				return;
			}
			
			for(int d=0;d<4;d++) {
				ni = cur.i + dy[d];
				nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj]<=cur.wall) continue;
				
				if(map[ni][nj]==0) {//벽이 아닌 경우
					v[ni][nj]=cur.wall;
					q.offer(new Data(ni,nj,cur.cnt+1,cur.wall));
				}
				if(map[ni][nj]==1 && cur.wall<K) {//벽인 경우
					v[ni][nj]=cur.wall+1;
					q.offer(new Data(ni,nj,cur.cnt+1,cur.wall+1));
				}
			}

		}




	}
	static class Data{
		int i;
		int j;
		int cnt;
		int wall;
		public Data(int i, int j, int cnt, int wall) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.wall = wall;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", cnt=" + cnt + ", wall=" + wall + "]";
		}


	}
}
