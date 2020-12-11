//[백준] 암호 만들기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MakePassword {
	static int L,C;
	static char [] ch;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		ch = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) {
			ch[i]=st.nextToken().charAt(0);
		}
		
		Arrays.sort(ch);
		
		dfs(0,0,0,"",0);
	}
	private static void dfs(int idx, int moem, int jaem,String pw, int cnt) { // 인덱스, 모음수, 자음수, 비밀번호, 현재까지의 비밀번호 갯수를 파라미터로 들고다니기
		if(cnt==L) {
			if(moem<1 || jaem<2) return; // 최소 모음 하나, 자음 두개
			System.out.println(pw); // 출력
			return;
		}
		for(int i=idx;i<C;i++) { // idx부터 시작함으로써 그 전 순서, 중복 없도로 하기(조합)
			if(ch[i]=='a' || ch[i]=='e' || ch[i]=='i' || ch[i]=='o' || ch[i]=='u') // 모음일 경우에 파라미터 모음 늘려주기
				dfs(i+1, moem+1, jaem, pw+ch[i],cnt+1);
			else // 자음일 경우
				dfs(i+1, moem, jaem+1, pw+ch[i],cnt+1);
		}
	}
}
