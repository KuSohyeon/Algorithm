//[SW EXpert] 한빈이와 Spot Mart(조합)
/*
4
3 45
20 20 20
6 10
1 2 5 8 9 11
4 100
80 80 60 60
4 20
10 5 10 16
 */
import java.util.Scanner;

public class SpotMart {
	static int result;
	static int N,M;
	static int [] weight,bag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			N = sc.nextInt(); //과자 개수
			M = sc.nextInt(); //과자 무게 제한
			
			weight = new int[N];
			bag = new int[2];
			
			result=0; //tc마다 무게 초기화
			
			for(int i=0;i<N;i++) {
				weight[i] = sc.nextInt();//과자 무게
			}
			
			comb(0,0);
			
			if(result==0) {//들고갈 수 없는 경우 -1 출력
				result=-1;
			}
			
			System.out.println("#"+tc+" "+result);
		}
		
		
		sc.close();
	}
	private static void comb(int cnt, int start) {
		
		if(cnt == 2) {
			int sum=0;
			for(int i=0;i<2;i++) {
				sum+=bag[i];
			}
			if(sum<=M && result<sum) {
				result=sum;
			}
			
			return;
		}
		for(int i = start;i<N;i++) {
			bag[cnt] = weight[i];
			comb(cnt+1,i+1);
		}
		
	}
}
