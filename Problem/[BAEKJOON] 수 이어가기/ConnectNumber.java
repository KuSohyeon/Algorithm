//[백준] 2635번 수 이어가기
/*
100
출력 :
8
100 62 38 24 14 10 4 6
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectNumber {
	static List<Integer> list,result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		list = new ArrayList<Integer>();
		result = new ArrayList<Integer>();
		
		list.add(N);
		
		//보통 한 60~65%정도가 최대 인 것 같음
		int high = (int)(N *0.65);
		int low = (int)(N *0.6);
		
		if(N==1) {//1인 경우 0밖에 안되니까 1도 포함시켜주기
			high=1;
		}
		//최대값
		int max=0;
		
		for(int i=low;i<=high;i++) {
			list.add(i); //리스트에 첫번째 수 넣어주기
			while(true) {
				int idx = list.size(); //현재 인덱스 가져오기
				int tmp = list.get(idx-2)-list.get(idx-1); //규칙 세번째 수 부터는 2번째 앞 - 1번째 앞
				if(tmp<0) break; //만약 음수일 경우 break
				list.add(tmp); //음수가 아니라면 리스트에 추가
			}
			max=Math.max(max, list.size()); // 최대값 갱신
			if(max==list.size()) { //만약 최대값이 업데이트가 됐다면 리스트꺼 result에 복사
				result.clear(); //일단 비워주고
				for(Integer s : list) { //넣어주기
					result.add(s);
				}
			}
			list.clear(); //list는 새로운거 받아야하니까 비워주고
			list.add(N);// 다시 첫번째 수 넣어주기
		}
		
		//출력
		System.out.println(result.size());
		for (Integer i : result) {
			System.out.print(i+" ");
		}
		
	}
}
