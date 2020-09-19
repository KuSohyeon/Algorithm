//[백준] 2578번 빙고

import java.util.Scanner;

public class Bingo2 {
	static int [][] bingo;
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		
		bingo = new int[5][5];
		
		// 입력
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				bingo[i][j]=sc.nextInt();
			}
		}
		
		// 사회자가 하나 부를때마다 하나씩 처리
		for(int s=1;s<=25;s++) {
			int now = sc.nextInt();
			
			out : for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(bingo[i][j]==now) {
						bingo[i][j]=0;
						break out;
					}
				}
			}
			
			if(s<10) continue;
			if(check()) {
				System.out.println(s);
				return;
			}
		}
	}

	private static boolean check() {
		int line=0;
		// 가로 체크
		for(int i=0;i<5;i++) {
			int count=0;
			for(int j=0;j<5;j++) {
				if(bingo[i][j]!=0) break;
				count++;
				if(count==5) {
					if(++line==3) {
						return true;
					}
				}
			}
		}
		// 세로 체크
		for(int j=0;j<5;j++) {
			int count=0;
			for(int i=0;i<5;i++) {
				if(bingo[i][j]!=0) break;
				count++;
				if(count==5) {
					if(++line==3) {
						return true;
					}
				}
			}
		}
		// 우하 체크
		for(int rc=0;rc<5;rc++) {
			if(bingo[rc][rc]!=0) break;
			if(rc==4) {
				if(++line==3) {
					return true;
				}
			}
		}
		// 좌하 체크
		for(int i=0;i<5;i++) {
			int j = 4-i;
			if(bingo[i][j]!=0) break;
			if(i==4) {
				if(++line==3) {
					return true;
				}
			}
		}
		return false;
	}
}
