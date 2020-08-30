//[백준] 말이 되고픈 원숭이
/*
1
4 4
0 1 1 1
0 0 1 1
1 0 1 1
1 1 1 0
# 4
3
4 5
0 1 1 1
1 1 0 1
1 1 1 1
1 1 1 0
1 1 1 0
# 3
1
5 5
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 1 1
0 0 0 1 0
# 6
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Honkey {
	static int K,W,H;
	static boolean [][][] v;
	static int [][] map;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [] dhy = {-1,-2,-2,-1,1,2,2,1};
	static int [] dhx = {-2,-1,1,2,2,1,-1,-2};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine().trim());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		v = new boolean[K+1][H][W];

		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<W;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		

		bfs();
		
		
	}
	private static void bfs() {
		Queue<Data> q = new LinkedList<>();
		
		q.offer(new Data(0,0,0,0));
		v[0][0][0]=true;
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			if(cur.i == H-1 && cur.j == W-1) {//도착지점이면 뛴값 뽑아주고 return
				System.out.println(cur.cnt);
				return;
			}
			
			if(cur.k<K) { //점프 횟수가 남아있으면
				for(int dh = 0;dh<8;dh++) { //최단경로로 가야하니까 일단 먼저 뛰기
					int nhi = cur.i + dhy[dh];
					int nhj = cur.j + dhx[dh];	

					if(!check(nhi,nhj)) continue; //행렬 범위 밖이면 continue
					if(v[cur.k+1][nhi][nhj] || map[nhi][nhj]==1) continue; // 방문체크가 되어있는지, 장애물이 있는지 확인

					v[cur.k+1][nhi][nhj]=true;
					q.offer(new Data(nhi,nhj,cur.cnt+1,cur.k+1));
				}
			}
			
			for(int d =0;d<4;d++) { //4방위 탐색
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];

				if(!check(ni,nj)) continue; //행렬 범위 밖이면 continue
				if(v[cur.k][ni][nj]) continue;
				if(map[ni][nj]==1) continue;

				v[cur.k][ni][nj]=true;
				q.offer(new Data(ni,nj,cur.cnt+1,cur.k));

			}

		}
		
		System.out.println("-1"); //큐 다 빠지면 도착 못한거
		
	}
	static boolean check( int i,int j) { //방문체크용 메소드
		if(i<0 || i>=H || j<0 || j>=W ) return false;
		return true;
	}
	static class Data{
		int i;
		int j;
		int cnt;
		int k;
		public Data(int i, int j, int cnt, int k) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.k = k;
		}
		
	}
}
