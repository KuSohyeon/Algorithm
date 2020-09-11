//[SW Expert] 가랏!RC카
/*
 * N초 동안 이동한 거리를 계산하는 프로그램
 * 입력은 가속(1) 또는 감속(2) 또는 현재 속도 유지(0)로 주어짐
 * 가속과 감속의 경우에는 가속도의 값이 추가로 주어짐
 * 현재속도보다 감속할 속도가 더 클 경우 속도는 0이 된다.
 */
/*
10
2
1 2
2 1
3
1 1
0
1 1
5
1 2
1 2
2 1
0
0
#1 3
#2 4
#3 15
 */

import java.util.Scanner;

public class GoRCCar {
	static int result, N, C;
	static Data [] vel;
	static class Data{
		int state;
		int velocity;
		public Data(int state, int velocity) {
			super();
			this.state = state;
			this.velocity = velocity;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			
			N = sc.nextInt();
			
			vel = new Data[N];
			
			for(int i=0;i<N;i++) {
				int state= sc.nextInt();
				if(state==0) { //현재 속도 유지일 경우에는 속도 값 0주기(가속도, 감속도 아님)
					vel[i]=new Data(state,0);
				}else {
					vel[i]=new Data(state,sc.nextInt());
				}
				
			}
			
			int dist =0;
			result=0;
			
			for(Data d:vel) {
				switch(d.state) {
				case 0: //현재 속도 유
					dist += result; //거리 = 속도 * 시간 But 시간이 1초라서 그냥 속도만 더해주기
					break;
				case 1://가속
					result+= d.velocity;
					dist+=result;
					break;
				case 2://감속
					result -= d.velocity; 
					if(result<0) { //만약 현재 속도보다 감속이 더 크면 그냥 0
						result=0;
					}
					dist += result;
					break;
				}
			}
			
			
			System.out.println("#"+tc+" "+dist);
		}
	}
}
