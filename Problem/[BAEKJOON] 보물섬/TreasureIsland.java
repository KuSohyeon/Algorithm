
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreasureIsland {
	static int R,C;
	static int [][] map;
	static boolean [][] v;
//	static int [][]v;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우
	static int [] dx = {0,0,-1,1}; // 상 하 좌 우
	static class Pair{
		int i;
		int j;
		int cnt;
		public Pair(int i, int j, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + ", cnt=" + cnt + "]";
		}
	}
	public static int execute() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		// 구현하세요.
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C]; // 인트형 배열로 입력을 받을 것임
		

		for(int i=0;i<R;i++) {
			char [] ch = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				map[i][j]=ch[j]=='L'?1:0; //땅 : 1, 물 : 0 로 치환해서 받아들이기
			}
		}
		
		// 확인용
//		for(int i=0;i<R;i++) {
//			for(int j=0;j<C;j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
		int result = 0; // 결과
		
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]==1) {
					int tmp = bfs(i,j); //bfs에서 return 받은값 tmp 에 저장
					result = Math.max(result,tmp); // 보물은 한 육지 내의 가장 멀리 떨어진 곳에 나눠서 묻혀져 있음
				}
			}
		}
		
		return result; // 결과 값 리턴
	} // end of execute

	private static int bfs(int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		v= new boolean[R][C]; // 방문 체크
		int cnt=0; // 경로 
		
		q.offer(new Pair(i,j,0));
		v[i][j]=true;
		boolean flag = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			cnt=Math.max(cnt, cur.cnt); // return해줄 거리 여기에 저장
			
			for(int d=0;d<4;d++) { // 상 하 좌 우로만 이동 가능
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<0 || ni>=R || nj<0 || nj>=C || map[ni][nj]==0 || v[ni][nj]) continue; // 조건 체크
				
				v[ni][nj]=true; // 지나갔다고 표시해주고
				q.offer(new Pair(ni,nj,cur.cnt+1)); // 큐에 넣어주기
			}
		}
		
		
		
		return cnt;// 큐가 다 빠지고 나면 결과 값 리턴
	}

	public static void main(String[] args) throws IOException {
		System.out.println(execute());
	}
} // end of class
