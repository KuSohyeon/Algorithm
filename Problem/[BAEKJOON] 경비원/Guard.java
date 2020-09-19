//[백준] 2564 경비원
// (1,1)에서 시계방향으로 각 상점까지의 거리와 동근이까지의 거리를 구해서
// 동근 - 상점 거리 하면 동근이~상점 시계 방향거리를 구할 수 있다.
// 반시계 방향 = 둘레 - 시계방향 거리
// 두 값 비교해서 최솟값을 결과에 더해주기
import java.util.Scanner;

public class Guard {

	static int W,H,N;
	static int dong;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		W = sc.nextInt(); //가로
		H = sc.nextInt(); //세로
		N = sc.nextInt();

		int [] arr = new int[N];

		for(int i=0;i<N;i++) {
			int dir = sc.nextInt();
			int loc = sc.nextInt();
			arr[i] = getDistanceFromZero(dir,loc); //시계방향 거리 구해주기
		}
		//동근이도 시작지점에서부터 시계방향으로 거리 구해주기
		dong = getDistanceFromZero(sc.nextInt(), sc.nextInt());

		int result=0;
		
		// 동근~ 상점의 거리 구해주기
		for(int i=0;i<N;i++) {
			int right = Math.abs(dong-arr[i]);
			int left = (W+H)*2 - right;
			result += Math.min(right, left);
		}
		System.out.println(result);


	}
	private static int getDistanceFromZero(int dir, int loc) {
		if(dir==1) {// 북
			return loc;
		}
		else if(dir==2) {// 남
			return W+H+W-loc;
		}
		else if(dir==3) {
			return W+H+W+H-loc;
		}
		else {// 동
			return W+loc;
		}
	}

}


