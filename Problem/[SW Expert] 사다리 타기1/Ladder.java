//[SW Expert] [S/W 문제해결 기본] 2일차 - Ladder1

import java.util.Scanner;

public class Ladder {
	static int [][] ladder = new int [100][100];
	static int nj;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int tc=1;tc<=10;tc++) {
			int T = sc.nextInt(); //tc받을거
			
			//입력
			for(int i=0;i<100;i++) {
				for(int j=0;j<100;j++) {
					ladder[i][j]=sc.nextInt();
				}
			}
			
			//시작위치 찾기
			int start=0;
			for(int j=0;j<100;j++) {
				if(ladder[99][j]==2) {
					start=j;
					break;
				}
			}
			
			nj = start;
			
			//좌우 없으면 위로가기
			for(int i=99;i>=0;i--) {
				boolean flag = false;
				while(nj-1>=0 && ladder[i][nj-1]!=0) {
					nj--;
					flag=true;
				}
				if(flag) {
					continue;
				}
				while(nj+1<100 && ladder[i][nj+1]!=0 ) {
					nj++;
					flag=true;
				}
				if(flag) {
					continue;
				}
			}
			

			System.out.println("#"+tc+" "+nj);
		}
		sc.close();
	}
	

}
