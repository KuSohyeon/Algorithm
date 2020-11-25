//[백준] 적록색약

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ColourBlindness {
	static int N;
	static char [][] map;
	static boolean [][] v1,v2;
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
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j]=s.charAt(j);
			}
		}
		
		int normal = 0;
		int abnormal = 0;
		
		// 일반인
		v1 = new boolean [N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!v1[i][j]) {
					normalBfs(i,j);
					normal++;
				}
			}
		}
		
		// 적록색약
		v2 = new boolean [N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!v2[i][j]) {
					abnormalBfs(i,j);
					abnormal++;
				}
			}
		}
		
		System.out.println(normal+" "+abnormal);
	}
	// 적록색약 method
	private static void abnormalBfs(int y, int x) {
		
		char key = map[y][x];
		
		Queue<Point> q = new LinkedList<Point>();
		
		q.offer(new Point(y,x));
		v2[y][x]=true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || v2[ni][nj]) continue;
				if(key!='B') { // 적록색약일 경우에는 파란색만 구분 가능
					if(map[ni][nj]=='B') continue;
				}else { // 파란색인 경우는 나머지 구별 가능
					if(map[ni][nj]!=key) continue;
				}
				v2[ni][nj]=true;
				q.offer(new Point(ni,nj));
			}
		}
		
	}
	// 일반인 method
	private static void normalBfs(int y, int x) {
		
		char key = map[y][x];
		
		Queue<Point> q = new LinkedList<Point>();
		
		q.offer(new Point(y,x));
		v1[y][x]=true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || v1[ni][nj] || map[ni][nj]!=key) continue;
				v1[ni][nj]=true;
				q.offer(new Point(ni,nj));
			}
		}
		
		
	}
}
