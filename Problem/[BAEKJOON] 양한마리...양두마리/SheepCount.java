//[백준] 양한마리...양두마리...

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SheepCount {
	static int H,W,result=0;
	static char [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
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
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++) {
			result=0; //tc마다 초기화

			st = new StringTokenizer(br.readLine().trim());
			
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			v = new boolean[H][W];
			
			for(int i=0;i<H;i++) {
				char [] ch = br.readLine().toCharArray();
				for(int j=0;j<W;j++) {
					map[i][j] = ch[j];
				}
			}
			
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(map[i][j]!='#' || v[i][j]) continue;
					dfs(i,j);
//					bfs(i,j);
					result++;
				}
			}
			
			
			System.out.println(result);
		}
	}
	private static void bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		
		q.offer(new Point(i,j));
		v[i][j]=true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				if(ni<0 || ni>=H || nj<0 || nj>=W || map[ni][nj]=='.' || v[ni][nj]) continue;
				v[ni][nj]=true;
				q.offer(new Point(ni,nj));
			
			}
		}
		
		
	}
	private static void dfs(int i, int j) {
		v[i][j]=true;
		for(int d=0;d<4;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			if(ni<0 || ni>=H || nj<0 || nj>=W || map[ni][nj]=='.' || v[ni][nj]) continue;
			dfs(ni,nj);
		}
		return;
		
	}
}
