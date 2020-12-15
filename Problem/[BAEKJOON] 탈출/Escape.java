//[백준] 탈출
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape {
	static int R,C, startI, startJ;
	static char [][] map;
	static boolean [][] v;
	static Queue<Data> hed, water;
	static int [] dy = {-1,1,0,0};  // 상 하 좌 우
	static int [] dx = {0,0,-1,1};
	static class Data{
		int i;
		int j;
		int cnt;
		
		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		public Data(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", cnt=" + cnt + "]";
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		v = new boolean[R][C];
		hed = new LinkedList<Escape.Data>();
		water = new LinkedList<Escape.Data>();
		
		for(int i=0;i<R;i++) {
			String s = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]=s.charAt(j);
				if(map[i][j]=='S') { // 고슴도치 위치 큐에 넣어주기
					hed.offer(new Data(i,j,0));
					map[i][j]='.';
					v[i][j]=true;
				}else if(map[i][j]=='*') { // 물 위치 큐에 넣어주기
					water.offer(new Data(i,j));
				}
			}
		}
		
		bfs();
	}
	private static void bfs() {
		
		String result = "KAKTUS";
		while(!hed.isEmpty()) { // 조건 : 고슴도치 큐가 빌 때 까지!!!!
			int size = hed.size();
			
			for(int i=0;i<size;i++) {
				Data cur = hed.poll();
				if(map[cur.i][cur.j]=='*') { // 만약 고슴도치가 있던 곳이 물로 찻으면 넘겨주기(혹시몰라서)
					continue;
				}
				
				for(int d=0;d<4;d++) {
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=R || nj<0 || nj>=C || map[ni][nj]=='*' || map[ni][nj]=='X' || v[ni][nj]) continue;
					if(map[ni][nj]=='D') { // 도착하면 끝
						System.out.println(cur.cnt+1);
						System.exit(0);
					}
					if(!check(ni,nj)) continue; // 물이 인접한 곳이면 어차피 물에 빠지니까 넘겨주기
					map[ni][nj]='S'; // 고슴도치 표시(확인용)
					v[ni][nj]=true; // 방문체크
					hed.offer(new Data(ni,nj,cur.cnt+1));
				}
			}
			
			size = water.size(); // 물의 사이즈만큼 반복
			for(int i=0;i<size;i++) {
				Data cur = water.poll();
				
				for(int d=0;d<4;d++) {
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni<0 || ni>=R || nj<0 || nj>=C || map[ni][nj]!='.') continue;
					map[ni][nj]='*';
					water.offer(new Data(ni,nj));
				}
			}
			
		}
		// 만약 큐가 빌때까지 탈출하지 못했다면 KAKTUS 출력
		System.out.println(result);
	}
	// 이번에 물이 차는 곳인지 확인하는 method
	private static boolean check(int i, int j) {
		for(int d=0;d<4;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			
			if(ni<0 || ni>=R || nj<0 || nj>=C) continue;
			if(map[ni][nj]=='*') return false;
		}
		return true;
	}
}
