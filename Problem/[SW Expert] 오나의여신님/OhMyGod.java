//[SW Expert] 오 나의 여신님
/*
2
5 3
D*S
.X.
.X.
.X.
...
5 3
D*S
...
.X.
.X.
...
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class OhMyGod {
	static int [] dr = {-1,1,0,0}; //상 하 좌 우
	static int [] dc = {0,0,-1,1};
	static int N,M;
	static Queue<Point> devils;
	static Queue<Point> suyeon;
	static char [][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N][M];

			devils = new LinkedList<>();
			suyeon = new LinkedList<>();


			for(int i=0;i<N;i++) {
				char [] ch = sc.next().toCharArray();
				for(int j=0;j<M;j++) {
					map[i][j]=ch[j];
					if(map[i][j]=='*') {
						devils.add(new Point(i,j));
					}if(map[i][j]=='S') {
						suyeon.add(new Point(i,j));
					}
				}
			}

			// 영원히 반복하면서
			int cnt=1;
			// 수연이 반복문 안에서 지은이를 만나서 break걸때 while에 대해서 break해야하니까 라벨
			out : while(true) {
				int size = suyeon.size();
				//수연이 큐 사이즈 0이면 게임오버이므로 반복 종료
				if(size==0) {
					cnt=0;
					break;
				}
				//				수연이의 갯수만큼 반복하면서 수연이를  꺼내서
				//		수연이가 지은이한테 가있으면 횟수 기억 후 종료
				//		아니라면. 각 수연이에 대해서 4방으로 갈수있는 곳이면 큐에 추가 .인 곳
				for(int i=0;i<size;i++) {
					Point p = suyeon.poll();
					if(map[p.r][p.c]=='*') continue;
					for(int d=0;d<4;d++) {
						int nr = p.r+dr[d];
						int nc = p.c+dc[d];
						//밖으로 나가면 아웃.
						if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
						//지은이를 만나면 완전 종료
						if(map[nr][nc]=='D') break out;
						//평지가 아니면 못감
						if(map[nr][nc]!='.') continue;
						map[nr][nc]='S';
						suyeon.add(new Point(nr,nc));
					}
				}
				//				악마큐의 사이즈만큼 반복하면서
				size = devils.size();
				for(int i=0;i<size;i++) {
					Point p = devils.poll();
					//						악마도 펼쳐나감 ( . 이거나 수연이 )
					for(int d=0;d<4;d++) {
						int nr = p.r+dr[d];
						int nc = p.c+dc[d];
						//밖으로 나가면 아웃.
						if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
						if(map[nr][nc]=='S' || map[nr][nc]=='.') {
							map[nr][nc]='*';
							
							devils.add(new Point(nr,nc));
						}
					}
				}
				//1초 증가
				cnt++;
				if(suyeon.size()==0) {
					cnt=0;
					break;
				}
			}
			if(cnt==0){
				System.out.println("#"+tc+" "+"GAME OVER");
			}else{
				System.out.println("#"+tc+" "+cnt);
			}

		}
	}

	static class Point{
		int r,c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

	}
}
