//[백준] 줄세우기
/*
5
0 1 1 3 2
# 4 2 5 3 1
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_2605_Line {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int [] input = new int[N];
		List<Integer> list = new ArrayList<Integer>();
		
		// 입력
		for(int i=0;i<N;i++) {
			input[i]=sc.nextInt();
		}
		
		for(int i=0;i<N;i++) { //첫번째 사람부터 줄 세우기
			list.add(list.size()-input[i], i+1); //list의 add라는 api 사용해서 원하는 인덱스에 값 바로 넣어주기
		}
		
		// 출력
		for(int s : list) {
			System.out.print(s+" ");
		}
	}
}
