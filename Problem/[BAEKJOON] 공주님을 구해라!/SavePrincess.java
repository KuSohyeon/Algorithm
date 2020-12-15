//[백준] 공주님을 구해라!

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SavePrincess {
	static int N,M,T;
	static int [][] map;
	static boolean [][][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static class Data implements Comparable<Data>{
		int i;
		int j;
		int cnt;
		boolean sword;
		public Data(int i, int j, int cnt, boolean sword) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.sword = sword;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", cnt=" + cnt + ", sword=" + sword + "]";
		}
		@Override
		public int compareTo(Data o) {
			return this.cnt-o.cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
	}
	private static void bfs() {
		int time = 0;
		boolean flag = false;
		PriorityQueue<Data> pq = new PriorityQueue<Data>();
		v = new boolean[N][M][2];
		v[0][0][0]=true;
		pq.offer(new Data(0,0,0,false));
		
		while(!pq.isEmpty()) {
			Data cur = pq.poll();
			
			if(cur.i == N-1 && cur.j == M-1) { // 공주한테 도달 한 경우 break
				time = cur.cnt;
				flag = true;
				break;
			}
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni< 0 || ni>=N || nj<0 || nj>=M) continue;
				if(cur.sword) { // 검이 있을 경우
					if(v[ni][nj][1]) continue;
					v[ni][nj][1]=true;
					pq.offer(new Data(ni,nj,cur.cnt+1, cur.sword));
				}else { // 검이 없을 경우
					if(v[ni][nj][0] || map[ni][nj]==1) continue; // 검이 없을 경우에는 벽이면 못감
					v[ni][nj][0]=true;
					if(map[ni][nj]==2) { // 검을 획득한 경우
						v[ni][nj][1]=true;
						pq.offer(new Data(ni,nj,cur.cnt+1,true)); // 
						continue; // 앞으로 검 없을 경우 생각할 필요없으니까 continue
					}
					pq.offer(new Data(ni,nj,cur.cnt+1, cur.sword));
					
				}
			}
		}
		
		if(flag) System.out.println(time<=T?time:"Fail"); // 도달을 하더라도 시간안에 못구하면 Fail 출력
		else System.out.println("Fail"); // 공주한테 가지도 못하면 Fail
	}
}
