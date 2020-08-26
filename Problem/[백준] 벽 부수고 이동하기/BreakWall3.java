//[백준] - 벽 부수고 이동하기 3
/*
1 4 1
0010
출력 : 5
1 4 1
0100
출력 : 4
6 4 1
0100
1110
1000
0000
0111
0000
출력 : 15
6 4 2
0100
1110
1000
0000
0111
0000
출력 : 9
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class BreakWall3 {
	static int [][] map,v;
	static int N,M,K;
	static int result;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		v = new int [N][M];

		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(str.substring(j, j+1));
				v[i][j]=Integer.MAX_VALUE;
			}
		}
		result = -1;
		bfs();

		System.out.println(result);
	}
	private static void bfs() {
//		Queue<Data> q = new LinkedList<>();
		PriorityQueue<Data> q = new PriorityQueue<BreakWall3.Data>();

		q.offer(new Data(0,0,1,0,true));
		v[0][0]=0;

		Data cur;
		int ni,nj;
		while(!q.isEmpty()) {
			cur = q.poll();
			
			if(cur.i == N-1 && cur.j == M-1) {
				result = cur.cnt;
				return;
			}



			for(int d = 0;d<4;d++) {
				ni = cur.i+dy[d];
				nj = cur.j+dx[d];

				if(ni<0 || ni>=N  || nj<0 || nj>=M || v[ni][nj]<=cur.wall) continue;


				if(map[ni][nj]==1 && cur.wall<K) {//벽일 경우

					if(cur.state) {// 낮이라면 정상적으로 그냥 벽 뿌시고 하기
						
						v[ni][nj] = cur.wall+1;
						q.offer(new Data(ni,nj,cur.cnt+1, cur.wall+1,!cur.state));
					}

					else { //만약 밤이라면 하루 더 기다리기 cnt도 하루 더 늘려줘야함
						v[ni][nj] = cur.wall+1;
						q.offer(new Data(ni,nj,cur.cnt+2, cur.wall+1,cur.state));
						
					}

				}
				if(map[ni][nj]==0) {//벽이 아닌 경우
					v[ni][nj]=cur.wall;
					q.offer(new Data(ni,nj,cur.cnt+1, cur.wall,!cur.state));

				}



			}

		}

	}
	static class Data implements Comparable<Data>{
		int i;
		int j;
		int cnt;
		int wall;
		boolean state;
		public Data(int i, int j, int cnt, int wall,boolean state) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.wall = wall;
			this.state = state;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", cnt=" + cnt + ", wall=" + wall +", state=" + state + "]";
		}
		@Override
		public int compareTo(Data o) {
			return this.cnt-o.cnt;
		}

	}
}
