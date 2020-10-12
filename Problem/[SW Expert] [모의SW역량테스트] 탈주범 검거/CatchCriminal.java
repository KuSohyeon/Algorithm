//[SWEA] [모의SW역량테스트] 탈주범 검거

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CatchCriminal {
	static int N,M,R,C,L,result,start,end;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우 좌상 우상 좌하 우하
	static int [] dx = {0,0,-1,1};
	static class Point implements Comparable<Point>{
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
		@Override
		public int compareTo(Point o) {
			return this.time-o.time;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int tc=1;tc<=T;tc++) {

			result = 0;
			st = new StringTokenizer(br.readLine().trim());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];

			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<M;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bfs();
			

			System.out.println("#"+tc+" "+result);
		}
	}
	private static void bfs() {
		PriorityQueue<Point> q = new PriorityQueue<Point>();
		v = new boolean[N][M];

		q.offer(new Point(R,C,1));
		v[R][C]=true;

		while(!q.isEmpty()) {
			Point cur = q.poll();

			if(cur.time == L) break; // 시간 다되면 break로 loop 빠져나오기

			// 각 위치에서 갈 수 있는 케이스가 다름 -> switch-case 문 이용
			switch(map[cur.i][cur.j]) {
			case 1:// 상 하 좌 우
				for(int d=0;d<4;d++) {
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]==0) continue;
					if(!check(d,ni,nj)) continue; // 이동할 수 있는지 확인
					v[ni][nj]=true;
					q.offer(new Point(ni, nj, cur.time+1));
				}
				break;
			case 2:// 상 하 0 1
				for(int d=0;d<2;d++) {
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]==0) continue;
					if(!check(d,ni,nj)) continue;
					v[ni][nj]=true;
					q.offer(new Point(ni, nj, cur.time+1));
				}
				break;
			case 3: // 좌 우 2 3
				for(int d=2;d<4;d++) {
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]==0) continue;
					if(!check(d,ni,nj)) continue;
					v[ni][nj]=true;
					q.offer(new Point(ni, nj, cur.time+1));
				}
				break;
			case 4: // 상 우 0 3
				for(int d=0;d<4;d++) {
					if(d%3!=0) continue; 
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]==0) continue;
					if(!check(d,ni,nj)) continue;
					v[ni][nj]=true;
					q.offer(new Point(ni, nj, cur.time+1));
				}
				break;
			case 5: // 하 우 1 3
				for(int d=0;d<4;d++) {
					if(d%2!=1) continue; 
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]==0) continue;
					if(!check(d,ni,nj)) continue;
					v[ni][nj]=true;
					q.offer(new Point(ni, nj, cur.time+1));
				}
				break;
			case 6: // 하 좌 1 2
				for(int d=1;d<3;d++) {
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]==0) continue;
					if(!check(d,ni,nj)) continue;
					v[ni][nj]=true;
					q.offer(new Point(ni, nj, cur.time+1));
				}
				break;
			case 7: // 상 좌 0 2
				for(int d=0;d<4;d++) {
					if(d%2!=0) continue; 
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj] || map[ni][nj]==0) continue;
					if(!check(d,ni,nj)) continue;
					v[ni][nj]=true;
					q.offer(new Point(ni, nj, cur.time+1));
				}
				break;

			}

		}
		
		// 갈 수 있는 경우의 수 세리기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(v[i][j]) result ++;
			}
		}


	}
	private static boolean check(int d,int ni, int nj) { // 파이프가 어떻게 연결되어 있느냐에 따라서 갈 수 잇는 좌표가 다름
		switch(d) {
		case 0: // 상
			if(map[ni][nj]==3 || map[ni][nj]==7 || map[ni][nj]== 4) return false;
			break;	
		case 1: // 하
			if(map[ni][nj]==3 || map[ni][nj]==5 || map[ni][nj]== 6) return false;
			break;	
		case 2: // 좌
			if(map[ni][nj]==2 || map[ni][nj]==6 || map[ni][nj]==7) return false;
			break;
		case 3: // 우
			if(map[ni][nj]==2 || map[ni][nj]==4 || map[ni][nj]==5) return false;
			break;
		}
		return true;
	}
	
}
