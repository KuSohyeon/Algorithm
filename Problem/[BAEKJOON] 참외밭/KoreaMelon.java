import java.util.Scanner;

public class KoreaMelon {
	static int [][] input;
	static int N;
	static int W,H;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); //1제곱미터당 자라는 참외의 개수
		
		input = new int[6][2];
		
		for(int i=0;i<6;i++) {
			input[i][0]=sc.nextInt(); //방향
			input[i][1]=sc.nextInt(); //거리
			
			//일단 기본이 되는 직사각형의 가로 세로 구하기
			if(input[i][0]==1 || input[i][0] ==2) {
				W = Math.max(W, input[i][1]);
			}
			if(input[i][0]==3 || input[i][0] ==4) {
				H = Math.max(H, input[i][1]);
			}
		}
		
//		System.out.println(W+" "+H);
		
		int w=0,h=0;
		int pre=0,next=0;
		

		for(int i=0;i<6;i++) {
			int nowLen = input[i][1];
//			기본이 되는 가로는 짧은 세로와 기본이 되는 세로 사이에 껴 있음 -> 짧은 세로를 구하면 짤려져 있는 높이를 구할 수 있음
			if(nowLen==W) {
				if(i==0) { //배열의 인덱스 예외 상황 막아주기 직사각형 -> 인덱스가 원처럼 반복되는 형태
					pre = input[5][1];
					next = input[1][1];
				}else if(i==5) {
					pre = input[4][1];
					next = input[0][1];
				}else {
					pre = input[i-1][1];
					next = input[i+1][1];
				}
				if(pre>next) { // 둘중에 하나는 기본이 되는 가로 하나는 짧은 가로
					h = H-next;
				}else {
					h = H-pre;
				}
			}
//			세로도 마찬가지
			if(nowLen==H) {
				if(i==0) {
					pre = input[5][1];
					next = input[1][1];
				}else if(i==5) {
					pre = input[4][1];
					next = input[0][1];
				}else {
					pre = input[i-1][1];
					next = input[i+1][1];
				}
				if(pre>next) {
					w = W-next;
				}else {
					w = W-pre;
				}
			}
		}
		
//		System.out.println(w+" "+h);
//		출력
		int result = (W*H-w*h)*N;
		System.out.println(result);
	}
}
