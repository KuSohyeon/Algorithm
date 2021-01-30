//[백준] 비밀번호

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13908_Password {
	static int n, m, result;
	static int [] number;
	static boolean [] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		number = new int[m];
		v = new boolean[10];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			int num = Integer.parseInt(st.nextToken());
			number[i] = num;
			v[num] = true;
		}
		
		dfs(0,0);
		
		System.out.println(result);
	}
	private static void dfs(int idx, int cnt) {
		if(idx == n) {
			if(cnt == m) result ++;
			return;
		}
		for(int i=0;i<10;i++) {
			if(v[i]) {
				v[i]=false;
				dfs(idx+1, cnt+1);
				v[i]=true;
				continue;
			}
			dfs(idx+1, cnt);
		}
		
	}
}
