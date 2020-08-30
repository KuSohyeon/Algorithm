//[백준] 벽 부수고 이동하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWall_1 {
	static int [][] map;
	static boolean [][][] v;
	static int N,M;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[2][N][M];
		
		for(int i=0;i<N;i++) {
			char [] ch = br.readLine().toCharArray();
			for(int j=0;j<M;j++){
				map[i][j] = ch[j] -'0';
			}
		}
		
		bfs();
	}
	private static void bfs() {
		Queue<Data> q = new LinkedList<>();
		
		q.offer(new Data(0,0,1,0));
		v[0][0][0]=true;
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			if(cur.i == N-1 && cur.j == M-1) {
				System.out.println(cur.cnt);
				return;
			}
			
			for(int d =0;d<4;d++) {
				int ni = cur.i+dy[d];
				int nj = cur.j+dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				if(v[cur.wall][ni][nj]) continue;
				if(map[ni][nj]==1) {
					if(cur.wall==0) {
						v[cur.wall+1][ni][nj]=true;
						q.offer(new Data(ni,nj,cur.cnt+1,cur.wall+1));
						continue;
					}
				}
				if(map[ni][nj]==1) continue;
				v[cur.wall][ni][nj] = true;
				q.offer(new Data(ni,nj,cur.cnt+1,cur.wall));
			}
		}
		System.out.println("-1");
		
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
