//[백준] 음식물 피하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AvoidFoods {
	static int N, M, K, result;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static class Point {
		int i;
		int j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		v = new boolean[N+1][M+1];
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c]=1;
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(map[i][j] == 1 && !v[i][j]) {
					result = Math.max(result, bfs(i,j));
				}
			}
		}
		
		System.out.println(result);
	}
	private static int bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		v[i][j] = true;
		q.offer(new Point(i, j));
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			cnt ++;
			
			for(int d =0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<1 || ni>N || nj<1 ||nj>M || v[ni][nj] || map[ni][nj]==0) continue;
				v[ni][nj]=true;
				q.offer(new Point(ni,nj));
			}
		}
		return cnt;
	}
	
}
