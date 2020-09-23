import java.util.Scanner;

public class ContinusNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int [] num = new int[N];
		
		for(int i=0;i<N;i++) {
			num[i]=sc.nextInt();
		}
		
		if(N==K) { // N과 K가 같은 경우 따로 처리해주기
			int result=0;
			for(int i: num) {
				result+=i;
			}
			System.out.println(result);
			return;
		}
		
		int max = 0;
		int tmp = 0;
		
		for(int i=0;i<N-K+1;i++) {//K개 묶어서 더해야하니까 인덱스 오류 안나려면 N-K+1까지만 해야됨
			tmp=0;
			for(int j=0;j<K;j++) { //연속적인 K개 다 더해보기
				tmp += num[i+j];
			}
			max = Math.max(max, tmp); // 최대값으로 넣어주기
		}
		// 최대값 출력
		System.out.println(max);
	}
}
