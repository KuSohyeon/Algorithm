//[백준] 벼락치기 - 0/1 Knapsack

import java.util.Scanner;

public class ThunderStudy {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 시험 단원의 개수
		int T = sc.nextInt(); // 공부할 수 있는 총 시간
		
		int [] time = new int[N+1]; // 공부할 시간
		int [] score = new int[N+1]; // 단원별 점수
		int [][] study = new int[N+1][T+1]; // 비교할 배열
		
		for(int i=1;i<=N;i++) {
			time[i]=sc.nextInt(); // 예상 공부 시간
			score[i]=sc.nextInt(); // 단원 문제의 배점
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=0;j<=T;j++) {
				if(time[i]<=j) { // 충분히 공부할 시간이 된다면 비교해서 최대 값으로 넣어주기
					study[i][j]=Math.max(study[i-1][j], score[i]+study[i-1][j-time[i]]);
				}else { // 공부못하면 이전의 값 넣어주기
					study[i][j]=study[i-1][j];
				}
			}
		}
		
		// 출력
		System.out.println(study[N][T]);
		sc.close();
	}
}
