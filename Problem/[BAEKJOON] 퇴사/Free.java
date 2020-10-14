//[백준] 퇴사

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Free {
	static int N,result;
	static boolean [] v;
	static Data [] arr;
	static class Data{
		int start;
		int time;
		int money;
		public Data() {}
		public Data(int start, int time, int money) {
			super();
			this.start = start;
			this.time = time;
			this.money = money;
		}
		@Override
		public String toString() {
			return "Data [start=" + start + ", time=" + time + ", money=" + money + "]";
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new Data[N+1];
		v = new boolean[N+1];
		
		for(int i=0;i<=N;i++) {
			arr[i]=new Data();
		}
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int time = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());
			
			arr[i] = new Data(i,time,money);
		}
		
		// 부분집합 만들어서 가능한 경우의 수 다 해보기
		subset(0); // dp지만 완탐으로 풀겠다....
		
		System.out.println(result);
	}
	private static void subset(int cnt) {
		if(cnt==N+1) {
			calMoney(); // 부분집합 만들어지면 계산하러 가기
			return;
		}
		v[cnt]=true;
		subset(cnt+1);
		v[cnt]=false;
		subset(cnt+1);
	}
	private static void calMoney() {
		List<Data> list = new ArrayList<Data>();
		for(int i=1;i<=N;i++) {
			if(v[i]) {
				list.add(arr[i]);
			}
		}
		if(!check(list)) { // 계산하기 전에 이렇게 일 다 할 수 있는지 확인해주기
			return;
		}
		
		int tmp =0;
		for(Data d:list) {
			tmp += d.money;
		}
		result = Math.max(result, tmp);
		
	}
	private static boolean check(List<Data> list) {
		for(int i=0;i<list.size();i++) {
			Data now = list.get(i);
			if(i<list.size()-1) {
				Data next = list.get(i+1);
				if(now.start+now.time>next.start) return false; // 만약 일 중복되서 못하는경우 false 리턴
			}
			if(now.start+now.time>N+1) return false; // 일 기간이 퇴사일보다 크면 못하니까 false 리턴
		}
		return true;
	}
}
