//[백준] 마법사 상어와 파이어스톰
/*
3 1
1 	2	3	4	5	6	7	8
9	10	11	12	13	14	15	16
17	18	19	20	21	22	23	24
25	26	27	28	29	30	31	32
33	34	35	36	37	38	39	40
41	42	43	44	45	46	47	48
49	50	51	52	53	54	55	56
57	58	59	60	61	62	63	64
2
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MagicSharkWithFireStorm {
	static int N,Q,max,result;
	static int [][] map, tmp;
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
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		int size = (int)Math.pow(2, N);
		map = new int[size][size];
		
		for(int i=0;i<size;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<size;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<Q;n++) {
			int now = Integer.parseInt(st.nextToken());
			// 1. 토네이도 일으키기
			tornado(now);
			// 2. 얼음 녹이기
			meltingIce();
		}
		
		// bfs로 가장 넓은 면적 구하기
		v = new boolean[size][size];
		max = 0;
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(map[i][j]!=0 && !v[i][j]) {
					max = Math.max(max, bfs(i,j));
				}
			}
		}
		
		System.out.println(iceCnt()); // 남아있는 얼음의 합
		System.out.println(max); // 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
	}
	// 얼음을 녹이는 method
	private static void meltingIce() {
		int size = (int)Math.pow(2, N);
		boolean [][] melt = new boolean[size][size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				int cnt = 0;
				for(int d=0;d<4;d++) {
					int ni = i + dy[d];
					int nj = j + dx[d];
					if(ni<0 || ni>=size || nj<0 || nj>=size || map[ni][nj]<=0) continue;
					cnt++;
				}
				if(cnt<3) melt[i][j]=true; // 3개이상 안둘러싸져 있으면 녹일것 
			}
		}
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(melt[i][j]) { 
					if(map[i][j] == 0) continue; // 얼음이 없다면 녹일것도 없음
					map[i][j]--; // 1만큼 녹여주기
				}
			}
		}
	}
	// 시계방향으로 돌리기 전에 영역 구분하는 method
	private static void tornado(int now) {
		int size = (int)Math.pow(2, N);
		int nowSize = (int)Math.pow(2, now);
		for(int i=0;i<size;i+=nowSize) {
			for(int j=0;j<size;j+=nowSize) {
				rotation(i,j,nowSize);
			}
		}
	}
	// 시계방향으로 90도 돌리는 method
	private static void rotation(int r, int c, int nowSize) {
		tmp = new int[nowSize][nowSize]; // L 크기만큼의 배열을 만들어서
		
		// 규칙을 찾아서 돌리기
		for(int i=0;i<nowSize;i++) {
			for(int j=0;j<nowSize;j++) {
				tmp[j][nowSize-1-i]=map[i+r][j+c];
			}
		}
		
		// 시계방향으로 돌린거 원본에 덮어쓰기
		for(int i=r;i<r+nowSize;i++) {
			for(int j=c;j<c+nowSize;j++) {
				map[i][j]=tmp[i-r][j-c];
			}
		}
	}
	// 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수를 구하는 method
	private static int bfs(int r, int c) {
		Queue<Data> q = new LinkedList<>();
		int cnt = 0;
		int size = (int)Math.pow(2, N);
		v[r][c]=true;
		q.offer(new Data(r,c));
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			cnt++;
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				if(ni<0 || ni>=size|| nj<0 || nj>=size || v[ni][nj] || map[ni][nj]==0) continue;
				q.offer(new Data(ni,nj));
				v[ni][nj]=true;
			}
		}
		
		return cnt;
	}
	// 남아있는 얼음의 합 method
	private static int iceCnt() {
		int cnt = 0;
		int size = (int)Math.pow(2, N);
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				cnt += map[i][j];
			}
		}
		return cnt;
	}
}
