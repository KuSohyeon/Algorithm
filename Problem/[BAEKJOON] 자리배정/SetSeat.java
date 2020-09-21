import java.util.Scanner;

public class SetSeat {
	static int [] dy = {1,0,-1,0}; // 하 우 상 좌
	static int [] dx = {0,1,0,-1};
	static int [][] map;
	static int N;
	static int R,C,P;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		C = sc.nextInt();
		R = sc.nextInt();
		P = sc.nextInt();
		
		map = new int[R+1][C+1];
		
		int cnt=1;
		int ni=1,nj=1;
		map[ni][nj]=cnt;
		boolean flag = true;
		while(flag){
			for(int d=0;d<4;d++) { //하 우 상 좌 순으로 반시계 방향으로 돌리면 됨
				while(true) {
				ni += dy[d];
				nj += dx[d];
				if(ni<1 || ni>R || nj<1 || nj>C) break; //만약 배열의 인덱스 밖이면 빠져나오기
				if(map[ni][nj]!=0) break; //만약 맵의 요소가 0이 아닌 경우에는 빠져나오기
				map[ni][nj]=++cnt; //숫자 늘려주기
				}
				ni -= dy[d]; // 이미 더해줬지만 조건에 걸려서 나온거 다시 되돌려주기
				nj -= dx[d];
			}
			if(cnt==R*C) break; //마지막 번호까지 할당했으면 자리 배정 끝
		}
		
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				if(map[i][j]==P) { // 사람 자리 찾아주기
					System.out.println(j +" "+ i); //출력
					return; //찾고 나서는 메인함수 종료
				}
			}
		}
		System.out.println(0); //못 찾으면 0 출력
	}
}
