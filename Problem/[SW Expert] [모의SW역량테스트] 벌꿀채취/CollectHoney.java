//[SW Expert] [모의SW역량테스트] 벌꿀채취

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.StringTokenizer;

public class CollectHoney {
	static int N,M,C,max,result;
	static int [][] honey;
	static int [] choice;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			result = 0; //tc마다 초기화
			
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			honey = new int[N][N];
			choice = new int[2];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					honey[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			comb(0,0); // 두 일꾼 벌통 선택하러 가기
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void comb(int start, int cnt) {
		if(cnt==2) {
			if(Math.abs(choice[1]-choice[0])<M) { // 1차원 배열로 벌통 관리 ->두 일꾼의 벌통은 겹치면 안됨
				return;
			}
			cal(); // 일단 수익 계산하러 가기
			return;
		}
		for(int i=start;i<N*N;i++) {
			choice[cnt]=i; // 벌통의 시작부분 선택
			comb(start+1,cnt+1);
		}
		
	}
	// 수익을 계산하는 method
	private static void cal() { 
		// 일차원 배열 -> 꿀 채취하려면 2차원 인덱스 구해야함
		int i1 = choice[0]/N; 
		int j1 =  choice[0]%N; 
		
		int i2 = choice[1]/N; 
		int j2 =  choice[1]%N; 
		
		// 배열의 인덱스밖으로 빠지는 경우에는 return해서 계산 X
		if(j1 + M-1 >= N || j2 + M-1 >=N) return;
		
		// 일꾼 1
		int tmp = 0;
		int [] arr = new int[M];
		boolean [] brr = new boolean [M];
		max=0; // 매 순간마다 초기화 해줘야함
		
		
		if(check(i1,j1)) { // 선택할 벌통에 벌꿀의 총 합이 C보다 작은 경우
			for(int i=0;i<M;i++) {
				tmp += Math.pow(honey[i1][j1+i], 2); // 수익에 다 더해주기
			}
		}else {// 선택할 벌통에 벌꿀의 총 합이 C보다 큰 경우
			for(int i=0;i<M;i++) { // 벌꿀 값을 가진 arr 세팅
				arr[i] = honey[i1][j1+i];
			}
			subset(arr,0,0,brr); // 부분집합으로 가장 큰 수익 구하기
			tmp += max; // 수익에 더해주기
		}
		
		// 일꾼 2
		max=0; // 일꾼1 꺼 남아있으니까 초기화 작업 진행
		Arrays.fill(brr, false); // 일꾼1 꺼 남아있으니까 초기화 작업 진행
		
		if(check(i2,j2)) {// 선택할 벌통에 벌꿀의 총 합이 C보다 작은 경우
			for(int i=0;i<M;i++) {
				tmp += Math.pow(honey[i2][j2+i], 2);// 수익에 다 더해주기
			}
		}else {// 선택할 벌통에 벌꿀의 총 합이 C보다 큰 경우
			for(int i=0;i<M;i++) {
				arr[i] = honey[i2][j2+i]; // 벌꿀 값을 가진 arr 세팅
			}
			subset(arr,0,0,brr);// 부분집합으로 가장 큰 수익 구하기
			tmp += max;// 수익에 더해주기
		}
		
		// 최대값으로 result 업데이트
		result = Math.max(result, tmp);
		
	}
	// 벌꿀의 총 합이 C보다 큰경우 부분집합을 이용하여 일꾼 1/2의 최대 수익을 계산
	private static void subset(int[] arr, int cnt, int sum, boolean [] brr) {
		if(cnt==M) {
			if(sum<=C) {
				int tmp = 0;
				for(int i=0;i<M;i++) {
					if(brr[i]) {
						tmp += Math.pow(arr[i], 2);
					}
				}
				max = Math.max(max, tmp);
			}
			return;
		}
		brr[cnt]=true;
		subset(arr,cnt+1,sum+arr[cnt],brr);
		brr[cnt]=false;
		subset(arr,cnt+1,sum,brr);
		
	}
	// 벌꿀의 총 합이 C 이하이면 true 리턴, 초과면 false 리턴
	private static boolean check(int r, int c) {
		int cal = 0;
		for(int i=0;i<M;i++) {
			cal += honey[r][c+i];
		}
		if(cal>C) {
			return false;
		}
		return true;
	}
}
