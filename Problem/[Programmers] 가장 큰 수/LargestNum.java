//[Programmers] 가장 큰 수

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class LargestNum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int [] numbers = new int[5];
		
		for(int i=0;i<5;i++) {
			numbers[i]=sc.nextInt();
		}
		
		
		System.out.println(solution(numbers));
	}
	
	static String answer="";
	private static String solution(int[] numbers) {
		
		// 숫자를 문자열로 변환
		String [] num = new String[numbers.length];
	
		for(int i=0;i<numbers.length;i++) {
			num[i] = String.valueOf(numbers[i]);
		}
		
		// 정렬
		/*
		 * 내림차순 : return (o2+o1).compareTo(o1+o2); 
		 * 오름차순 : return (o1+o2).compareTo(o1+o2);
		 */
		Arrays.sort(num, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return (o2+o1).compareTo(o1+o2);
			}
		});
		
		
		for(int i=0;i<numbers.length;i++) {
			answer += num[i];
		}
		if(num[0]=="0") { // 0000과 같은 경우 0만 리턴
			answer="0";
		}
		
		return answer;
	}
	
}
