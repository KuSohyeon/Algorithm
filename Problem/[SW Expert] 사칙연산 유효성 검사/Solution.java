//[SW Expert] [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사

import java.util.Scanner;

public class Solution {
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int tc=1;tc<=10;tc++) {
			N = sc.nextInt();//정점의 총 수

			char [] ch = new char[N];
			for(int i=0;i<N;i++) {
				int num = sc.nextInt();//정점 번호
				ch[i] = sc.next().charAt(0);//내용
				String s = sc.nextLine();//쓸모없는거 그냥 날리기

			}

			int n=N;
			int cnt=0;
			while(n!=0) { //정점개수로 트리의 높이 구하기
				n = n/2;
				cnt++;
			}
			cnt--; //트리의 높이는 0부터 시작하니까 1 날려주기
			int start=1;
			for(int i=0;i<cnt;i++) {//레벨이 h인 정점의 번호는 2^h부터 시작
				start *=2;//cnt 높이의 첫 번호 찾아주기
			}
			int result=1;
			for(int i=start;i<N;i++) {//최고 레벨의 첫번쨰 잎 노드부터 시작해서 연산자가 있으면 안됨 있다면 유효성이 없으니 0출력
				if(ch[i]=='+' || ch[i]=='-' || ch[i]=='*' || ch[i]=='/') {
					result=0;
				}
			}

			System.out.println("#"+tc+" "+result);
		}
	}
}
