//[백준] 영역구하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class FindingArea {
	static int M,N,K;
	static int [][] map;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static List<Integer> list;
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
			return "Pair [i=" + i + ", j=" + j + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		list = new ArrayList<Integer>();
		
		// 직사각형 그리기
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			makeRectangular(x1,y1,x2,y2);
		}
		
		// 영역 구하기
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0) 
					bfs(i,j);
			}
		}
		
		// 영역 크기 오름차순으로 출력
		Collections.sort(list);
		// 영역의 개수 = 리스트의 크기
		System.out.println(list.size());
		// 영역 출력
		for(Integer li : list) {
			System.out.print(li+" ");
		}
		
		
	}
	private static void bfs(int a, int b) {
		Queue<Point> q = new LinkedList<Point>();
		
		q.offer(new Point(a,b));
		map[a][b]=1;
		
		int cnt=0;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			cnt++;
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=M || nj<0 || nj>=N || map[ni][nj]==1) continue;
				map[ni][nj]=1;
				q.offer(new Point(ni,nj));
			}
		}
		
		list.add(cnt);
		
	}
	private static void makeRectangular(int x1, int y1, int x2, int y2) {
		for(int i=y1;i<y2;i++) {
			for(int j=x1;j<x2;j++) {
				if(map[i][j]==0) map[i][j]=1;
			}
		}
		
	}
}
