//[백준] 불!
/*
4 4
####
#JF#
#..#
#..#
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fire {
	static int R,C;
	static char [][] map;
	static Queue<Point> fire = new LinkedList<Point>();
	static Queue<Point> jihun = new LinkedList<Point>();
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for(int i=0;i<R;i++) {
			char [] ch = br.readLine().toCharArray();
			for(int j=0;j<C;j++) {
				map[i][j]=ch[j];
				if(map[i][j]=='F') {
					fire.offer(new Point(i,j));
				}
				if(map[i][j]=='J') {
					if(i==0 || i == R-1 || j ==0 || j==C-1) { //만약 지훈이가 이미 가장자리에 있을 경우
						System.out.println(1);
						return;
					}
					jihun.offer(new Point(i,j));
				}
			}
		}

		//만약 출구가 없을 경우
		boolean flag=false;
		for(int i=0;i<R;i++) {
			if(map[i][0]!='#') ;
			flag = true;
			if(map[i][C-1]!='#') ;
			flag = true;
		}	
		for(int j=0;j<C;j++) {
			if(map[0][j]!='#') ;
			flag = true;
			if(map[R-1][j]!='#') ;
			flag = true;
		}
		
		if(!flag) {
			System.out.println("IMPOSSIBLE");
			return;
		}


		Point p;
		int cnt = 1;
		while(!jihun.isEmpty()) {		
			int size = fire.size();
			for(int i=0;i<size;i++) {//불의 개수 만큼 반복
				p = fire.poll();

				for(int d=0;d<4;d++) {
					int ni = p.i + dy[d];
					int nj = p.j + dx[d];

					if(ni<0 || ni>=R || nj<0 || nj>=C || map[ni][nj]!='.') continue;
					map[ni][nj]='F';
					fire.offer(new Point(ni,nj));

				}
			}
			size = jihun.size();
			for(int i=0;i<size;i++) {//지훈이 개수만큼 반복
				p = jihun.poll();
				
				if(p.i == 0 || p.i ==R-1 || p.j ==0 || p.j==C-1) {
					System.out.println(cnt);
					return;
				}
				
				for(int d=0;d<4;d++) {
					int ni = p.i + dy[d];
					int nj = p.j + dx[d];

					if(ni<0 || ni>=R || nj<0 || nj>=C || map[ni][nj]!='.') continue;
					map[ni][nj]='J';
					jihun.offer(new Point(ni,nj));

				}
			}
			
			cnt++;
		}
		System.out.println("IMPOSSIBLE");//지훈 큐 비었을 때까지 못나간 경우니까 실패
	}
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
			return "Point [i=" + i + ", j=" + j +  "]";
		}

	}
}
