//[SW Expert] [S/W 문제해결 기본] 2일차 - Ladder1

import java.util.Scanner;

public class Ladder1 {
	static int [][] ladder = new int [100][100];
	static int [] dy = {0,0,-1};
	static int [] dx = {-1,1,0};
	static int ni,nj;
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
			ni=99;
			
			for(int i=99;i>=0;i--) {
				for(int j=0;j<100;j++) {
					for(int d=0;d<3;d++) {//좌 우확인후 없으면 위로가기
						ni = ni + dy[d];
						nj = nj + dx[d];
						if(ni<0 || nj<0 || nj>=100 || ladder[ni][nj]!=1) {
							ni -=dy[d];//만약 못가는 경우 원래 위치로 되돌아가기
							nj -=dx[d];
							continue;
						}

					}
				}
			}

			System.out.println("#"+tc+" "+nj);
		}
		sc.close();
	}
	

}
