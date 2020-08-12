//[SW Expert] 스도쿠 검증 

import java.util.Scanner;

public class Sudoku {
	static int [][] map;
	static boolean[] isSelected;
	static int result=1;
	static boolean flag=false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			
			result=1;//tc마다 초기화
			flag = false;//tc마다 초기화
			
			map = new int[9][9];//배열 생성
			
			//입력
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					map[i][j]=sc.nextInt();
				}
			}
			
			isSelected = new boolean[10]; //체크용
			
			
			garo();//가로 체크
			if(flag) {//만약 증복된값이 있다면 결과 바로 출력하고 밑에꺼 수행하지 말기
				System.out.println("#"+tc+" "+result);
				continue;
			}
			
			sero();//세로 체크
			if(flag) {
				System.out.println("#"+tc+" "+result);
				continue;
			}

			square();//사각형 체크
			if(flag) {
				System.out.println("#"+tc+" "+result);
				continue;
			}
			

			System.out.println("#"+tc+" "+result);
		}
		sc.close();
	}

	static boolean garo() {
//		가로
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(isSelected[map[i][j]]) {//만약에 똑같은 숫자가 반복되면 빠져나오기
					result=0;
					flag = true;
					break;
				}
				isSelected[map[i][j]]=true; //숫자 방문체크 해주기
			}
			isSelected = new boolean[10];//한줄마다 초기화
			if(flag) {
				break;
			}
		}
		return flag;

	}
	static boolean sero() {
//		세로
		for(int j=0;j<9;j++) {
			for(int i=0;i<9;i++) {
				if(isSelected[map[i][j]]) {
					result=0;
					flag = true;
					break;
				}
				isSelected[map[i][j]]=true;
			}
			isSelected = new boolean[10];//한줄마다 초기화
			if(flag) {
				break;
			}
		}
		return flag;
	}
	static boolean square() {
//		사각형
		for(int i=0;i<9;) {
			for(int j=0;j<9;) {
				for(int m=0;m<3;m++) {
					for(int l=0;l<3;l++){
						if(isSelected[map[i+m][j+l]]) {
							result=0;
							flag = true;
							break;
						}
						isSelected[map[i+m][j+l]]=true;
						
					}
				}
				isSelected = new boolean[10];//한줄마다 초기화
				if(flag) {
					break;
				}
				j = j+3;//3씩 증가(칸 9개니까)
			}
			i=i+3;
		}
		return flag;
	}
	
}
