//[백준] 치즈

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {
	static int N,M,result;
	static int [][] map;
	static boolean [][] v;
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
				if(map[i][j]==1) {
					result++; //만약 한시간만에 다 녹을 경우를 대비해서 치즈의 수를 저장
				}
			}
		}
		
		boolean flag = true;
		
		int cnt =0; // 몇 시간동안 녹는지 확인할 변수
		
		while(flag) {
			cnt++; 
			if(bfs()) break;
		}
	
		System.out.println(cnt);
		System.out.println(result);
	}
	private static boolean bfs() { // 탐색이 끝나면 boolean 형으로 리턴함 -> 치즈가 다 녹을 경우 true/ 그 외 false
		
		Queue<Data> q = new LinkedList<Data>();
		v = new boolean[N][M]; // 탐색할 때마다 방문체크 초기화
		
		q.offer(new Data(0,0)); // 치즈가 없는 공기부터 탐색 시작
		v[0][0]=true;
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>= N || nj<0 || nj>=M || v[ni][nj]) continue; // 범위체크, 방문체크
				v[ni][nj]=true;
				if(map[ni][nj]==1) { // 만약 치즈가 있는 공간이라면 녹여주기
					map[ni][nj]=0;
					continue; // 큐에는 넣지 않음
				}
				q.offer(new Data(ni,nj));// 공기일 경우 큐에 넣어주고 탐색하도록 해주기
			}
		}
		
		if(check()==0) { // 치즈의 개수를 확인하는 method
			return true;
		}
		
		return false;
		
	}
	private static int check() {
		int count=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==1) {
					count++;
				}
			}
		}
		
		// 남은 치즈의 개수가 0일 경우에는 1시간 전꺼를 출력해야함
		result = count==0?result:count;
		
		// 남은 치즈 개수 반환
		return count;
	}
}
