//[백준] 계란으로 계란치기

import java.util.Scanner;

public class EggToEgg {
	static int N;
	static int [] power, weight;
	static boolean [] v;
	static int result,tmp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		power = new int[N];
		weight = new int[N];
		v = new boolean[N];
		
		for(int i=0;i<N;i++) {
			power[i]=sc.nextInt();
			weight[i]=sc.nextInt();
		}
		tmp = 0;
		dfs(0);
		
		System.out.println(result);
	}
	private static void dfs(int now) {
		if(now==N) { // 더이상 계란이 없으면 return
			result = Math.max(result, tmp);
			return;
		}
		if(v[now]) { //이미 깨진거라면 다음껄(오른쪽)로 보내주기
			dfs(now+1);
			return;
		}
		for(int i=0;i<N;i++) {
			if(i==now || v[i]) continue;
			
			int p1 = power[now];
			int back1 = p1;
			int w1 = weight[now];
			
			
			int p2 = power[i];
			int back2 = p2;
			int w2 = weight[i];
			
			p1 -= w2; // 내구도 -= 상대방의 무게
			p2 -= w1;
			
			// 업데이트
			power[now]=p1;
			power[i]=p2;
			
			if(p1<=0) { // 깨지면 깬거 true처리 + 갯수 늘리기
				v[now]=true;
				tmp++;
			}
			if(p2<=0) {// 깨지면 깬거 true처리 + 갯수 늘리기
				v[i]=true;
				tmp++;
			}
			
			
			
			dfs(now+1);
			
			//백트래킹
			if(p1<=0) {
				v[now]=false;
				tmp--;
			}
			if(p2<=0) {
				v[i]=false;
				tmp--;
			}
			
			power[now]=back1;
			power[i]=back2;
		}
		//now==N일때 못잡아내는거 처리
		if(now==N-1) { // 제일 오른쪽이면서 다 깨서 더이상 깰게 없을 경우
			int cnt=0;
			for(int c=0;c<N;c++) {
				if(v[c]) { 
					cnt++;
				}
			}
			if(cnt==N-1) {
				result = Math.max(result, tmp);
				return;
			}
		}
		
	}
}
