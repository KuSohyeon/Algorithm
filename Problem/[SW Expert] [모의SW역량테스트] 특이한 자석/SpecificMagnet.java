//[SW Expert] [모의 SW 역량테스트] 특이한 자석
/*
 * 판에 4개의 자석이 있고, 각 자석은 8개의 날을 가지고 있다.
 * 임의의 자석을 K번 회전
 * 하나의 자석이 1 칸 회전될 때, 붙어 있는 자석은 서로 붙어 있는 날의 자성과 다를 경우에만 인력에 의해 반대 방향으로 1 칸 회전된다.
 * K 번 자석을 회전시킨 후 획득하는 점수의 총 합을 출력하는 프로그램을 작성하라.
 * 각 자석이 S극이면 순서대로 1,2,4,8점 획득
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SpecificMagnet {
	static int K,result;
	static int [][] magnet;
	static int [] checkM;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			K = Integer.parseInt(br.readLine());
			
			magnet = new int[5][8];
			
			result=0; //tc마다 초기화
			
			for(int i=1;i<5;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<8;j++) {
					magnet[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			// K번 돌리기
			for(int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine().trim());
				int num = Integer.parseInt(st.nextToken()); // 몇 번 자석?
				int dir = Integer.parseInt(st.nextToken()); // 1은 시계방향 -1은 반시계
				// 톱니바퀴 돌릴 수 있는지 확인
				check(num,dir);
				// 돌리기
				rotation();
			}
			
			// 점수 업데이트
			for(int i=1;i<5;i++) {
				if(magnet[i][0]==1) {	
					result += Math.pow(2, i-1);
				}
			}
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void rotation() {
		for(int i=1;i<5;i++) { 
			if(checkM[i]!=0) { // 0일 경우는 돌리지 않는 경우
				int tmp =magnet[i][0],now = magnet[i][0];
				switch(checkM[i]) { // 시계방향, 반시계 방향 구분해서 돌리기
				case 1:// 시계방향
					for(int j=0;j<8;j++) {
						now = tmp;
						tmp = magnet[i][(j+1)%8];
						magnet[i][(j+1)%8] = now;	
					}
					break;
				case -1: // 반시계 방향
					tmp =magnet[i][7];
					now = magnet[i][7];
					for(int j=7;j>=0;j--) {
						now = tmp;
						tmp = magnet[i][((j-1)+8)%8];
						magnet[i][((j-1)+8)%8] = now;	
					}
					break;
				}
			}
		}
		
	}
	private static void check(int num, int dir) {
		// 돌릴 자석 관리하는 배열 만들기
		checkM = new int[5];
		// 일단 num번 자석은 무조건 돌릴거
		checkM[num]=dir;
		int newNum = num;
		// 왼쪽 확인
		while(true) {
			if(newNum-1==0) break; // 더 이상 비교할 자석이 없을 경우 break로 빠져나가기
			if(magnet[newNum][6]!=magnet[newNum-1][2]) { // 자성이 다를 경우에는 돌릴 수 있으니까 배열에 표시
				checkM[newNum-1]=checkM[newNum]==-1?1:-1;
			}
			if(magnet[newNum][6]==magnet[newNum-1][2]) { // 자성이 같을 경우에는 더이상 옆에꺼 비교하지 않도록 break를 통해 빠져나가기
				break;
			}
			newNum--; // 번호 줄여가면서 확인
			
		}
		// 오른쪽 확인(자신보다 큰 번호일 경우)
		newNum = num;
		while(true) {
			if(newNum+1==5) break; // 더 이상 비교할 자석이 없을 경우 break로 빠져나가기
			if(magnet[newNum][2]!=magnet[newNum+1][6]) {// 자성이 다를 경우에는 돌릴 수 있으니까 배열에 표시
				checkM[newNum+1]=checkM[newNum]==-1?1:-1;
			}
			if(magnet[newNum][2]==magnet[newNum+1][6]) { // 자성이 같을 경우에는 더이상 옆에꺼 비교하지 않도록 break를 통해 빠져나가기
				break;
			}
			newNum++; // 번호 늘려가면서 확인
			
		}
		
		
	}
}
