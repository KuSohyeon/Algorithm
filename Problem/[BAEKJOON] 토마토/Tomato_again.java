//[BAEKJOON] 토마토
/*
6 4
1 -1 0 0 0 0
0 -1 0 0 0 0
0 0 0 0 -1 0
0 0 0 0 -1 1
출력 : 6
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato_again {
	static int [][] box;
	static boolean [][] v;
	static int N,M,days;
	static Queue<Data> q = new LinkedList<Data>();
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		v = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st =  new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				box[i][j]=Integer.parseInt(st.nextToken());
				if(box[i][j]==1) {
					q.offer(new Data(i,j,0));
					v[i][j]=true;
				}
			}
		}
	
		//가지치기(안익은 토마토가 하나도 없을때는 0 출력)
		boolean flag = false;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(box[i][j]==0) {
					flag = true;
				}
			}
		}
		if(!flag) {
			System.out.println(0);
			return;
		}
		
		
		bfs();
		
		//bfs 돌고 와서 안익은 토마토가 있으면 -1출력
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(box[i][j]==0) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		System.out.println(days);
		
		br.close();
		
	}
	private static void bfs() {//토마토 퍼트리기
		
		Data cur;
		int ni,nj;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			for(int d= 0;d<4;d++) {
				ni = cur.i+dy[d];
				nj = cur.j+dx[d];
				// 조건 체크
				if(ni<0 || ni>=N || nj<0 || nj>=M || box[ni][nj] != 0 || v[ni][nj] ) continue;
				
				v[ni][nj]=true;//방문체크
				box[ni][nj]=1;//토마토 익었으니까 1로 바꿔주기
				q.offer(new Data(ni,nj,cur.cnt+1));
				
				
			}
			days = cur.cnt;//days 업데이트
		}
		
	}
	static class Data{
		int i;
		int j;
		int cnt;
		public Data(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
}
