import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등산로조성 {
	static int N, K,result;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			v = new boolean[N][N];
			
			int max = 0;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			
			result = 0; // tc마다 초기화
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]==max) {
							v[i][j]=true;
							dfs(i,j,1,max,false);
							v[i][j]=false;
					}
				}
			}
			
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void dfs(int i, int j, int cnt, int height,boolean b) {
	
		result = Math.max(result, cnt);
		for(int d=0;d<4;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			if(ni<0 || ni>=N || nj<0 || nj>=N || v[ni][nj]) continue;
			
			if(map[ni][nj]<height) {
				v[ni][nj]=true;
				dfs(ni,nj,cnt+1,map[ni][nj],b);
				v[ni][nj]=false;
			}else {
				if(!b && map[ni][nj]-K<height) {
					v[ni][nj]=true;
					dfs(ni,nj,cnt+1,height-1,true);
					v[ni][nj]=false;
				}
			}
		}
	}
}