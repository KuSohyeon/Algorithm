//[백준] 직사각형 네개의 합집합 면적 구하기

import java.util.Scanner;

public class RectangularArea {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int [][] input = new int[4][4];
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				input[i][j]=sc.nextInt();
			}
		}
		
		int [][] map = new int[100][100];
		for(int i=0;i<4;i++) {
			int a = input[i][0];
			int b = input[i][1];
			int c = input[i][2];
			int d = input[i][3];
			
			for(int y=b;y<d;y++) {
				for(int x=a;x<c;x++) {
					map[y][x]++;
				}
			}
		}
		
		int result=0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]>=1) {
					result++;
				}
//				System.out.print(map[i][j]+" ");
			}
//			System.out.println();
		}
		
		System.out.println(result);
		
	}
}
