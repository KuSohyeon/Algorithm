//[백준] 드래곤커브

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685 {
	static int [][] map;
	static int result;
	static int [] dy = {0,-1,0,1};// 우 상 좌 하
	static int [] dx = {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			makeDragonFly(x,y,d,g); // 드래곤 플라이 만들고 map에 표시해주기
		}
		
		// 사각형 갯수 세리러 가기
		cntSquare();
		
		
		System.out.println(result);
		
	}
	// 1x1 사각형 갯수 세리는 method
	private static void cntSquare() {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]==0) continue;
				if(map[i+1][j]==1 && map[i][j+1]==1 && map[i+1][j+1]==1) result++;
			}
		}
		
	}
	// 드래곤 플라이 만들기
	private static void makeDragonFly(int x, int y, int d, int g) {
		List<Integer> [] dir = new ArrayList [g+1];
		
		for(int i=0;i<=g;i++) {
			dir[i] = new ArrayList<Integer>();
		}
		
		dir[0].add(d);
		
		for(int i=1;i<=g;i++) {
			dir[i]=dir[i-1];
			for(int j = dir[i-1].size()-1; j>=0; j--) {
				int now = dir[i-1].get(j);
				dir[i].add(now+1==4?0:now+1); 
			}
		}
		
		// g세대 드래곤 플라이 구한걸로 map에 찍어주기
		int nx = x; int ny = y;
		map[y][x]=1;
		for(int i=0;i<dir[g].size();i++) {
			int nd = dir[g].get(i);
			ny += dy[nd];
			nx += dx[nd];
			
			if(ny<0 || ny>=101 || nx<0 || nx>=101) continue;
			map[ny][nx]=1;
		}
	}
}
