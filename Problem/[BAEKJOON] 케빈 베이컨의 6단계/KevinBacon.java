//[백준] 케빈 베이컨의 6단계 법칙
/*
5 5
1 3
1 4
4 5
4 3
3 2
출력 : 3
 */
import java.util.Scanner;

public class KevinBacon {
	static int [][] map;
	static int [][] friend;
	static int N,M,result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N+1][N+1];
		friend = new int[N+1][N+1];

		for(int i=0;i<M;i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			map[from][to]=map[to][from]=1;
		}

		for(int c=1;c<=N;c++) {// 경유지
			for(int i=1;i<=N;i++) {//출발지
				if(i==c) continue;
				for(int j=1;j<=N;j++) {//도착지
					if(i==j || c==j) continue;
					if(map[i][j]==0 && map[i][c]!=0 && map[c][j]!=0) { // 직접 경로가 없을 경우 거쳐서 가는 경우로 업데이트
						map[i][j]=map[i][c]+map[c][j];
					}
					if(map[i][j]!=0 && map[i][c]!=0 && map[c][j]!=0 && map[i][j]>map[i][c]+map[c][j]) { // 값이 있는 경우 가중치 작은걸로 넣어주기
						map[i][j]=map[i][c]+map[c][j];
					}
				}
			}
		}
		
		int who=0;
		result = Integer.MAX_VALUE;
		// 케빈 베이컨 수 제일 작은 애로 출력
		for(int i=1;i<=N;i++) {
			int tmp=0;
			for(int j=1;j<=N;j++) {
				tmp += map[i][j];
			}
			if(result>tmp) {
				result = tmp;
				who=i;
			}
		}
		
		
		System.out.println(who);
	}
}
