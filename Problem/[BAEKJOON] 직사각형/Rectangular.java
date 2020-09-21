//[백준] 2527번 직사각형
/*
3 10 50 60 100 100 200 300
45 50 600 600 400 450 500 543
11 120 120 230 50 40 60 440
35 56 67 90 67 80 500 600
출력:
d
a
a
b
 */
import java.util.Scanner;

public class Rectangular {
	static int a1x, a1y, a2x, a2y, b1x, b1y, b2x, b2y;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for(int s=0;s<4;s++) {
			a1x=sc.nextInt(); //가로
			a1y=sc.nextInt(); //세로
			a2x=sc.nextInt();
			a2y=sc.nextInt();

			b1x=sc.nextInt();
			b1y=sc.nextInt();
			b2x=sc.nextInt();
			b2y=sc.nextInt();

			char result = getRes();
			
			System.out.println(result);
			
		}
	}
	private static char getRes() {
		// 겹치는 부분이 없는 것 부터 처리해서 하나씩 치기
		if(a2x<b1x || a2y<b1y || b2x<a1x || b2y<a1y) return 'd';
		// 점
		if(a2x==b1x) {
			if(a2y==b1y) return 'c';
			if(a1y==b2y) return 'c';
		}
		if(a1x==b2x) {
			if(a1y==b2y) return 'c';
			if(a2y==b1y) return 'c';
		}
		//선
		if(a2x==b1x || a2y==b1y || b2x==a1x || b2y==a1y) {
			return 'b';
		}
		//나머지 = 면
		return 'a';
	}
}
