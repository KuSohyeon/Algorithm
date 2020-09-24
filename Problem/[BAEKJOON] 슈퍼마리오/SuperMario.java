//[백준] 2851번 슈퍼마리오

import java.util.Scanner;

public class SuperMario {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int [] eat = new int[10];
		
		for(int i=0;i<10;i++) {
			eat[i]=sc.nextInt();
		}
		
		int sum=0;
		
		for(int i=0;i<10;i++) {
			int before = Math.abs(100-sum); // 먹기전에 100이랑 점수 차 비교
			int after = Math.abs(100-(sum+eat[i])); // 먹고나서 100이랑 점수 차 비교
			if(before<after) { // 먹고 나서가 100이랑 더 멀면
				break; // 그만 먹기
			}
			sum+=eat[i]; // 먹어야지 가까워지면 먹기
		}
		
		System.out.println(sum);
	}
}
