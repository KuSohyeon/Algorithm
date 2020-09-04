//[백준] 점프왕 쩰리(이동 거리 반복문 돌린버전)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JumpKingJelly_for {
	static int [] dy = {0,1};// 오른쪽 아래만 이동 가능
	static int [] dx = {1,0};
	static int N;
	static boolean [][] v;
	static int [][] map;
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
		
		dfs(0, 0, map[0][0]);
		v[0][0]=true;
		
		if(!flag) { // 도착하지 못하면 힝 출력
			System.out.println("Hing");
		}
		
	}
	private static void dfs(int i, int j, int cnt) {

		if(i==N-1 && j == N-1) { //만약 도착할 수 있으면 하루하루 출력
			flag = true;
			System.out.println("HaruHaru");
			return;
		}
		
		for(int d=0;d<2;d++) {
			int ni = i;
			int nj = j;
			for(int m=0;m<cnt;m++) { // 현재 밟고 있는 칸에 쓰여진 수만큼 이동 가능
				ni += dy[d];
				nj += dx[d];
			}
			if(ni<0 || ni>=N || nj<0 || nj>=N || v[ni][nj]) continue;
			v[ni][nj]=true;
			dfs(ni,nj,map[ni][nj]);
		}
		
	}
}
