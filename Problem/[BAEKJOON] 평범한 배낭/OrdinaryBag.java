//[백준] 평범한 배낭

import java.util.Scanner;

public class OrdinaryBag {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 물품의 수
		int K = sc.nextInt(); // 최대 무게
		
		int [] weight = new int[N+1];
		int [] value = new int[N+1];
		int [][] knapsack = new int[N+1][K+1];
		
		for(int i=1;i<=N;i++) {
			weight[i]=sc.nextInt();
			value[i]=sc.nextInt();
		}
		
		for(int i=1;i<=N;i++) { // 각 아이템 하나씩 넣어보기
			for(int j=0;j<=K;j++) { // 무게마다 반복
				if(weight[i]<=j) { // 담을 수 있다면 이전 값이랑 현재 아이템 넣은 거랑 비교해서 최대값으로 넣어주기
					knapsack[i][j]=Math.max(knapsack[i-1][j], value[i]+knapsack[i-1][j-weight[i]]);
				}else { // 넣을 수 없다면 이전 값 넣어주기
					knapsack[i][j]=knapsack[i-1][j];
				}
			}
		}
		//출력
		System.out.println(knapsack[N][K]);
		sc.close();
	}
}
