//[백준] -1681번 줄 세우기
/*
 * 10 1
 * 22
 */
import java.util.Scanner;

public class BOJ_1681_Line {
	static int N,L;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		
		String no = String.valueOf(L);
		int cnt=0;
		String num = null;
		for(int i=1;i<=N;i++) {
			while(true) {
				num = String.valueOf(++cnt);
				if(!num.contains(no)) {
					break;
				}
			}
		}
		System.out.println(num);
	}
}
