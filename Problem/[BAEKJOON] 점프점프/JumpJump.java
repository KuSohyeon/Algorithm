//[백준] 점프점프
/*
10 12 30 8
1 1 7 12
7 7 2 1
 */
import java.util.Scanner;

public class JumpJump {
	static int X,Y,H1,H2; // 입력받을 변수선언
	static boolean [] v; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 입력
		X = sc.nextInt();
		Y = sc.nextInt();
		H1 = sc.nextInt();
		H2 = sc.nextInt();
		
		v = new boolean[10001]; //10000미터까지만 체크하면됨 0미터~10000미터 
		
		// 먼저 A부터 뛰어다님
		for(int i=0;i<10001;i++) {
			int kim = H1 + X * i; //A가 뛴 곳
			if(kim>10000) { //만약 10000미터 이상인 경우 break로 빠지게 하기
				break;
			}
			v[kim]=true;
		}
		//그 후 B 뛰어다님
		for(int i=0;i<10001;i++) {
			int park = H2 + Y * i; //B가 뛴 곳
			if(park>10000) {//만약 10000미터 이상인 경우 break로 빠지게 하기
				break;
			}
			if(v[park]) { // 만약 B가 방문한 곳이 이미 A가 방문한 곳이면? 그 지점에서 만나는 것
				System.out.println(park); // 그 좌표 출력
				return; // 밑에꺼 수행 못하도록 return
			}
		}
		// 다 뛰어다녔는데 만나지 못했다면 -1 출력
		System.out.println("-1");
	}

}
