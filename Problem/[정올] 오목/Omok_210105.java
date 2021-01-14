//[정올] 오목

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Omok_210105 {
	static int r,c;
	static int [] dy = {1,0,1,-1}; // 하 우 우하 우상
	static int [] dx = {0,1,1,1};
	static int [] ddy = {-1,0,-1,1};// 상 좌 좌상 우하
	static int [] ddx = {0,-1,-1,1};
	static int [][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[19][19];
		
		for(int i=0;i<19;i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j=0;j<19;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = game();
		System.out.println(answer);
		if(answer!=0)
		System.out.println((r+1)+" "+(c+1));
		
	}
	public static int game() {
		int result = 0;
		out : for(int i=0;i<19;i++) {
			for(int j=0;j<19;j++) {
				if(map[i][j]==0) continue;
				int stone = map[i][j];
				for(int d=0;d<4;d++) {
					int ni = i;
					int nj = j;
					// 반대 방향으로 돌이 있으면 continue
					int nni = i + ddy[d];
					int nnj = j + ddx[d];
					if(nni>=0 && nni<19 && nnj>=0 && nnj<19) {
						if(map[nni][nnj]==stone) continue;
						
					}
					int cnt=1;
					while(true) {
						ni += dy[d];
						nj += dx[d];
						if(ni<0 || ni>=19 || nj<0 || nj>=19 || map[ni][nj]!=stone) break;
						if(++cnt>5) break;
					}
					if(cnt==5) {
						result = stone;
						r = i;
						c = j;
						break out;
					}
				}
			}
		}
		
		return result;
	}
}
