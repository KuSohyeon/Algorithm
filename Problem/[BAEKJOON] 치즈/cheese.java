//[백준] 치즈

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class cheese {
	static int N,M,time,last;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0,-1,-1,1,1};// 상 하 좌 우 좌상 우상 좌하 우하
	static int [] dx = {0,0,-1,1,-1,1,-1,1};
	static class Data{
		int i;
		int j;
		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) last++; //처음 치즈 개수 기록 -> 이거 안하면 1시간만에 치즈가 녹았을 때의 경우가 기록이 안됨
			}
		}
		// 바깥 공기를 먼저 생각하기
		while(true) {
			bfs(0,0); //  항상 0,0부터 탐색
			++time; // bfs 후 시간 증가
			int tmp=0;
			for(int r=0;r<N;r++) {
				for(int c=0;c<M;c++) {
					if(map[r][c]==0) continue;
					if(map[r][c]==2) { // 2로 바꿔놓은거 0으로 바꿔주기(공기에 산화됨)
						map[r][c]=0;
					}else {
						tmp++; // map[i][j]==1 (치즈) 개수 기록
					}
				}
			}
			if(tmp==0) { // 만약 치즈가 없다면 다 녹은거
				break; // 바꿔주기 전에 브레이크
			}
			last = tmp; // 남은 치즈 개수 바꿔주기

		}

		// 출력
		System.out.println(time);
		System.out.println(last);
	}
	private static void bfs(int i, int j) {
		Queue<Data> q = new LinkedList<>();
		v = new boolean[N][M];

		q.offer(new Data(i,j));
		v[i][j]=true;

		while(!q.isEmpty()) {
			Data cur = q.poll();

			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];

				if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj]) continue;
				if(map[ni][nj]==1) { // 만약에 치즈라면 
					map[ni][nj]=2; // 공기에 노출됐다고 표시해주기 (0으로 안해주는 이유 : 다음 큐가 탐색할까봐)
					continue;
				}
				if(map[ni][nj]!=0) continue; // 공기가 아니라면 넘겨주기
				v[ni][nj]=true;
				q.offer(new Data(ni,nj));

			}
		}

	}
}
