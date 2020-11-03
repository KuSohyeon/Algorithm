//[SWEA] [모의SW역량테스트] 탈주범 검거

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CatchCrimimal2 {
	static int N,M,R,C,L;
	static int [][] map;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우
	static int [] dx = {0,0,-1,1};
	static class Point{
		int i;
		int j;
		int time;
		public Point(int i, int j, int time) {
			super();
			this.i = i;
			this.j = j;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", time=" + time + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken()); // 세로
			M = Integer.parseInt(st.nextToken()); // 가로
			R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 위치 i
			C = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑 위치 j
			L = Integer.parseInt(st.nextToken()); // 시간 L
			
			map = new int[N][M];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<M;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			
			System.out.println("#"+tc+" "+bfs());
		}
	}
	private static int bfs() {
		Queue<Point> q = new LinkedList<Point>();	
		boolean [][] v = new boolean[N][M];
		
		q.offer(new Point(R,C,1));
		v[R][C]=true;
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			int dir = map[cur.i][cur.j];
			
			for(int d=0;d<4;d++) {
				// 현 터널에서 갈 수 있는 방향 검사
				if(!check(dir,d)) continue;
				
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj]) continue;
				
				// 다음터널로 이동할 수 있는지 검사
				if(!check2(d,ni,nj)) continue;
				if(cur.time>=L) continue; // 이동 시간 끝나면 큐에 더이상 집어넣지 않음
				q.offer(new Point(ni, nj,cur.time+1));
				v[ni][nj]=true;
			}
		}
		
		// 범인이 있을 수 있는 터널위치를 카운트
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(v[i][j]) cnt++;
			}
		}
		return cnt;
	}
	// 다음 터널로 이동가능한지 여부를 boolean형으로 리턴
	private static boolean check2(int d, int ni, int nj) {
		switch (d) {
		case 0: // 상
			if(map[ni][nj]==1 || map[ni][nj]==2 || map[ni][nj]==5 || map[ni][nj]==6) return true;
			break;
		case 1: // 하
			if(map[ni][nj]==1 || map[ni][nj]==2 || map[ni][nj]==4 || map[ni][nj]==7) return true;
			break;
		case 2: // 좌
			if(map[ni][nj]==1 || map[ni][nj]==3 || map[ni][nj]==4 || map[ni][nj]==5) return true;
			break;
		case 3: // 우
			if(map[ni][nj]==1 || map[ni][nj]==3 || map[ni][nj]==6 || map[ni][nj]==7) return true;
			break;
		}
		return false;
	}
	// 현재 위치한 터널에서 이동하려는 방향이 가능한지 여부를 boolean형으로 리턴
	private static boolean check(int dir,int d) {
		switch (dir) {
		case 1: // 상 하 좌 우
			return true;
		case 2:// 상 하 
			if(d<=1) return true;
			break;
		case 3: // 좌 우
			if(d>1) return true;
			break;
		case 4: // 상 우
			if(d%3==0) return true;
			break;
		case 5: // 하 우
			if(d%2==1) return true;
			break;
		case 6: // 하 좌
			if(d==1 || d==2) return true;
			break;
		case 7: // 상 좌
			if(d%2==0) return true;
			break;
		}
		return false;
	}
}
