import java.util.Arrays;

public class 전화번호목록 {
	public static boolean solution(String[] phone_book) {
		
        Arrays.sort(phone_book); // 길이순
        
	    for(int i=0;i<phone_book.length-1;i++) {
	    	int len = phone_book[i].length(); // 현재 길이
	    	String now = phone_book[i]; // 현재 문자열
	    	for(int j=i+1;j<phone_book.length;j++) { // 현재 위치 +1 인덱스부터 검사
	    		if(phone_book[j].length()<len) continue; // 만약 내 길이보다 짧은 경우 검사 X -> 런타임 에러남 ?!!! 길이순으로 sort를 했는데 왜 런타임 에럴까..
	    		if(phone_book[j].substring(0, len).equals(now)) { // 만약 접두어가 되는 경우에는 false 리턴
	    			return false;
	    		}
	    	}
	    }
        return true;
    }
	public static void main(String[] args) {
		/** tc */
//		String [] arr = {"119", "97674223", "1195524421"};
//		String [] arr = {"123", "456", "789"};
		String [] arr = {"12345", "123", "12"};
		
		System.out.println(solution(arr));
		
	}
}
