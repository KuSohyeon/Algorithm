//[백준] 플로이드

import java.util.Scanner;

public class Floyd {
	static int n,m;
	static int [][] matrix;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		matrix = new int[n+1][n+1];

		for(int i=0;i<m;i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			// 같은 경로 입력 여러번 들어오는 경우도 있어서 가장 작은걸로 넣어주기
			if(matrix[from][to]!=0 && matrix[from][to]<weight) continue;
			matrix[from][to]=weight;

		}

		for(int c=1;c<=n;c++) { //경유지
			for(int i=1;i<=n;i++) {//출발지
				if(i==c) continue; // 경유지 = 출발지인 경우 넘겨주기
				for(int j=1;j<=n;j++) {//도착지
					if(i==j || j==c) continue;// 경유지 = 도착지, 도착지 = 출발지인 경우 넘겨주기
					if(matrix[i][j]==0 && matrix[i][c]!=0 && matrix[c][j]!=0) { // 만약 직접경로가 없을 경우
						matrix[i][j]=matrix[i][c]+matrix[c][j]; // 간접 경로 넣어주기
					}
					else if(matrix[i][c]!=0 && matrix[c][j]!=0 && matrix[i][c]+matrix[c][j]<matrix[i][j]) { // 간접경로가 있다면 최소 경로로 넣어주기
						matrix[i][j]=matrix[i][c]+matrix[c][j];

					}
				}
			}
		}
		// 출력
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
