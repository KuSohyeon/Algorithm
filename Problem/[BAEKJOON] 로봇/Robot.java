//[백준] 로봇

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Robot {
	static int N,M,result;
	static int [][] map;
	static int [][][] v;
	static Queue<Point> q;
	static int [] dy = {0,0,0,1,-1}; // 0 동 서 남 북
	static int [] dx = {0,1,-1,0,0};
	static class Point{
		int i;
		int j;
		int dir;
		int cnt;
		int go;
		public Point(int i, int j, int dir, int cnt, int go) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.cnt = cnt;
			this.go = go;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", dir=" + dir + ", cnt=" + cnt + ", go=" + go + "]";
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M+1][N+1]; // 1~M, 1~N
		v = new int[M+1][N+1][5]; 
		
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=1;j<=N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				for(int d=1;d<=4;d++) {
					v[i][j][d]=Integer.MAX_VALUE; //방문배열 max값으로 초기화
				}
			}
		}
		
		q = new LinkedList<Robot.Point>();
		
		// 출발 좌표
		st = new StringTokenizer(br.readLine().trim());
		
		int startI = Integer.parseInt(st.nextToken());
		int startJ = Integer.parseInt(st.nextToken());
		int startD = Integer.parseInt(st.nextToken());
		
		q.offer(new Point(startI, startJ, startD, 0, 0));
		
		// 도착 좌표
		st = new StringTokenizer(br.readLine().trim());
		
		int EndI = Integer.parseInt(st.nextToken());
		int EndJ = Integer.parseInt(st.nextToken());
		int EndD = Integer.parseInt(st.nextToken());
		
		result = Integer.MAX_VALUE; // result 초기화
		
		bfs(EndI, EndJ, EndD);
		
		System.out.println(result);
		
	}
	private static void bfs(int endI, int endJ, int endD) {
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.i == endI && cur.j == endJ && cur.dir == endD) {
				result = Math.min(result, cur.cnt); // 최소값으로 넣어줘야함 cnt가 큰게 먼저 나올 수 있음
			}
			
			//  돌려주기
			for(int d=1;d<4;d++) {
				// 왼쪽 오른쪽으로 90도씩만 회전 가능
				// 동:1 서:2 남:3 북:4
				int ndir = cur.dir+d>4?(cur.dir+d)%5+1 :(cur.dir+d);
				if(v[cur.i][cur.j][ndir]<cur.cnt) continue; // 더 큰값은 큐에 넣어주지 X
				v[cur.i][cur.j][ndir]=cur.cnt;
				if(cur.dir%2==0 && d==3) { //짝수인 경우 +3한게 180도 회전 -> cnt+2
					q.offer(new Point(cur.i,cur.j,ndir,cur.cnt+2,0));
				}else if(cur.dir%2==1 && d==1) {// 홀수인 경우 +1 한게 180도 회전 -> cnt+2
					q.offer(new Point(cur.i,cur.j,ndir,cur.cnt+2,0));
				} // 그 외의 경우는 그냥 90도 회전
				else q.offer(new Point(cur.i,cur.j,ndir,cur.cnt+1,0));
			}
			
			// 직진
			int ni = cur.i + dy[cur.dir];
			int nj = cur.j + dx[cur.dir];
			if(ni<1 || ni>M || nj<1 || nj>N || map[ni][nj]==1 || v[ni][nj][cur.dir]<cur.cnt) continue;
			v[ni][nj][cur.dir]=cur.cnt; // 방문체크
			q.offer(new Point(ni, nj, cur.dir, cur.go+1==1?cur.cnt+1:cur.cnt, cur.go+1==3?0:cur.go+1)); // 3칸까지 한번에 이동가능
		}
		
	}
	
}
