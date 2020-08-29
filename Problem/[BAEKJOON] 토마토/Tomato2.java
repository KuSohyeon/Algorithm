//[백준] 7569번 - 토마토2
/*
5 3 1
0 -1 0 0 0
-1 -1 0 1 1
0 0 0 1 1
# -1
5 3 2
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 1 0 0
0 0 0 0 0
# 4
4 3 2
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
-1 -1 -1 -1
1 1 1 -1
# 0
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tomato2 {

	static int [][][] box;
	static boolean [][][] v;
	static int N,M,H,result;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [] dh = {-1,1};
	static Queue<Data> q = new LinkedList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt();//가로
		N = sc.nextInt();//세로
		H = sc.nextInt();//높이

		box = new int [H][N][M];
		v = new boolean [H][N][M];

		for(int h = 0;h<H;h++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					box[h][i][j] = sc.nextInt();
					if(box[h][i][j]==1) { //입력받을때 익은 토마토 큐에 넣어주기
						q.offer(new Data(h,i,j,0));
						v[h][i][j]=true;
					}
				}
			}
		}
		//박스에 안익은 토마토가 하나도 없을 경우 bfs 들어가지말고 그냥 0 출력
		boolean flag = false;
		for(int h=0;h<H;h++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(box[h][i][j]==0) {
						flag = true;
					}
				}
			}
		}
		if(!flag) {
			System.out.println("0");
			return;
		}

		bfs();


		// 만약 토마토가 모두 익지 못하는 상황인경우
		for(int h=0;h<H;h++) {

			if(!check(h)) {
				return;
			}
		}

		System.out.println(result);

	}
	private static void bfs() {

		while(!q.isEmpty()) {
			Data cur = q.poll();
			// 먼저 현재 위치에서 위 아래 체크
			for(int e=0;e<2;e++) {
					int nh = cur.h + dh[e];
					
					if(nh<0 || nh>=H || box[nh][cur.i][cur.j]!=0 || v[nh][cur.i][cur.j]) continue;
					
					v[nh][cur.i][cur.j]=true;
					box[nh][cur.i][cur.j]=cur.cnt+1;
					q.offer(new Data(nh,cur.i,cur.j,cur.cnt+1));
			}
			// 상하좌우 체크
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];

					if(ni<0 || ni>=N || nj<0 || nj>=M || box[cur.h][ni][nj]!=0 || v[cur.h][ni][nj]) continue;

					v[cur.h][ni][nj]=true;
					box[cur.h][ni][nj]=cur.cnt+1;
					q.offer(new Data(cur.h,ni,nj,cur.cnt+1));
				
			}

			result = cur.cnt;
		}

	}
	private static boolean check(int h) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(box[h][i][j]==0) {
					System.out.println("-1");
					return false;
				}
			}
		}
		return true;

	}
	static class Data{
		int h;
		int i;
		int j;
		int cnt;
		public Data(int h, int i, int j,int cnt) {
			super();
			this.h = h;
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
}
