import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class RussiaFlag {
	static int N,M, result;
	static char [][] map;
	static int [] choice;
	static int white, blue, red;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char [N][M];
			choice = new int[3];
			result = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				char [] crr = br.readLine().toCharArray();
				for(int j=0;j<M;j++) {
					map[i][j]=crr[j];
				}
			}
			
			comb(1,1); 
			
			System.out.println("#"+tc+" "+result);
			
		}
	}
	//N개의 줄 중 3개의 줄을 고르기 고른 게 시작지점
	private static void comb(int cnt, int start) {  
		if(cnt==3) {
			makeFlag(); // 다 골랐으면 국기 만들러 가기
			return;
		}
		for(int i=start;i<N;i++) {
			choice[cnt]=i;
			comb(cnt+1,i+1);
		}
		
	}
	private static void makeFlag() {
		int cnt=0;
		for(int j=0;j<M;j++) {
			for(int i=0;i<choice[1];i++) {// 0부터 파란색 전까지 무조건 흰색
				if(map[i][j]!='W') {
					cnt++;
				}
			}
			for(int i=choice[1];i<choice[2];i++) { // 인덱스 1부터 2전까지는 무조건 파란색
				if(map[i][j]!='B') {
					cnt++;
				}
			}
			for(int i=choice[2];i<N;i++) { // 배열[2]부터는 무조건 빨간색
				if(map[i][j]!='R') {
					cnt++;
				}
			}
		}
		// 최소로 바꾸게 하는 값 업데이트
		result = Math.min(result, cnt);
	}
}
