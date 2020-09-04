import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JumpKingJelly_multiple {
	static int [] dy = {0,1};// 오른쪽 아래만 이동 가능
	static int [] dx = {1,0};
	static int N;
	static int [][] map;
	static boolean [][] v;
	static boolean flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		v = new boolean[N][N];
		flag = false;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		v[0][0]=true;
		if(!flag) {
			System.out.println("Hing");
		}
	
		
	}
	private static void dfs(int i, int j) {

		if(i==N-1 && j == N-1) {
			flag = true;
			System.out.println("HaruHaru");
			return;
		}
		
		for(int d=0;d<2;d++) {
			int ni = i + (map[i][j]*dy[d]);
			int nj = j + (map[i][j]*dx[d]);
			
			if(ni<0 || ni>=N || nj<0 || nj>=N || v[ni][nj]) continue;
			v[ni][nj]=true;
			dfs(ni,nj);
		}
		
	}
}
