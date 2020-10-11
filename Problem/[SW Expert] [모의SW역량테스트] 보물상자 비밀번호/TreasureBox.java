//[SWEA] 보물상자 비밀번호

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 * 16진수 10진수로 바꾸는 방법
 * Integer.parseInt("1F7", 16); 
 */
public class TreasureBox {
	static int N,K,result = 0;
	static char [] arr;
	static List<String> list;
	static List<Long> num;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			
			arr = new char[N];
			String s = sc.next();
			for(int i=0;i<N;i++) {
				arr[i] = s.charAt(i);
			}
			
			// 0회전일때의 값들 list에 넣어주기
			list = new ArrayList<String>();
			
			AddListByString();
			
			for(int i=0;i<N/4;i++) { // 제자리로 올때 까지만 돌려주기
				Rotation(); //시계방향으로 돌리기
				AddListByString(); // 리스트에 16진수 더해주기
			}
			
			
			num = new ArrayList<Long>();
			
			// 여기서 16진수를 10진수로 바꿔주기
			for(int i=0;i<list.size();i++) {
				num.add(Long.parseLong(list.get(i), 16));
			}
			
			
			Collections.sort(num,new Comparator<Long>() { // 내림차순으로 정렬
				@Override
				public int compare(Long o1, Long o2) {
					return Long.compare(o2,o1);
				}
			});
			
			// 출력
			System.out.println("#"+tc+" "+num.get(K-1));
		}
	}
	/** 시계방향으로 돌리는 method **/
	private static void Rotation() {
		char tmp = arr[0], now = arr[0];
		for(int i=0;i<N;i++) {
			now = tmp;
			tmp = arr[(i+1)%N];
			arr[(i+1)%N]=now;
		}
		
	}
	/** 16진수 리스트에 더해주는 method **/
	static void AddListByString() {
		for(int i=0;i<4;i++) {
			String tmp = "";
			for(int j=0;j<N/4;j++) { // 한 변에 적힌 글자수는 N/4
				int idx = N/4*i+j; 
				tmp += arr[idx];
			}
			// 같은 수는 넣어주지않는다.
			boolean flag = false;
			for(int l=0;l<list.size();l++) {
				if(list.get(l).equals(tmp)) {
					flag = true; // 같다면 flag를 true로 바꿔주고
					break;
				}
			}
			//flag가 false인 경우(중복 수가 없을 때)만 list에 더해준다.
			if(!flag)
				list.add(tmp);
		}
	}
}
