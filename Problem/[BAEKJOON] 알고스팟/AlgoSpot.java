//[백준] 알고스팟(BFS)
/*
3 3
011
111
110
출력 : 3
4 2
0001
1000
출력 : 0
6 6
001111
010000
001111
110001
011010
100010
출력 : 2
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AlgoSpot {
	static int [][] map;
	static int N,M;
	static int wallCnt=Integer.MAX_VALUE;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};//상 하 좌 우
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		

		for(int i=0;i<N;i++) {
			char [] ch = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j]=ch[j]-'0';
			}
		}
		
		bfs();
		
		System.out.println(wallCnt);
		
	}
	private static void bfs() {
		PriorityQueue<Data> q = new PriorityQueue<Data>();//우선순위 큐 이용
		v = new boolean[N][M];
		
		
		q.offer(new Data(0,0,0,0));		//첫 좌표 넣어주기
		v[0][0]=true;//방문체크 필수
		
		Data cur;
		int ni,nj;
		while(!q.isEmpty()) {
			cur=q.poll();//현재꺼 처리하기 위해서 뽑기
			
			if(v[N-1][M-1]) {//N,M에 도달했을때 벽깬 횟수 넣어주기
				wallCnt=cur.wall;
				break;
			}
					
			for(int d =0;d<4;d++) {
				ni = cur.i + dy[d];
				nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj]) {
					continue;
				}
				if(map[ni][nj]==0) {
					q.offer(new Data(ni,nj,cur.cnt+1,cur.wall));
					v[ni][nj]=true;
				}else{
					q.offer(new Data(ni,nj,cur.cnt+1,cur.wall+1));
					v[ni][nj]=true;
				}

			}	
		}	
		
	}
	static class Data implements Comparable<Data>{//벽cnt 순서대로 sort
		int i;
		int j;
		int cnt;
		int wall;
		
		public Data(int i, int j, int cnt,int wall) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.wall = wall;
		}

		@Override
		public int compareTo(Data o) {
			if(this.wall==o.wall) return this.cnt-o.cnt;
			return this.wall-o.wall;
		}
			
	}
}
