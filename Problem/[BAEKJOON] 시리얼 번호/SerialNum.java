//[백준] 시리얼 번호

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SerialNum {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String [] arr = new String[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=br.readLine();
		}
		
		// Bubble Sort 이용
		int last = N-1;
		for(int n=0;n<N;n++) {
			for(int i=0;i<last;i++) {
				if(arr[i].length() > arr[i+1].length()) { // 길이가 짧은 것이 앞
					String tmp = arr[i];
					arr[i]=arr[i+1];
					arr[i+1]=tmp;
					continue;
				}
				if(arr[i].length()==arr[i+1].length()) { // 길이가 같다면
					char [] ch1 = arr[i].toCharArray();
					char [] ch2 = arr[i+1].toCharArray();
					int num1=0, num2=0;
					for(int c=0;c<ch1.length;c++) {
						// 모든 자리의 수의 합 비교
						if(ch1[c]-'0'>=0 && ch1[c]-'0'<10) {
							num1 += (ch1[c]-'0');
						}
						if(ch2[c]-'0'>=0 && ch2[c]-'0'<10) {
							num2 += (ch2[c]-'0');
						}
					}
					if(num1>num2) { //작은 합을 가지는 것이 먼저온다.
						String tmp = arr[i];
						arr[i]=arr[i+1];
						arr[i+1]=tmp;
					}else if(num1==num2) {
						String [] tmp = new String[2];
						tmp[0]=arr[i];
						tmp[1]=arr[i+1];
						Arrays.sort(tmp);
						arr[i]=tmp[0];
						arr[i+1]=tmp[1];
					}
				}
			}
			last--; // 맨 뒤에는 가장 큰 문자열이 들어가기 때문에 더 이상 비교해줄 필요 X
		}
		
		// 출력
		for(String s : arr) {
			System.out.println(s);
		}
	}
}
