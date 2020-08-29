//[백준] Z
/*
3 7 7
출력 : 63
 */

import java.util.Scanner;

public class Z {
	static int N,R,C,num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		//2의 N승 구하기
		int size = (int) Math.pow(2, N);
		//도착지점
		R = sc.nextInt();
		C = sc.nextInt();
		
		divide(0,0,size);
	}

	private static void divide(int i, int j, int size) {
		if(size==2) {
			for(int k=i;k<i+2;k++) {
				for(int h=j;h<j+2;h++) {
					if(k==R && h==C) {
						System.out.println(num);
						return;
					}
					num++;
				}
			}
			return;
		}
		
		
		int size2 = size/2;
		
		divide(i,j,size2);
		divide(i,j+size2,size2);
		divide(i+size2,j,size2);
		divide(i+size2,j+size2,size2);
		
	}
}