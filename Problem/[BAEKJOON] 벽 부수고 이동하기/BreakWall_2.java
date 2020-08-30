//[백준] 벽 부수고 이동하기2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWall_2 {
	static int [][] map;
	static boolean [][][] v; //3차원 방문체크 사용
	static int N,M,K;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[K+1][N][M];
		
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
			//종료 조건
			if(cur.i == N-1 && cur.j == M-1) {
				System.out.println(cur.cnt);
				return;
			}
			
			for(int d =0;d<4;d++) {
				int ni = cur.i+dy[d];
				int nj = cur.j+dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				if(v[cur.wall][ni][nj]) continue;
				if(map[ni][nj]==1) {//벽을 만난 경우
					if(cur.wall<K) { //부수기 기회 남아있으면 부수기
						v[cur.wall+1][ni][nj]=true;
						q.offer(new Data(ni,nj,cur.cnt+1,cur.wall+1));
						continue;
					}
				}
				if(map[ni][nj]==1) continue;//못 부수는 경우 넘어가기
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
