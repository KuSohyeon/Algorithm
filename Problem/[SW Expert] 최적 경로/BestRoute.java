//[SW Expert] 최적 경로
/*
10
5
0 0 100 100 70 40 30 10 10 5 90 70 50 20
6
88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14
7
22 47 72 42 61 93 8 31 72 54 0 64 26 71 93 87 84 83
8
30 20 43 14 58 5 91 51 55 87 40 91 14 55 28 80 75 24 74 63
9
3 9 100 100 16 52 18 19 35 67 42 29 47 68 59 38 68 81 80 37 94 92
10
39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36
10
26 100 72 2 71 100 29 48 74 51 27 0 58 0 35 2 43 47 50 49 44 100 66 96
10
46 25 16 6 48 82 80 21 49 34 60 25 93 90 26 96 12 100 44 69 28 15 57 63
10
94 83 72 42 43 36 59 44 52 57 34 49 65 79 14 20 41 9 0 39 100 94 53 3
10
32 79 0 0 69 58 100 31 67 67 58 66 83 22 44 24 68 3 76 85 63 87 7 86

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BestRoute {
	static Data [] arr;
	static Data [] numbers;
	static boolean [] isSelected;
	static int N,min;
	static Data company,home;
	static int totalCnt=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			min=Integer.MAX_VALUE; //tc마다 최단 경로 초기화
			N = Integer.parseInt(br.readLine());
			
			arr = new Data[N]; //고객의 좌표를 넣을 배열
			numbers = new Data[N]; // 순열의 경우의수를 넣을 배열
			isSelected = new boolean[N]; //순열 방문체크용
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0;i<N+2;i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				if(i>1) {
					arr[i-2] = new Data(x,y); // 3번째 입력부터 고객 넣어주기
					continue;
				}else if(i==0) {
					company = new Data(x,y); //첫번쨰 좌표 회사
				}else {
					home = new Data(x,y); //두번째 좌표 집
				}
			}
			
			perm(0); //순열 + 거리 구해주는 메소드 호출

			System.out.println("#"+tc+" "+min);
		}
	}
	private static void perm(int cnt) { //고객에게 방문하는 경우의 수 만들어주기 N!
		if(cnt==N) { //각 경우의 수가 완성되었을 때 거리구해주기
			int tmp = move(numbers); 
			min = Math.min(min, tmp); //최단 경로 구해서 비교 후 min값 넣어주기
			return; //더 이상 작업을 수행하지 않으니 return
		}
		for(int i=0;i<N;i++) {
			if(isSelected[i]) continue;
			numbers[cnt] = arr[i];
			isSelected[i]=true;
			perm(cnt+1);
			isSelected[i]=false;
		}
		
	}
	private static int move(Data [] arr) {//거리 구하는 메소드
		
		int dist=0;
		
		dist = Math.abs(company.x - arr[0].x) + Math.abs(company.y - arr[0].y);
		for(int i=0;i<arr.length-1;i++) {
			dist += Math.abs(arr[i].x - arr[i+1].x) + Math.abs(arr[i].y - arr[i+1].y);
		}
		dist += Math.abs(home.x - arr[N-1].x) + Math.abs(home.y - arr[N-1].y);
		return dist;
		
	}
	static class Data{
		int x;
		int y;
		
		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		
	}
}
