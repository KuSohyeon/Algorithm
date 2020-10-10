//[SWEA] 벽돌깨기
/*
 * 벽돌깨기 규칙
 * 1. 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨뜨릴 수 있다.
 * 2. 구슬이 명중한 벽돌은 상하좌우로 (벽돌에 적힌 숫자 -1)칸 만큼 같이 제거된다.
 * 3. 제거되는 범위 내에 있는 벽돌은 동시에 제거된다.
 * 4. 남은 벽돌의 개수를 구하라.
 * 
 * 구현 방법
 * 1. 구슬 떨어뜨릴 위치 찾기(중복 순열) 완탐..
 * 2. 깨기
 * 3. 내리기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallByMarble {
	static int T,N,W,H,result;
	static int [][] map,copy;
	static boolean[][] v;
	static int [] loc;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우
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
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H+1][W];
			
			
			Arrays.fill(map[0], 0);
			for(int i=1;i<=H;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<W;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			loc = new int[N];
			result = Integer.MAX_VALUE; // tc마다 초기화
			
			findLocation(0);
			
			// 출력
			System.out.println("#"+tc+" "+result);
		}
	}
	/** 내리기 **/
	private static void down() {
		boolean flag = false;
		while(true) {
			flag = false;
			for(int j=0;j<W;j++) {
				for(int i=1;i<H;i++) {
					if(copy[i][j]!=0 && copy[i+1][j]==0) {
						copy[i+1][j] = copy[i][j]; //한칸 내려주고
						copy[i][j]=0; // 원래칸은 0으로 만들기
						flag = true;
					}
				}
			}
			if(!flag) break;
		}
		
	}
	/** 벽돌 깨기 **/
	private static void bfs() {
		Queue<Point> q = new LinkedList<Point>();
		copy = new int[H+1][W]; // 원본 보존
		
		for(int i=1;i<=H;i++) {
			for(int j=0;j<W;j++) {
				copy[i][j]=map[i][j];
			}
		}
		
		for(int n=0;n<N;n++) {
			v = new boolean[H+1][W];
			Point point = DetailLoc(loc[n]);
			q.offer(point);
			while(!q.isEmpty()) {
				Point cur = q.poll();
				int num = copy[cur.i][cur.j]-1;
				copy[cur.i][cur.j]=0; // 먼저 좌표 터뜨려 주기
				v[cur.i][cur.j]=true;
				if(num==0) continue; // 벽돌에 적힌 숫자가 1이면 자기꺼만 깨주면 됨
				for(int d=0;d<4;d++) {
					int ni = cur.i;
					int nj = cur.j;
					for(int m=0;m<num;m++) {
						ni += dy[d];
						nj += dx[d];
						if(ni<1 || ni>H || nj<0 || nj>=W) break; // 배열의 인덱스 넘어가면 브레이크
						if(v[ni][nj] || copy[ni][nj]==0) continue; // 만약 이미 방문했거나 0이라면 넘겨주기
						v[ni][nj]=true; // 방문체크
						q.offer(new Point(ni,nj)); // 큐에 넣어주기
					}
					
				}
			}
			down(); // 내려주고
		}
		count(); // 남은 벽돌 개수 카운트
		
	}
	/** 벽돌개수 카운트 **/
	private static void count() {
		int tmp = 0;
		for(int i=1;i<=H;i++) {
			for(int j=0;j<W;j++) {
				if(copy[i][j]!=0) {
					tmp++;
				}
			}
		}
		// result 업데이트
		result = Math.min(result, tmp);
	}
	/** 처음 깰 벽돌 좌표 구하기 **/
	private static Point DetailLoc(int n) {
		Point loc = null;
		for(int i=1;i<=H;i++) {
			if(copy[i][n]!=0) {
				loc = new Point(i,n); //좌표 넣어주기
				break;
			}
		}
		if(loc==null) {
			loc = new Point(H,n);
		}
		return loc;
	}
	/** 중복 순열(완탐) - 처음 구슬의 좌표 정하기 **/
	private static void findLocation(int cnt) {
		if(cnt==N) {
			bfs();
			return;
		}
		for(int i=0;i<W;i++) {
			loc[cnt]=i;
			findLocation(cnt+1);
		}
	}
	
}
