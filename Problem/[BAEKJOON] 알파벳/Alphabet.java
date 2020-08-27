//[백준] 알파벳
/*
2 4
CAAB
ADCB
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Alphabet {
	static char [][] map;
	static boolean [] v;
	static int R,C,max;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][];
		v = new boolean[26];

		for(int i=0;i<R;i++) {
			map[i]=br.readLine().toCharArray();
		}

		v[map[0][0]-65]=true;
		dfs(0,0,1);

		System.out.println(max);
	}
	private static void dfs(int i, int j, int cnt) {

		max = Math.max(max, cnt);
		
		if(max==26) {
			return;
		}

		for(int d=0;d<4;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];

			if(ni<0|| ni>= R || nj<0 || nj>=C || v[map[ni][nj]-65]) {
				continue;
			}
			v[map[ni][nj]-65]=true;
			dfs(ni,nj,cnt+1);
			v[map[ni][nj]-65]=false;
		}

	}

}

