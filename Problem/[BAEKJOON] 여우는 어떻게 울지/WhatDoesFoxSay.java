//[백준] 여우는 어떻게 울지?

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WhatDoesFoxSay {
	static String result,input;
	static String [] animals;
	static boolean [] check;
	static List<String> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<T;tc++) {
			
			
			String input = br.readLine();
			
			animals = input.split(" "); // 문자열을 구분자로 분할하여 넣어주기
			check = new boolean[animals.length];
			list = new ArrayList<String>();
			
			boolean flag = true;
			
			while(flag) {
				
				st = new StringTokenizer(br.readLine().trim());
				
				String s1 = st.nextToken();
				if("what".equals(s1)) break; // What does fox say?가 종료조건
				
				String s2 = st.nextToken();
				String s3 = st.nextToken(); // 여기가 동물 울음소리 (앞에꺼 다 날려도 상관 X)
				
				list.add(s3); //동물 울음소리 보관
			}
			
			for(int i=0;i<list.size();i++) {
				String now = list.get(i);
				for(int j=0;j<animals.length;j++) { //해당 동물 울음소리를 가진 것 true 처리
					if(now.equals(animals[j])) check[j]=true;
				}
			}
			
			result = ""; //tc마다 초기화
			for(int i=0;i<check.length;i++) {
				if(!check[i]) { // 여우 아닌 것 제외해주기
					result += animals[i]+" ";
				}
			}
			 
			// 출력
			System.out.println(result);
		}
	}
}
