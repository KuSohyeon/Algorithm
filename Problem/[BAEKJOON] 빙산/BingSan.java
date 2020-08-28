//[백준] - 빙산(BFS DFS)

/*
5 5
0 0 0 0 0
0 6 9 6 0
0 3 3 9 0
0 6 1 6 0
0 0 0 0 0
출력 4:
5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0
출력 : 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BingSan {
	static int [][] map,map2;
	static boolean [][] v;
	static int N,M,year=0,cnt;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		map2 = new int[N][M];
		v = new boolean[N][M];
		//입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				map2[i][j]=map[i][j];//복사본 저장
			}
		}


		int fin =0;
		
		//빙산이 없을 경우 예외처리
		int empty=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0) {
					empty=-1;
				}
			}
		}
		if(empty==0) {
			System.out.println("0");
			return;
		}

		while(true) {
			cnt=0;
			fin=0;
			v = new boolean[N][M];

			
			int ni,nj;
			for(int i=1;i<N-1;i++) {
				for(int j=1;j<M-1;j++) {
					if(map[i][j]!=0) {
						int count=0;
						fin++;
					for(int d=0;d<4;d++) {
						ni = i + dy[d];
						nj = j + dx[d];

						if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
						if(map[ni][nj]!=0)
							continue;
						count++;//주위 바다 갯수 카운트

					}
					map2[i][j]-=count;//바다갯수만큼 빼주기
					if(map2[i][j]<0) {//음수일 경우 0넣어주기
						map2[i][j]=0;
					}

					}
				}
			}

			for(int i=1;i<N-1;i++) {
				for(int j=1;j<M-1;j++) {

					if(v[i][j]) continue;
					if(map[i][j]==0) continue;
					
//					bfs(new Data(i,j));
					dfs(i,j);
					cnt++;
					
				}
			}

			if(cnt>1) {//빙산 나눠지면 break;
				break;
			}

			if(fin==0) {//만약 마지막에 빙산이 다 0이면 안쪼개진거 -> year에 0출력
				year=0;
				break;
			}
			year++;
			//해마다 바뀐 정보 업데이트
			for(int i=1;i<N-1;i++) {
				for(int j=1;j<M-1;j++) {
					map[i][j]=map2[i][j];
				}
			}

		}

		System.out.println(year);
		
	}
	//dfs로 푸는 법
	static void dfs(int y, int x) {
		v[y][x]=true;
		int ni,nj;
		for(int d=0;d<4;d++) {
			ni = y + dy[d];
			nj = x + dx[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
			if(v[ni][nj]) continue;
			if(map[ni][nj]==0) continue;
			dfs(ni,nj);
			
		}
	}
	//bfs로 푸는 법
	private static void bfs(Data data) {
		Queue<Data> q = new LinkedList<>();
		if(cnt==1) return;

		q.offer(data);
		v[data.i][data.j]=true;

		Data cur;
		int ni,nj;
		while(!q.isEmpty()) {
			cur = q.poll();


			for(int d=0;d<4;d++) {
				ni = cur.i + dy[d];
				nj = cur.j + dx[d];

				if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
				if(v[ni][nj]) continue;
				if(map[ni][nj]==0) continue;
				q.offer(new Data(ni,nj));
				v[ni][nj]=true;
			}
		}



	}

	static class Data{

		int i;
		int j;

		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + "]";
		}


	}

}
