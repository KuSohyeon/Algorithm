//[백준] 미친아두이노
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class CrazyArduino {
	static int R,C;
	static char [][] map;
	static int [] moveDir;
	static int [] dy = {0,1,1,1,0,0,0,-1,-1,-1};
	static int [] dx = {0,-1,0,1,-1,0,1,-1,0,1};
	static List<Point> arduino;
	static Point jongsu;
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		arduino = new LinkedList<CrazyArduino.Point>();
		
		for(int i=0;i<R;i++) {
			String s = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j]= s.charAt(j);
				if(map[i][j]=='R') {
					arduino.add(new Point(i,j));
				}else if(map[i][j]=='I') {
					jongsu = new Point(i,j);
				}
			}
		}
		
		char [] dir = br.readLine().toCharArray();
		int len = dir.length;
		moveDir = new int [len];
		
		for(int i=0;i<len;i++) {
			moveDir[i]=dir[i]-'0';
		}
		
		// 게임 시작
		for(int i=0;i<len;i++) {
			game(moveDir[i],i+1);
		}
		
		
		// 출력
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	private static void game(int dir, int cnt) {
		
		char [][] afterMove = new char[R][C];
		for(int i=0;i<R;i++) {
			Arrays.fill(afterMove[i], '.');
		}
		// 종수 이동
		Point now = jongsu;
		jongsu = new Point(now.i+dy[dir], now.j+dx[dir]);
		// 종수가 이동한 곳이 아두이노가 있던 곳이라면 게임 종료
		if(map[jongsu.i][jongsu.j]=='R') { 
			System.out.println("kraj "+cnt);
			System.exit(0);
		}
		afterMove[jongsu.i][jongsu.j]='I';
		
		// 아두이노 이동
		for(int i=0;i<arduino.size();i++) {
			 Point CR = arduino.get(i);
			 int li=CR.i, lj=CR.j, dist=987654321;
			 map[li][lj]='.';
			 for(int d=1;d<=9;d++) {
				 if(d==5) continue;
				 int ni = CR.i + dy[d];
				 int nj = CR.j + dx[d];
				 int ndist = Math.abs(ni-jongsu.i)+Math.abs(nj-jongsu.j);
				 if(dist>ndist) {
					 dist = ndist;
					 li = ni;
					 lj = nj;
				 }
			 }
			 if(afterMove[li][lj]=='I') {// 종수가 미친 아두이노를 만날경우 종료
				 System.out.println("kraj "+cnt);
				 System.exit(0);
			 }
			 if(afterMove[li][lj]=='R' || afterMove[li][lj]=='D') { // 한 칸에 2개 이상의 아두이노가 있을 경우 모두 파괴
				 afterMove[li][lj]='D';
				 continue;
			 }
			 afterMove[li][lj]='R';
		}
		
		copy(afterMove); // 다음판을 위해 맵 카피
	}
	private static void copy(char [][] copy) {
		List<Point> list = new ArrayList<CrazyArduino.Point>();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(copy[i][j]=='D') {// 한 곳에 여러개의 아두이노가 있다면 폭파
					copy[i][j]='.';
				}
				map[i][j] = copy[i][j];
				if(map[i][j]=='R') list.add(new Point(i,j)); // 아두이노 리스트에 넣어주기
			}
		}
		// 아두이노 리스트 갱신
		arduino.clear();
		arduino = list;
		
	}
}
