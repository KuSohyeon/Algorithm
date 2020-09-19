//[백준] 2563번 색종이

import java.util.Scanner;

public class ColorPaper {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int [][] map = new int[100][100];
		
		int N = sc.nextInt();
		
		for(int s=0;s<N;s++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for(int i=x;i<x+10;i++) {
				for(int j=y;j<y+10;j++) {
					map[i][j]++;
				}
			}
			
		}
		
		int result=0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]!=0) {
					result++;
				}
			}
		}
		
		System.out.println(result);
	}
}
