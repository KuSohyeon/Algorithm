//[백준] 테트로미노
// 1x1 정사각형이 4개인 칸은 테트로미노
// ㅗ 이런 모양도 있기 때문에 단순히 boolean 형 visit 배열을 사용하면 만들지 못함
// int형 방문체크를 이용하여 1번까지는 중복 허용해주기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tetromino {
	static int N,M,result;
	static int [][] map;
	static int [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				v[i][j]=1;
				dfs(i,j,1,map[i][j]);
				v[i][j]=0; // 백트래킹
			}
		}

		
		System.out.println(result);
	}
	private static void dfs(int i, int j, int cnt,int tmp) {
		if(cnt==4) { // 칸의 개수가 4개가 되면 result 업데이트 후 return
			result = Math.max(result, tmp);
			return;
		}
		for(int d=0;d<4;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=M || v[ni][nj]>1) continue; //  ㅗ ㅓ ㅏ ㅜ 이런 모양 때문에 한번만 중복 허용해주기
			if(v[ni][nj]==0) { // 만약 한번도 방문한 지점이 아니라면
				tmp += map[ni][nj]; // 그 칸의 숫자를 더해주고
				cnt++; // 칸 수 늘려주기
			}
			v[ni][nj]++; // 올때마다 방문체크
			dfs(ni,nj,cnt,tmp); // dfs 보내주고
			tmp -= map[ni][nj]; // 백트래킹
			v[ni][nj]--; 
			if(v[ni][nj]==0) { // 만약 백트래킹 후 방문한적이 없으면 칸 수 빼주기(중복의 경우때문)
				cnt--;
			}
		}
		
	}
	
}
