import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 한 이닝에 3 아웃이 발생하면 이닝이 종료
 * 경기가 시작되기 전까지 순서를 정해야하고 경기중에는 타순을 변경할 수 없다.
 * 9번타자까지 공을 쳤는데 3아웃이 발생하지 않으면 이닝은 끝나지 않는다.
 * 타순은 이닝이 변경되어도 순서를 유지해야한다.
 * 2이닝에 6번 타자가 마지막이었다면 3이닝은 7번타자부터 타석에 선다.
 * 1루 -> 2루 -> 3루 -> 홈 : 1점
 * 아인티는 1번 선수를 4번 타자로 미리 결정했다.
 * 가장 많은 득점을 하는 타순을 찾고 그 때의 득점을 구하기
 * 
 */

public class baseball {
	static int T,score, player, out;
	static int [][] step;
	static int [] turn,temp;
	static boolean [] isSelected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		step = new int[T][9];
		turn = new int[9];
		temp = new int[8];
		isSelected = new boolean [9];
		
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<9;j++) {
				step[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		perm(0);
			
		System.out.println(score);
		
	}

	private static void perm(int cnt) {
		if(cnt==8) {
			goGame();
			return;
		}
		for(int i=1;i<9;i++) {
			if(isSelected[i]) continue;
			temp[cnt]=i;
			isSelected[i]=true;
			perm(cnt+1);
			isSelected[i]=false;
		}
		
	}
	static int [] order;
	private static void goGame() {
		int tmpscore=0;
		order = new int[9];
		setPlayer(0);
		int startP = 0;
		int [] before = new int[9];

		
		for(int tc=0;tc<T;tc++) {
			out = 0; // 매 경기마다 out 초기화
			boolean [] ru = new boolean [4];
			if(tc>=1) { // 순서 바꿔주기
				setPlayer2(tc,startP,before);
			}
			int i=0;
			while(true){
				if(i==9) {
					i=0;
				}
				ru[0]=true;
				switch(turn[i]) {
				case 1:
					tmpscore+=run(1,ru);
					break;
				case 2:
					tmpscore+=run(2,ru);
					break;
				case 3:
					tmpscore+=run(3,ru);
					break;
				case 4:
					for(int r=0;r<4;r++) {
						if(ru[r]) {
							tmpscore++;
						}
					}
					Arrays.fill(ru, false); // 모든 주자는 홈으로 갔으니 루에는 주자가 없음
					break;
				case 0:
					ru[0]=false;
					out++; // 아웃 증가
					break;
					
				}
				if(out==3) {
					startP = i+1;
					before = Arrays.copyOfRange(order, 0, 9);
					break;
				}
				i++;
			}
			
		}
		score = Math.max(score, tmpscore);
		
	}

	private static int run(int num, boolean[] ru) {
		int get = 0;
		for(int r=3;r>=0;r--) {
			if(ru[r]) {
				int nr = r + num; // 달리기
				ru[r]=false; // 뛰었으니까 원래자리엔 없음
				if(nr>3) { // 홈에 도착하면 점수 더해주고
					get++;
					continue;
				}
				ru[nr]=true; // 홈에 도착하지 못했다면 도착 위치 표시
				
			}
		}
		return get;
	}

	private static void setPlayer(int time) {
		// 4번 타자는 1번 타자로 세팅
		turn[3]=step[time][0];
		order[3]=0;
		for(int i=0;i<9;i++) {
			if(i==3) continue;
			if(i>3) {
				turn[i]=step[0][temp[i-1]];
				order[i]=temp[i-1];
				continue;
			}
			turn[i]=step[0][temp[i]];
			order[i]=temp[i];
			
		}
		
	}
	
	private static void setPlayer2(int time, int startP, int [] before) { // 이전 판에서 마지막으로 아웃 당한 주자 다음 주자가 1번 주자
		for(int i=0,j=startP;i<9;i++,j++) {
			turn[i]=step[time][before[j%9]];
			order[i]=before[j%9];
		}
	}
}
