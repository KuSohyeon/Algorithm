//[백준] 로봇 시뮬레이션
// 가로, 세로 방향 조심하기!!

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotSimulation {
	static int A,B,N,M;
	static int [][] map;
	static int [] dy = {-1,0,1,0}; //상 우 하 좌
	static int [] dx = {0,1,0,-1};
	static Data [] robot;
	static Info [] order;
	static class Data{
		int i;
		int j;
		int dir;
		public Data(int i, int j, int dir) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", dir=" + dir + "]";
		}
	}
	static class Info{
		int num;
		int way;
		int cnt;
		public Info(int num, int way, int cnt) {
			super();
			this.num = num;
			this.way = way;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Info [num=" + num + ", way=" + way + ", cnt=" + cnt + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[A+1][B+1];
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 로봇 개수
		M = Integer.parseInt(st.nextToken()); // 명령 개수
		
		robot = new Data[N+1];
		order = new Info[M];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			char dir = st.nextToken().charAt(0);
			int d = 0;
			switch(dir) {
			case 'N':
				d = 1;
				break;
			case 'E':
				d = 2;
				break;
			case 'S':
				d = 3;
				break;
			case 'W':
				d = 0;
				break;
			}
			map[r][c]=i;
			robot[i] = new Data(r,c,d);
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			char way = st.nextToken().charAt(0);
			int dir = 0;
			switch(way) {
			case 'L':
				dir = -1;
				break;
			case 'R':
				dir = 1;
				break;
			case 'F':
				dir = 0;
				break;
			}
			int cnt = Integer.parseInt(st.nextToken());
			order[i] = new Info(num,dir,cnt);
		}
		
		System.out.println(game());
	}
	private static String game() {
		for(int m=0;m<M;m++) {
			Info now = order[m];
			int num = now.num;
			int way = now.way;
			int replay = now.cnt;
			Data rob = robot[now.num];
			for(int i=0;i<replay;i++) {
				if(way==0) {
					map[rob.i][rob.j]=0;
					rob.i += dy[rob.dir];
					rob.j += dx[rob.dir];
					if(rob.i<1 || rob.i>A || rob.j<1 || rob.j>B) {
						return "Robot "+num+" crashes into the wall";
					}else if(map[rob.i][rob.j]!=0) {
						return "Robot "+num+" crashes into robot "+map[rob.i][rob.j];
					}
					map[rob.i][rob.j]=num;
				}else {
					rob.dir += way;
					if(rob.dir==-1) rob.dir = 3;
					if(rob.dir==4) rob.dir = 0;
				}
			}
		}
		return "OK";
	}
}
