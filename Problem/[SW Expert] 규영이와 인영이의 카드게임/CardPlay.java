//[SW Expert] 6808. 규영이와 인영이의 카드게임
/*
4
1 3 5 7 9 11 13 15 17
18 16 14 12 10 8 6 4 2
13 17 9 5 18 7 11 1 15
1 6 7 9 12 13 15 17 18

#1 112097 250783
#2 250783 112097
#3 336560 26320
#4 346656 16224
 */
import java.util.Scanner;

public class CardPlay {
	static int [] gyu,in;
	static boolean [] check,isSelected;
	static int [] inyoung;
	static int win,lose,totalCnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			
			//tc마다 초기화
			win=0;
			lose=0;
			
			gyu = new int[9]; //규영이의 카드 배열
			inyoung = new int[9]; //인영이의 카드 배열(순서 O)
			in = new int[9]; //인영이의 카드 배열(순서 X)
			check = new boolean[19]; //인영이 카드 배열만들때 사용
			isSelected = new boolean[9]; //순열 만들때 사용
			
			 //규영이 카드 입력 받기
			for(int i=0;i<9;i++) {
				gyu[i]=sc.nextInt();
				check[gyu[i]]=true;
			}
			
			//인영이 카드 배열 만들어주기
			int cnt=0;
			for(int i=1;i<=18;i++) {
				if(!check[i]) {
					in[cnt++]=i;
				}
			}
			
			perm(0);// 순열 생성
			
			System.out.println("#"+tc+" "+win+" "+lose);
		}
		
	}
	private static void perm(int cnt) {
		if(cnt==9) {
			compare(); //인영이 카드 순서 정해졌으면 게임 start
			return;
		}
		for(int i=0;i<9;i++) {
			if(isSelected[i]) continue;
			isSelected[i]=true;
			inyoung[cnt]=in[i];
			perm(cnt+1);
			isSelected[i]=false;
			
		}
		
	}
	private static void compare() {
		int gy=0, iy=0;
		for(int i=0;i<9;i++) {
			if(gyu[i]>inyoung[i]) { //규영이가 이기면 규영이 점수에 더해주기
				gy += gyu[i]+inyoung[i];
			}else {//규영이가 지면 인영이 점수에 더해주기
				iy += gyu[i]+inyoung[i];
			}
		}
		//게임 종료 후 점수 비교
		if(gy>iy) {//규영이가 이겼을 경우 
			win++; 
		}else if(gy<iy) { //규영이가 졌을 경우
			lose++;
		}
		
	}
}
