import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Banner {
	static int M,N,letter;
	static int [] dy = {-1,1,0,0,-1,-1,1,1}; // 상 하 좌 우 좌상 우상 좌하 우하
	static int [] dx = {0,0,-1,1,-1,1,-1,1};
	static int [][] map;
	static boolean [][] v;
	static class Point{
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
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		v = new boolean[M][N];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==1 && !v[i][j]) { // 현수막에 안세렸던 글씨만 세리기
//					bfs(i,j);
					dfs(i,j);
					letter++;
				}
			}
		}
		System.out.println(letter);
	}
	private static void dfs(int i, int j) {
		v[i][j]=true;
		
		for(int d=0;d<8;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			
			if(ni<0 || ni>=M || nj<0 || nj>=N || v[ni][nj]|| map[ni][nj]==0) continue;
			dfs(ni,nj);
		}
		
		return;
		
	}
	private static void bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		
		q.offer(new Point(i,j));
		v[i][j]=true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<8;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=M || nj<0 || nj>=N || v[ni][nj]|| map[ni][nj]==0) continue;
				v[ni][nj]=true;
				q.offer(new Point(ni,nj));
			}
		}
		
	}
}
