//[백준] 방배정
/*
 * 한 방에는 같은 학년 같은 성별만 배정 가능
 * 한 방의 인원 = 최대 K명
 * 한 방에 한명 배정 가능
 */
import java.util.Scanner;

public class SetRoom {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int [][] student = new int[7][2]; //index 1부터 사용할거라서 +1
		
		for(int i=0;i<N;i++) {
			int gender = sc.nextInt();
			int grade = sc.nextInt();
			student[grade][gender]++; // 해당 학년 성별에 인원 넣어주기
		}
		
		int room = 0;
		// 방 배정 시작 -> 같은학년 같은 성별만 한 방에 배정가능
		for(int i=1;i<7;i++) {
			for(int j=0;j<2;j++) {
				room+=student[i][j]%K==0?student[i][j]/K:student[i][j]/K+1; // 만약 나누어 떨어지면 나눈값 넣고 아니면 방 하나 더 써야함
			}
		}
		
		System.out.println(room);
	}
}
