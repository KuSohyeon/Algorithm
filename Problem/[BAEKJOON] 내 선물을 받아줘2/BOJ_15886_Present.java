//[백준] 내 선물을 받아줘2
// 구사과의 시작 위치의 상관없이 항상 선물을 가져갈 수 있는 곳은 EW인 곳.........

import java.util.Scanner;

public class BOJ_15886_Present {
	static int N;
	static char [] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new char[N];
		
		String street = sc.next();
		for(int i=0;i<N ;i++) {
			map[i] = street.charAt(i);
		}
		
		int result = 0;
		
		for(int i=0;i<N-1;i++) {
			if(map[i] == 'E' && map[i+1] == 'W') {
				result++;
				i++;
			}
		}
		
		System.out.println(result);

	}
}
