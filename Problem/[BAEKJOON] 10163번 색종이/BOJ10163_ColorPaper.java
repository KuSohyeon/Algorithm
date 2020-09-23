import java.util.Scanner;

public class BOJ10163_ColorPaper {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int [][] map = new int[102][102];
		
		int N = sc.nextInt();
		
		for(int s=1;s<=N;s++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int w = sc.nextInt();
			int h = sc.nextInt();
			
			for(int i=y1;i<y1+h;i++) { // 시작지점에서 높이만큼 더해준거까지 반복
				for(int j=x1;j<x1+w;j++) { // 시작지점에서 너비만큼 더해준거까지 반복
					map[i][j]=s; // 현재 인덱스로 덮어버리기 -> 젤 마지막것이 위에 있으니까 상관  X
				}
			}
		}
	
		StringBuilder sb = new StringBuilder();
		int cnt=0;
		for(int s=1;s<=N;s++) {
			cnt=0; // 배열이 반복될 때 마다 초기화
			for(int i=0;i<=101;i++) {
				for(int j=0;j<=101;j++) {
					if(map[i][j]==s) { // 현 index와 일치하는경우 크기 늘려주기
						cnt++;
					}
				}
			}
			sb.append(cnt+"\n");
			
			
		}
		// 출력
		System.out.println(sb.toString());
	}
}
