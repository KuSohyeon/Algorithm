//[백준] 어드벤처 게임

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AdventureGame {
	static int n;
	static List<Integer> [] map;
	static char [] who; 
	static boolean [] v;
	static String result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			
			result = "No";
			n = Integer.parseInt(br.readLine());
			if(n==0) break;
			
			map = new List[n+1];
			who = new char[n+1];
			v = new boolean[n+1];
			
			for(int i=1;i<=n;i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			for(int i=1;i<=n;i++) {
				st = new StringTokenizer(br.readLine().trim());
				
				who[i]=st.nextToken().charAt(0);
				int cnt=0;
				while(true) {
					cnt++; // 빈방일 경우 첫번째가 0이니까 그거 막아줄 용도
					int next = Integer.parseInt(st.nextToken());
					if(cnt!=1 && next==0)break;
					map[i].add(next);
				}
			}
			// 1번방 부터 시작
			dfs(1, 0);
			// 출력
			System.out.println(result);
		}
	}
	private static void dfs(int number, int money) {
		// 기저조건
		if(number==n) {
			if(who[n]!='T') {
				result = "Yes"; // 끝 방 도착
				return;
			}else {
				if(money-map[n].get(0)>=0) {
					result = "Yes"; // 끝 방 도착
					return;
				}
			}
			return;
		}
		v[number]=true; // 스택오버플로우 빠지지 않도록 조심하기
		// 트롤일 경우, 레프리콘일 경우, 빈방일 경우 나눠서 처리해주기
		if(who[number]=='T') {
			if(money-map[number].get(0)<0) return; // 트롤있는 방인데 통행료 낼 돈이 없으면  return
			else {
				for(int i=1;i<map[number].size();i++) {
					int nextroom = map[number].get(i);
					if(v[nextroom]) continue;
					dfs(nextroom, money-map[number].get(0));
				}
			}
		}
		else if(who[number]=='L'){
			for(int i=1;i<map[number].size();i++) {
				int nextroom = map[number].get(i);
				if(v[nextroom]) continue;
				dfs(nextroom, money<map[number].get(0)?map[number].get(0):money); // 일정 금액 이하면 그만큼 채워주고 이상이면 안채워줌
			}
		}else {
			for(int i=1;i<map[number].size();i++) {
				int nextroom = map[number].get(i);
				if(v[nextroom]) continue;
				dfs(nextroom, money); // 빈방은 돈 그대로 가져감
			}
		}
		return;
		
	}
}
