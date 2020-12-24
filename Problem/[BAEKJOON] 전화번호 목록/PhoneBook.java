//[백준] 전화번호 목록
/*
 * 정렬 전
 * 134
 * 15
 * 12
 * 134
 * 1234
 * 
 * 정렬 후
 * 12
 * 1234
 * 13
 * 134
 * 15
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PhoneBook {
	static int max = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int n = Integer.parseInt(br.readLine());
			
			String [] phone = new String[n];
			max = 0;
			
			for(int i=0;i<n;i++) {
				phone[i]=br.readLine();
				max = Integer.compare(max, phone[i].length());
			}
			
			System.out.println(solution(phone)?"YES":"NO");
		}
		
	}
	public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book); // 길이순
        
	    for(int i=0;i<phone_book.length-1;i++) {
	    	int len = phone_book[i].length(); // 현재 길이
	    	String now = phone_book[i]; // 현재 문자열
	    	for(int j=i+1;j<phone_book.length;j++) { // 현재 위치 +1 인덱스부터 검사
	    		if(phone_book[j].length()<len) break; // 만약 내 길이보다 짧은 경우 더이상 비교할 필요가 없음. 접두도 달라지니까!
	    		if(phone_book[j].substring(0, len).equals(now)) { // 만약 접두어가 되는 경우에는 false 리턴
	    			return false;
	    		}
	    	}
	    }
        return true;
    }
}
