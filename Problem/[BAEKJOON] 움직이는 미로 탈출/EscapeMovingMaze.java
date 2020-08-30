//[백준] 움직이는 미로탈출
/*
........
........
........
........
........
........
##......
........
출력 : 0
........
........
........
........
........
.#######
#.......
........
출력 : 1
 */
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class EscapeMovingMaze {
	static char [][] map;
	static Queue<Data> wall = new LinkedList<>();
	static Queue<Data> person = new LinkedList<>();
	static int [] dy = {0,-1,1,0,0,-1,-1,1,1};// 상 하 좌우 좌상 우상 좌하 우하
	static int [] dx = {0,0,0,-1,1,-1,1,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[8][8];

		for(int i=0;i<8;i++) {
			char [] ch = br.readLine().toCharArray();
			for(int j=0;j<8;j++) {
				map[i][j]=ch[j];
				if(map[i][j]=='#') {
					wall.offer(new Data(i,j));
				}
			}
		}
		//벽없으면 그냥 탈출 가능
		if(wall.size()==0) {
			System.out.println("1");
			return;
		}

		bfs();


	}
	private static void bfs() {

		person.offer(new Data(7,0));//욱제 시작위치

		while(!person.isEmpty()) {

			int size = person.size();
			for(int i=0;i<size;i++) {
			
				Data cur = person.poll();
				//만약 벽이 하나도 없으면 탈출 할 수 있으니까 그만하기
				int cnt=0;
				for(int l=0;l<8;l++) {
					for(int j=0;j<8;j++) {
						if(map[l][j]=='#')
							cnt++;
					}
				}
				
				if(cnt==0) { //벽없으면 탈출
					System.out.println("1");
					return;
				}
				
				if(map[cur.i][cur.j]=='#') { // 꺼냈더니 벽이면 버리기
					continue;
				}
				if(cur.i == 0 && cur.j==7) { // 탈출 조건
					System.out.println("1");
					return;
				}

				for(int d=0;d<9;d++) {
					int ni = cur.i+dy[d];
					int nj = cur.j+dx[d];

					if(!check(ni,nj) || map[ni][nj] == '#') continue;

					person.offer(new Data(ni,nj));

				}
			}

			down();//벽 내리기


		}
		System.out.println("0");


	}
	private static void down() {//벽 내리는 메소드
		for (int i = 7; i >= 0; i--) {
			for (int j = 7; j >= 0; j--) {
				if (i - 1 < 0)
					map[i][j] = '.';
				else
					map[i][j] = map[i - 1][j];
			}
		}
	}
	static boolean check(int i,int j) { // 행렬 안에서 노는지 확인
		if(i<0 || i>=8 || j<0 || j>=8) return false;
		return true;
	}
	static class Data{
		int i;
		int j;
		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}
}
