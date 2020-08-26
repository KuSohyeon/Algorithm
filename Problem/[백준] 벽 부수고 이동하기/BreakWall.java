//[백준] 2206번 - 벽 부수고 이동하기
/*
6 4
0100
1110
1000
0000
0111
0000
출력 : 15
4 4
0111
1111
1111
1110
출력 : -1
6 4
0000
1110
1000
0000
0111
0000
출력 : 7
5 9
000110000
110000110
011111110
011111110
000000000
출력 : 13
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWall {
	static int [][] map;
	static int N,M,result;
	static int [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		v = new int[N][M];
		
		for(int i=0;i<N;i++) {
			char [] ch = br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				map[i][j]=ch[j]-'0';
				v[i][j]=Integer.MAX_VALUE;
			}
		}

		result = -1;
		
		BFS(new Data(0,0,1,0));

		System.out.println(result);
		br.close();
	}


	private static void BFS(Data data) {

		Queue<Data> q = new LinkedList<>();

		q.offer(data);


		Data cur;
		int ni,nj;
		while(!q.isEmpty()) {

			cur = q.poll();

			if(cur.wall==2) continue; //벽 두개 뿌시면 더이상 큐에 넣지말기
			
			if(cur.i == N-1 && cur.j ==M-1) {//도착지점에 도착하면 result에 최단경로 반환
				result = cur.cnt;
				return;
			}
			

			for(int d=0;d<4;d++) {
				ni = cur.i + dy[d];
				nj = cur.j + dx[d];

				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj]<=cur.wall) continue;
				
				
				if(map[ni][nj]==0) {//벽이 아닌경우 넣어주기
					v[ni][nj]=cur.wall;
					q.offer(new Data(ni,nj,cur.cnt+1,cur.wall));
					
				}
				if(map[ni][nj]==1 && cur.wall==0) {//벽인경우에는 wall + 1해서 넣어주기
					v[ni][nj]=cur.wall+1;
					q.offer(new Data(ni,nj,cur.cnt+1,cur.wall+1));
					
				}
			}


		}

	}
	static class Data{
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

	}
}
