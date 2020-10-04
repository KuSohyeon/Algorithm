//[SWEA] 2016년 요일 맞추기

import java.util.Scanner;

public class DayOfTheWeek {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			int result =0;
			
			int m = sc.nextInt();
			int d = sc.nextInt();
			
			int [] month = {0,31,29,31,30,31,30,31,31,30,31,30,31}; // 월 별 일 배열에 저장
			
			int day=0;
			
			for(int i=1;i<m;i++) { // 달 수 만큼 더해주기
				day += month[i];
			}
			 
			day += d-1; // 1일 기준이니까 -1해주기
			
			result = (day+4)%7; //1월 1일은 목요일 기준으로 세리니까 +4 %7
			
			// 출력
			System.out.println("#"+tc+" "+result);
		}
	}
}
