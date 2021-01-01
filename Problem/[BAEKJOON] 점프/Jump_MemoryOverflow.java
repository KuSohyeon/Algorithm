//[백준] 1890번 : 점프 (bfs - 메모리초과) 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jump_MemoryOverflow {
	static int N;
	static int [][] map;
	static int [] dy = {1,0};// 하 우
	static int [] dx = {0,1};
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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		System.out.println(bfs());
	}
	private static long bfs() {
		long cnt = 0;
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(0,0));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.i == N-1 && cur.j == N-1) cnt++;
			if(map[cur.i][cur.j]==0) continue;
			
			int num = map[cur.i][cur.j];
			
			for(int d=0;d<2;d++) {
				int ni = cur.i + num * dy[d];
				int nj = cur.j + num * dx[d];
				
				if(ni>=N || nj>=N ) continue;
				q.offer(new Point(ni,nj));
			}
		}
		return cnt;
	}
}
