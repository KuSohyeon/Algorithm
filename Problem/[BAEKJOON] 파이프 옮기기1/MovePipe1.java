import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MovePipe1 {
	static int N;
	static long cnt;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {0,1,1};// 가로 세로 대각선
	static int [] dx = {1,0,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		
		map = new int[N+1][N+1];
		v = new boolean[N+1][N+1];

		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		v[1][1]=true;
		v[1][2]=true;
		
		dfs(1,2,0);
		
		System.out.println(cnt);

	}

	private static void dfs(int i, int j, int dir) {
		

		if(i==N && j==N) { //만약 도착하면 경우의 수 늘려주고 return
			cnt++;
			return;
		}
		
		for(int d=0;d<3;d++) {
			if(dir==0) { //가로의 경우에는 세로가 불가능
				if(d==1) continue;
			}else if(dir==1) { //세로의 경우에는 가로가 불가능
				if(d==0) continue;
			}
			int ni = i + dy[d];
			int nj = j + dx[d];
			
			if(ni<1 || ni>N || nj<1 || nj>N) continue; // 조건 처리
			if(map[ni][nj]==1) continue; //조건 처리
			
			if(d==2) { // 만약 대각선의 경우에는 ni,nj의 상, 좌가 비어있어야 함-> 벽도 안되고 파이프가 있어도 안됨
				if(map[ni-1][nj]==1 || map[ni][nj-1]==1 || v[ni-1][nj] || v[ni][nj-1]) continue;
			}
			v[ni][nj]=true; //방문체크 해주고
			dfs(ni,nj,d); // dfs 보내주고
			v[ni][nj]=false;// 백트래킹
		}
		
		
		
		
	}
}
