//[백준] 아기상어
// 먹을 수 있는 물고기는 pq에 저장 경로는 q에 저장하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark {
	static int N,maxTime;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static Shark shark;
	static class Shark{
		int y;
		int x;
		int eat;
		int growCnt=2;
		public Shark(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "Shark [y=" + y + ", x=" + x + ", eat=" + eat + ", growCnt=" + growCnt + "]";
		}
	}
	static class Fish implements Comparable<Fish>{
		int y;
		int x;
		int time;
		public Fish(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Fish [y=" + y + ", x=" + x + ", time=" + time + "]";
		}
		@Override
		public int compareTo(Fish o) {
			// 두 y 값이 같을 경우
			if(this.y==o.y) {
				// 가장 왼쪽 물고기부터 먹어야 하므로 x 내림차순 정렬
				return Integer.compare(this.x, o.x);
			}
			// 가장 위쪽 물고기부터 먹어야 하므로 y 내림차순 정렬
			return  Integer.compare(this.y, o.y); 
			
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		v = new boolean[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					shark = new Shark(i,j);//상어 정보 저장
				}
			}
		}
	
		startEat();
		
		System.out.println(maxTime);

	}
	private static void startEat() {
		PriorityQueue<Fish> pq = new PriorityQueue<Fish>();
		
		while(true) {
			// 시간에 따른 먹을 수 있는 물고기 찾기
			findFish(pq);
			
			// 먹을 수 있는 물고기가 없는 경우
			if(pq.isEmpty()) break;
			
			eatFish(pq);
		}
		
	}
	/** 물고기 먹기 **/
	private static void eatFish(PriorityQueue<Fish> pq) {
		Fish fish = pq.poll();
		
		int x = fish.x;
		int y = fish.y;
		
		int time = fish.time;
		maxTime += time; // 시간 누적
		// 상어 원래 위치 0으로 바꿔주기
		map[shark.y][shark.x]=0;
		//상어 위치 변경
		shark.x = x;
		shark.y = y;
		shark.eat++;
		map[y][x]=9; // 물고기 위치 9
		//상어 성장
		if(shark.eat == shark.growCnt) {
			shark.eat=0;
			shark.growCnt++;
		}
		// 다 먹고 나면 비워주기
		pq.clear();
		
	}
	/** 물고기 찾기 **/
	private static void findFish(PriorityQueue<Fish> pq) {
		v = new boolean[N][N];
		//상어 위치
		int cur_x = shark.x;
		int cur_y = shark.y;
		int grow = shark.growCnt;
		
		Queue<int []> q = new LinkedList<>();
		
		//상어 위치 넣어줌(y,x,흐른 시간)
		q.offer(new int[] {cur_y,cur_x,0});
		v[cur_y][cur_x]=true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			// 레벨 단위로 BFS
			for(int s = 0; s < size; s++) {
				int [] pos = q.poll();
				
				for(int d=0;d<4;d++) {
					int ny = pos[0] + dy[d];
					int nx = pos[1] + dx[d];
					int time = pos[2];
					
					// 범위를 벗어났으면 패스
					if(outRange(ny,nx)) continue;
					// 이미 방문했으면 패스
					if(v[ny][nx]) continue;
					// 맵의 물고기가 상어보다 큰 경우는 패스
					if(map[ny][nx]>grow) continue;
					
					// 빈 곳 큐에 넣고 패스, 같은 곳은 이동가능하니까 큐에 넣고 패스
					if(map[ny][nx]==0 || map[ny][nx]==grow) {
						q.offer(new int[] {ny,nx,time+1});
						v[ny][nx]=true;
						continue;
					}
					
					// 작은 경우만 pq에 저장하고 패스
					if(map[ny][nx]<grow) {
						pq.offer(new Fish(ny,nx,time+1));
						v[ny][nx]=true;
						continue;
					}
				}
			}
			if(!pq.isEmpty()) break; // 무언가 먹을 수 있다면 먹으러 가기
		}
		
	}
	private static boolean outRange(int ny, int nx) {
		if(ny < 0 || ny >= N || nx < 0 || nx >= N) return true;
		return false;
	}

}
