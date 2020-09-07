//[SW Expert] 보호 필름
/*
10
6 8 3
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
6 8 3
1 1 1 1 0 0 1 0
0 0 1 1 0 1 0 1
1 1 1 1 0 0 1 0
1 1 1 0 0 1 1 0
1 1 0 1 1 1 1 0
1 1 1 0 0 1 1 0
#1 2
#2 0
 */

import java.util.Arrays;
import java.util.Scanner;

public class ProtectFilm {
	//필요한 변수 선언
	static int D,W,K; //행의 개수, 열의 개수, 연속되어져야하는 기준
	static int [][] film;
	static int ans;//정답을 저장할 변수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int tc=1;tc<=T;tc++) {
			//입력
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();

			film = new int[D][W];

			for(int i=0;i<D;i++) {
				for(int j=0;j<W;j++) {
					film[i][j] = sc.nextInt();
				}
			}

			ans = 987654321;// 최소값 초기화
			
			//약품 처리 안해도 성능검사 완료되면 바로 값 출력하고 다음 tc로 넘어가기
			if(isOk()) {
				System.out.println("#"+tc+" "+ans);
				continue;
			}
			solve(0,0);

			System.out.println("#"+tc+" "+ans);
		}//tc end

	}//main end

	// 각 행에 대해 3가지 경우의 수를 검사하는 재귀 함수
	// row 행에 대해서, 그냥 두는 경우. A로 바꾼 경우. B로 바꾸는 경우 세가지에 대해 검사
	// 바꾼 횟수를 cnt에 기억하고 다닌다.
	static void solve(int row,int cnt) {
		if(isOk()) {
			// 현재 셀이 통과되는 상태라면, 지금까지 변경한 변경횟수 중 가장 작은값을 기억해 내자.
			ans = Math.min(ans,cnt);	
			return;
		}
		// 가지치기. cnt가 이미 발견된 ans보다 커졌으면 더 볼 것도 ㅇ벗음
		if(ans<cnt) {
			return;
		}
		// 기저 조건 : row가 D에 도달했으면 더 이상 검사할 행이 남아있지 않음. 끝!@!!
		if(row==D) {
			return;
		}
		
		// 현재 행을 그대로 두고 다음행으로 이동
		solve(row+1,cnt);
		
		// 원래 값으로 돌려놓기 위해서 현재 행의 값을 기억
		 int [] tmp = new int[W];
		 for(int i=0;i<W;i++) {
			 tmp[i]=film[row][i];
		 }
//		 tmp = Arrays.copyOfRange(film[row], 0, W); //api 사용버전
		// 현재 행을 A로 다 바꿔버리고 다음행으로 이동
		 // A로 다 바꿔버림
		Arrays.fill(film[row], 0);
		 solve(row+1,cnt+1);
		// 현재 행을 B로 다 바꿔버리고 다음 행으로 이동
		 Arrays.fill(film[row], 1);
		 solve(row+1,cnt+1);
		 // 다시 원복
		 for(int i=0;i<W;i++) {
			 film[row][i]=tmp[i];
		 }
//		 film[row] = Arrays.copyOfRange(tmp,0,W); //api 사용 버전
	

	}
	//필름이 통과되는지 확인
	static boolean isOk() {
		// 모든 열에 대해서 검사.
		// 현재 검사하는 열의 첫번째 행부터 마지막 행까지 검사하면서 연속된 같은 셀의 특성의 개수를 센다.
		// 끝행까지 왔는데 최대 연속된 특성의 개수가 K에 미치지 못한다면 return false;
		// 모든 열이 검사가 끝났는데 여전히 코드가 살아서 여기까지 왔다면return true;
		for(int j=0;j<W;j++) {
			int cnt=1;
			for(int i=0;i<D-1;i++) {
				if(film[i][j]==film[i+1][j]) {
					cnt++;
				}
				else { 
					cnt=1;
				}
				if(cnt==K) break;
			}
			if(cnt<K) {
				return false;
			}
		}
		return true;
	}

}// class end
