import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_치즈2 {
	static int N,M;
	static int [][] map;
	static int [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
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
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		boolean flag = true;
		
		int cnt=0;
		
		while(flag) {
			cnt++; // 시간 계산
			if(bfs()) break;
		}
		
		// 출력
		System.out.println(cnt);
	}
	private static boolean bfs() {
		Queue<Data> q = new LinkedList<Data>();
		v = new int[N][M];
		
		q.offer(new Data(0,0));
		
		v[0][0]++;
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M) continue; // 범위체크
				if(map[ni][nj]==0 && v[ni][nj]>=1) continue; // 공기일 경우에는 한번 이상 방문할 필요 X
				v[ni][nj]++; // 방문체크
				if(map[ni][nj]==1) { // 치즈 일 경우
					if(v[ni][nj]>=2) { // 두면이상 접촉한 곳은 녹음
						map[ni][nj]=0;
					}
					continue; // 치즈였을 경우에는 큐에 넣어주지 않는다.
				}
 				q.offer(new Data(ni,nj));
			}
		}
		
		if(check()==0) { // 치즈의 개수가 0일 경우
			return true; // while문 빠져나가기
		}
		
		// 치즈가 남아있다면 while문 한번 더 돌기
		return false;
		
		
		
	}
	// 치즈 개수 세는 method
	private static int check() {
		int count=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==1) {
					count++;
				}
			}
		}
		
		
		// 남은 치즈 개수 반환
		return count;
	}
	
}
