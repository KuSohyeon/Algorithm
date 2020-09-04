// [SW Expert] 4366. 정식이의 은행업무
/*
1
1010
212
#1 14
*/

import java.util.Scanner;

public class BankWork {
	static int [] M2,M3;
	static char [] m2,m3;
	static long result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int tc=1;tc<=T;tc++) {
			m2 = sc.next().toCharArray();
			m3 = sc.next().toCharArray();

			M2 = new int[m2.length];
			M3 = new int[m3.length];
			
			//2진법 3진법을 배열로 하나씩 관리할거라서 문자열로 받은 것을 int형 배열로 바꿔주기
			for(int i=0;i<m2.length;i++) {
				M2[i]=m2[i]-'0';
			}
			for(int i=0;i<m3.length;i++) {
				M3[i]=m3[i]-'0';
			}

			boolean flag = false; // 바뀐 값을 찾은지 확인해주는 flag
			long m2Cal=0;
			for(int i=M2.length-1;i>=0;i--) { //배열의 인덱스 진법의 반대로 되어있으니까 거꾸로 반복문돌기
				int wrong = M2[i]; // 한자리씩 받아서 수행 M2[0] = 2의 3승자리  (tc기준)
				switch(wrong) {
				case 1: //그 자리가 1이면
					M2[i] = 0; // 0으로 바꿔주고
					m2Cal = cal(M2); //10진법으로 계산해주고
					if(compare(m2Cal)) { //3진법 다 바꿔서 10진법 값이 같은지 확인
						result = m2Cal; //같으면 result에 10진법 값 넣어주고
						flag=true; // 값 찾은거 표시해주기
					}
					break;
				case 0://만약 0인 경우에는
					M2[i] = 1; //1로 바꿔주고
					m2Cal = cal(M2); // 10진법으로 계산하고
					if(compare(m2Cal)) { //3진수 한자리씩 다 바꿔가면서 비교해주기
						result = m2Cal;
						flag=true;
					}
					break;
				}
				M2[i]=wrong; // 다시 원상복구 시켜주기 
				if(flag) { //만약 어디가 바꼇는지 찾은거면
					System.out.println("#"+tc+" "+result); // 원래 값(10진수) 출력해주기
					break; //break 후 다음 tc로 돌아가기
				}
			}
		}


	}
	//2진수와 3진수를 비교해주는 메소드 같으면 true 다르면 false를 리턴
	private static boolean compare(long m2Cal) {
		long m3Cal=0; //3진수를 10진수로 바꿔준 값을 저장할 변수
		for(int j=M3.length-1;j>=0;j--) { //똑같이 M3[N-1]의 부터 바꾸면서 비교
			int wrong3 = M3[j]; 
			switch(wrong3) {
			case 0:
				M3[j] = 1;
				m3Cal = cal(M3);
				if(m2Cal == m3Cal) { //만약 M2의 10진법으로 바꾼 수와 M3의 10진법으로 바꾼 수가 같다면  true를 리턴
					return true;
				}
				M3[j] = 2;
				m3Cal = cal(M3);
				if(m2Cal == m3Cal) { 
					return true;
				}
				break;
			case 1:
				M3[j] = 0;
				m3Cal = cal(M3);
				if(m2Cal == m3Cal) { 
					return true;
				}
				M3[j] = 2;
				m3Cal = cal(M3);
				if(m2Cal == m3Cal) { 
					return true;
				}
				break;
			case 2:
				M3[j] = 1;
				m3Cal = cal(M3);
				if(m2Cal == m3Cal) { 
					return true;
				}
				M3[j] = 0;
				m3Cal = cal(M3);
				if(m2Cal == m3Cal) { 
					return true;
				}
				break;
			}
			M3[j]=wrong3;
		}
		return false;
	}
	private static long cal(int[] M) { //10 진수로 바꿔주는 메소드
		long cal=0;
		int base=0;

		if(M == M2) {
			base = 2;
		}else {
			base = 3;
		}

		for(int i=0;i<M.length;i++) {
			cal += M[i] * Math.pow(base, M.length-1-i);
		}

		return cal;
	}
}
