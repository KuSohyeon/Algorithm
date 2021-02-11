//[백준] 행운의 문자열

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1342_행운의문자열 {
	static int len;
	static int [] alphabet;
	static char [] arr;
	static boolean [] v;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = br.readLine().toCharArray();
		len = arr.length;
		alphabet = new int[26];
		v = new boolean[len];
		
		for(int i=0;i<len;i++) {
			alphabet[arr[i]-'a']++;
		}
		
		// 브루트포스
		dfs(0, "");
		
		// 중복 경우 제거
		for(int i=0;i<26;i++) {
			if(alphabet[i]>1) {
				result /= factorial(alphabet[i]);
			}
		}
		System.out.println(result);
		
	}
	// 팩토리얼 method
	private static int factorial(int n) {
		int num = 1;
		for(int i=1;i<=n;i++) {
			num *= i;
		}
		return num;
	}
	// 순열 method
	private static void dfs(int cnt, String word) {
		if(cnt == len) {
			if(isLucky(word)) result++;
			return;
		}
		for(int i=0;i<len;i++) {
			if(v[i]) continue;
			v[i]=true;
			dfs(cnt+1, word+arr[i]);
			v[i]=false;
		}
	}
	// 행운의 숫자인지 판단하는 method
	private static boolean isLucky(String word) {
		for(int i=1;i<word.length();i++) {
			if(word.charAt(i-1) == word.charAt(i)) return false;
		}
		return true;
	}
}
