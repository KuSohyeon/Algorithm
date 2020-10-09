import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//[백준] Puyo Puyo
/*
 * 뿌요 뿌요는 12*6
 * 같은 색의 뿌요가 4개 이상이면 터뜨리기
 * 한 판이 끝나면 내려주기
 * 터진 후 뿌요들이 내려오면 +1 연쇄
 * 몇 연쇄가 일어나는지 계산
 */
public class PuyoPuyo {
	static int result;
	static boolean flag;
	static char [][] map,copy;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map = new char [13][6];
		copy = new char [13][6];
		
		Arrays.fill(map[0],'.'); // 내려줄거 대비해서 맨 위 칸은 빈칸으로 초기화
		for(int i=1;i<=12;i++) {
			char [] ch = sc.next().toCharArray();
			for(int j=0;j<6;j++) {
				map[i][j]=ch[j];
			}
		}
		
		while(true) {
			flag = false; // 게임 종료 여부를 판단한 flag
			v = new boolean[13][6]; // 한 판마다 방문 체크 배열 초기화
			// 깰 뿌요 고르기
			selectPuyo(); 
			if(!flag) break;
			result++;
			// 내려주기
			down();
		}
		
		System.out.println(result);
	}
	/** 깰 뿌요 고르기 **/
	private static void selectPuyo() {
		for(int i=1;i<=12;i++) {
				for(int j=0;j<6;j++) {
					if(map[i][j]=='.' || v[i][j]) continue; // 색깔 뿌요랑 방문 안한 뿌요만  bfs
					puyo(i,j,map[i][j]);
				}
			}
	}
	/** 한판이 끝나면 뿌요들 내려주기 **/
	private static void down() {
		while(true) {

			boolean flag = false;
			for(int j=0;j<6;j++) {
				for(int i=1;i<=12;i++) {
					if(i+1<=12 && map[i][j]!='.' && map[i+1][j]=='.') { // 내릴 수 있는 애들만(나는 뿌요고 내 밑은 빈칸인 경우)
						map[i+1][j]=map[i][j]; // 밑에칸으로 한칸 내리고
						map[i][j]='.'; // 내칸은 빈칸으로 표시
						flag=true; // 내릴게 있다면 flag에 표시
					}
				}
			}
			if(!flag) break; // 하나도 내린게 없으면 이제 그만 내리기

		}
		
	}
	/** 4개 이상인 뿌요들 판단해서 깨주기 **/
	private static void puyo(int i, int j, char c) {
		Queue<Point> q = new LinkedList<Point>();
		
		q.offer(new Point(i,j));
		v[i][j]=true;
		
		int cnt = 1;
		
		//copy 배열 사용하기(원본 보존)
		for(int y=1;y<=12;y++) {
			for(int x=0;x<6;x++) {
				copy[y][x]=map[y][x];
			}
		}
		copy[i][j]='.'; // 일단 뿌요 체크
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				
				if(ni<1 || ni>12 || nj<0 || nj>=6 || v[ni][nj] || map[ni][nj]!=c) continue;
				v[ni][nj]=true;
				copy[ni][nj]='.';
				cnt++; // 뿌요 개수 세리기
				q.offer(new Point(ni,nj));
			}
		}
		
		if(cnt>=4) { // 뿌요가 4개 이상이면
			flag=true; // 게임 진행 상황 업데이트 해주고
			// 뿌요 업데이트
			for(int y=1;y<=12;y++) {
				for(int x=0;x<6;x++) {
					map[y][x]=copy[y][x];
				}
			}
		}
	}
	private static class Point{
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
}
