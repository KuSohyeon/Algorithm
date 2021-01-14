//[SWEA] [모의 SW 역량 테스트] 차량 정비소

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CarRepairShop {
	static int N,M,K,A,B,result;
	static List<Integer> readyList, fixList;
	static List<Data> list;
	static int [] receipt, repair;
	static int [][] people, rec, rep;
	static class Data implements Comparable<Data>{
		int num;
		int time;
		public Data(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Data [num=" + num + ", time=" + time + "]";
		}
		@Override
		public int compareTo(Data o) {
			return Integer.compare(this.time, o.time);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			result = 0;//tc마다 초기화
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 접수 창구 개수
			M = Integer.parseInt(st.nextToken()); // 정비 창구 개수
			K = Integer.parseInt(st.nextToken()); // 사람 수
			A = Integer.parseInt(st.nextToken()); // 지갑 잃어버린 사람이 간 접수 창구 번호
			B = Integer.parseInt(st.nextToken()); // 지갑 잃어버린 사람이 간 정비 창구 번호
			
			list = new ArrayList<>(); // 고객 관리 리스트
			readyList = new ArrayList<>(); // 접수 창구 기다리는 고객 관리 리스트
			fixList = new ArrayList<>(); // 정비 창구 기다리는 고객 관리 리스트
			people = new int[K+1][2]; // 사람들이 갔던 창구 저장할 2차원 배열
			receipt = new int[N+1]; // 원본(접수 창구 걸리는 시간 저장)
			rec = new int[N+1][2]; // 현재 접수 창구 저장하는 상태 저장할 2차원 배열 (남은 시간, 현재 접수하는 사람 번호)
			repair = new int[M+1]; // 원본(정비 창구 걸리는 시간 저장)
			rep = new int[M+1][2]; // 현재 정비 창구 저장하는 상태 저장할 2차원 배열
			
			// 접수 창구 시간 저장
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=N;i++) {
				receipt[i] = Integer.parseInt(st.nextToken());
			}
			// 정비 창구 시간 저장
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<=M;i++) {
				repair[i] = Integer.parseInt(st.nextToken());
			}
			// 고객 k의 도착 시간
			st = new StringTokenizer(br.readLine());
			for(int i = 1;i<=K;i++) {
				int time = Integer.parseInt(st.nextToken());
				
				list.add(new Data(i,time));
			}
			
			Collections.sort(list); //혹시 몰라서 시간순으로 정렬
			
			int time = 0, customer = 0; // 시간, 고객 수 관리(모든 고객이 서비스 받을 경우 while문 break)
			while(true) {
				// 먼저 온 순서대로 접수 리스트에 넣어주기 (없으면 넘어감)
				boolean flag = true;
				while(flag) {
					if(list.isEmpty()) break; // nullPointer 방지
					Data person = list.get(0);
					if(time == person.time) {
						readyList.add(person.num);
						list.remove(0);
					}else {
						flag = false;
					}
				}
				
				// 접수 창구 리스트 관리
				while(true) {
					if(readyList.isEmpty()) break; // nullPointer 방지
					int person = readyList.get(0); 
					int num = check(rec);
					if(num==0) break;
					readyList.remove(0);
					rec[num][0]=receipt[num];
					rec[num][1]=person;
					people[person][0]=num; // 첫번째 이용한 창구번호 기록
					
				}
				
				// 접수 창구 시간 보내주기
				recTimeIsGo(rec);
				
				// 정비 창구 리스트 관리
				while(true) {
					if(fixList.isEmpty()) break; // nullPointer 방지
					int person = fixList.get(0);
					int num = check(rep);
					if(num==0) break;
					fixList.remove(0);
					customer++;
					rep[num][0]=repair[num];
					rep[num][1]=person;
					people[person][1]=num; // 첫번째 이용한 창구번호 기록
				}
				
				// 정비 창구 시간 보내주기
				repTimeIsGo(rep);
				
				time ++; // 시간은 간다.
				if(customer == K) break; // 모든 고객이 이용했을 경우 while문 종료
				
			}
			
			// 모든 고객 서비스 완료 후 지갑 잃어버린 사람이랑 같은 창구 이용한 고객 찾기
			for(int i=1;i<=K;i++) {
				if(people[i][0]==A && people[i][1]==B) {
					result += i;
				}
			}
			
			// 출력
			System.out.println("#"+tc+" "+(result==0?-1:result));
		}
		
		
	}
	// 정비 창구 시간 관리하는 method
	private static void repTimeIsGo(int[][] arr) {
		for(int i=1;i<arr.length;i++) {
			if(arr[i][1]==0) continue;
			if(--arr[i][0]==0) {
				arr[i][1]=0;
			}
		}
	}
	// 접수 창구 시간 관리하는 method
	private static void recTimeIsGo(int[][] arr) {
		for(int i=1;i<arr.length;i++) {
			if(arr[i][1]==0) continue;
			if(--arr[i][0]==0) {
				fixList.add(arr[i][1]);
				arr[i][1]=0;
			}
		}
	}
	// 이 창구가 이용가능한지 확인하는 method
	private static int check(int[][] arr) {
		for(int i=1;i<arr.length;i++) {
			if(arr[i][0]==0) {
				return i;
			}
		}
		return 0;
	}
}
