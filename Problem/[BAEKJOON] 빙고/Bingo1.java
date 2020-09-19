//[백준] 2578번 빙고

import java.util.Scanner;
// 사회자꺼는 1차원 배열에 집어 넣기
// 빙고판은 2차원 배열에 집어넣기
// 빙고 검사하는 메소드 만들기
public class Bingo1 {
	static int [][] map;
	static int [] speaker;
	static boolean [][] bingo;
	static int cnt;
	static int [] dy = {1,0,1,1}; // 하 우 좌하 우하
	static int [] dx = {0,1,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		map = new int[5][5]; // 숫자 적힌 빙고판
		bingo = new boolean[5][5]; //체크용 빙고판
		speaker = new int[25]; //사회자는 그냥 1차원 배열으로
		cnt = 0;

		// 빙고판 입력
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				map[i][j] = sc.nextInt();
				bingo[i][j]=false;
			}
		}
		// 사회자 꺼 입력
		for(int i=0;i<25;i++) {
			speaker[i]=sc.nextInt();
		}


		for(int s=0;s<25;s++) {
			if(s>10) { // 10개 미만 불렀는데 3빙고가 나올리가 없다.
				if(check()) { // 빙고 맞는지 체크해 주는 메소드
					break;
				}
			}
			cnt++; //사회자가 빙고 부른 횟수 증가

			out :for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(map[i][j]==speaker[s]) { //사회자가 부른거 체크해주기
						bingo[i][j]=true; 
						break out;
					}
				}	
			}
		}
		// 출력
		System.out.println(cnt);

	}
	private static boolean check() {
		
		int line=0;
		// 첫번째 가로줄, 세로줄만 체크해주면 됨 5*5 빙고니까 
		for(int j=0;j<5;j++) { //가로줄부터 체크
			if(bingo[0][j]) { // 만약 0행 j열의 빙고가 체크되어 있으면
				int ni=0,nj=0;
				for(int d=0;d<4;d++) { //하 우 좌하 우하 방향으로 체크해주기
					int count=1;
					ni = dy[d];
					nj = j + dx[d];
					
					if(ni<0 || ni>=5 || nj<0 || nj>=5) continue; // 배열의 범위 밖이면 나가리
					if(!bingo[ni][nj]) continue; // 만약 아직 사회자가 안 부른것도 나가리
					count++;
					while(true) {
						ni += dy[d];
						nj +=dx[d];
						
						if(ni<0 || ni>=5 || nj<0 || nj>=5) break;
						if(!bingo[ni][nj]) break;
						count++;
						if(count==5) //만약 5개가 누적이되면
						{
							line++; //한줄 더해주기
							if(line>=3) return true; //만약 줄이 3줄이 완성되면 빙고!!
							break;
						}
						
					}

				}
			}
		
		}
		for(int i=1;i<5;i++) {	// 후에 세로줄 체크
			if(bingo[i][0]) {
				
				int ni=0,nj=0;
				for(int d=1;d<4;d++) {
					int count=1;
					ni = i + dy[d];
					nj = dx[d];
					
					if(ni<0 || ni>=5 || nj<0 || nj>=5) continue;
					if(!bingo[ni][nj]) continue;
					count++;
					while(true) {
						ni += dy[d];
						nj += dx[d];
						
						if(ni<0 || ni>=5 || nj<0 || nj>=5) break;
						if(!bingo[ni][nj]) break;
						count++;
						if(count==5)
						{
							line++;
							if(line==3) return true;
							break;
						}
						
					}

				}
			}
		}
		
		return false;

	}
}
