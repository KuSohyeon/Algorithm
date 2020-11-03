//[SWEA] [모의SW역량테스트] 벽돌깨기
/**
 * 최적화
 * 메모리 30,944kb
 * 실행 시간 174ms
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWalls_bfs {
	static int N,W,H,min;
	static int [] marble;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static class Point{
		int r;
		int c;
		int cnt;
		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int [][] map = new int[H][W];
			marble = new int[N];
			
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<W;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			go(0, map); // 경우의 수 : 중복 순열 만들기
			
			System.out.println("#"+tc+" "+min);
		}
	}
	private static boolean go(int count, int[][] map) { // 던져진 구슬의 개수, 이전 구슬까지의 2차원 배열
		int result = getRemain(map);
		if(result==0) { // 리턴을 활용해서 벽돌의 개수가 0일때는 더 이상 재귀 안타도록 true 리턴
			min=0;
			return true;
		}
		if(count==N) {
			// 남아 있는 벽돌의 개수를 구하여 최소값 갱신
			if(min>result) {
				min = result;
			}
			return false;
		}
		int [][] newMap = new int[H][W];
		// 모든 열에 떨어뜨리는 시도
		for(int c=0;c<W;c++) {
			int r = 0;
			while(r<H && map[r][c]==0) ++r;
			
			if(r==H) continue;
			
			// 이전 구슬 상태로 배열 복사하여 초기화
			copy(map,newMap);
			// 터뜨리기
			boom(newMap, r,c);
			// 벽돌 내리기
			down(newMap);
			// 다음 구슬 처리
			if(go(count+1,newMap)) return true;
		}
		
		return false;
		
	}
	private static void boom(int[][] map, int r, int c) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(r,c,map[r][c]));
		map[r][c]=0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			if(p.cnt==1) continue;
			
			for(int d=0;d<4;d++) {
				int nr = p.r, nc = p.c;
				for(int k=1;k<p.cnt;k++) {
					nr += dy[d];
					nc += dx[d];
					if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc]!=0) {
						q.offer(new Point(nr, nc, map[nr][nc]));
						map[nr][nc]=0;
					}
				}
			}
		}
		
	}
	private static void down(int[][] map) {
		for (int c = 0; c < W; c++) {
			int r = H-1;
			while(r>0) {
				if(map[r][c]==0) {
					int nr = r-1; // 직전행
					while(nr>0 && map[nr][c]==0) --nr; // 처음 만나는 벽돌 찾기
					map[r][c]=map[nr][c];
					map[nr][c]=0;
				}
				--r;
			}
		}
	}
	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j]=map[i][j];
			}
		}
		
	}
	private static int getRemain(int[][] map) {
		int count=0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]>0) count++;
			}
		}
		return count;
	}
}
