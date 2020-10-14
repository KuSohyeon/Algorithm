//[백준] 로봇청소기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class RobotCleaner {
	static int N,M,result;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,0,1,0};// 상 우 하 좌
	static int [] dx = {0,1,0,-1};
	static Queue<Point> q;
	static class Point{
		int i;
		int j;
		int dir;
		public Point(int i, int j, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", dir=" + dir + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		v = new boolean[N][M];
		q = new LinkedList<Point>();

		st = new StringTokenizer(br.readLine().trim());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		q.offer(new Point(r,c,d));
		
		v[r][c]=true;

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		map[r][c]=5;
		goClean();

		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(v[i][j]) {
					result++;
				}
			}
		}

		System.out.println(result);
	}
	private static void goClean() {

		while(!q.isEmpty()) {
			Point cur = q.poll();

			int ndir = cur.dir;

			out : while(true) {
		
				for(int d=0;d<4;d++) {
					ndir = (ndir+3)%4; // 왼쪽으로 돌리기(반시계 방향)
					
					int ni = cur.i + dy[ndir];
					int nj = cur.j + dx[ndir];

					// 범위체크
					if(ni<0 || ni>=N || nj<0 || nj>=M ) continue;

					if(!v[ni][nj] && map[ni][nj]==0) { // 청소할 영역이 있다면 그 방향으로 한칸 전진 후 청소
						v[ni][nj]=true; // 방문처리
						map[ni][nj]=5; // 청소해준 티 내기(디버깅용)
						q.offer(new Point(ni,nj,ndir)); // 큐에 넣어주고
						break out; // break로 빠져나와서 다음 영역 탐색
					}
					

				}
				// 네방향 다 벽이면서 청소할 수 없는 경우 그 방향 그대로 후진
				cur.i -= dy[ndir];
				cur.j -= dx[ndir];
				
				// 후진도 못할 경우는 끝내기
				if(map[cur.i][cur.j]==1) return;
			}
		}

	}
	
}
