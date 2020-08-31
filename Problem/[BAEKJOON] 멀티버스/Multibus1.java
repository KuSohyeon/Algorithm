// [백준] 멀티버스 1
/*
2 3
1 3 2
12 50 31
# 1
2 3
1 3 2
12 50 10
# 0
5 3
20 10 30
10 20 60
80 25 79
30 50 80
80 25 81
# 2
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Multibus1 {
	static int M,N; //우주의 개수, 각 행성수
	static int [][] group; //각 우주의 행성을 받아줄 2차원 배열
	static int cnt; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		// 입력
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		group = new int[M][N]; // M개의 우주 N개의 행성
		
		for(int i=0;i<M;i++) {//각 우주마다 반복하면서
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				group[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각 우주 각 행성마다 크기 비교하기
		for(int a=0;a<M-1;a++) {//1번 우주부터 ~ M번까지 비교
			for(int b=a+1;b<M;b++) {
				boolean flag = false; // 행성이 비슷한지 안비슷한지 판별할 때 이용하는 flag
				for(int i=0;i<N-1;i++) {//1번부터 ~N번까지 비교
					for(int j=i+1;j<N;j++) {
						//각 반 점수 비교해주기
						if(group[a][i]>group[a][j]) { // Ai>Aj일 경우
							if(group[b][i]<=group[b][j]) {// A와 경우가 맞지 않으면 break;
								flag=true;
								break;
							}
						}
						else if(group[a][i]==group[a][j]) { // Ai=Aj일 경우
							if(group[b][i]!=group[b][j]) {// A와 경우가 맞지 않으면 break;
								flag=true;
								break;
							}
						}
						else if(group[a][i]<group[a][j]) { // Ai<Aj일 경우
							if(group[b][i]>=group[b][j]) {// A와 경우가 맞지 않으면 break;
								flag=true;
								break;
							}
						}
					}
					if(flag) {//다음 우주으로 넘어가기
						break;
					}
				}
				if(flag) {//만약 다른 경우가 있다면 cnt++하지말기
					continue;
				}

				cnt++;//저거 다 통과하면 우주 같은거니까 cnt++
			}
		}
		//비슷한 우주 쌍 개수 출력
		System.out.println(cnt);
		
	}
}
