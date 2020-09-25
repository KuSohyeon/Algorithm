
import java.util.Scanner;

public class UpsideDownWords2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char [] crr = sc.nextLine().toCharArray();

		
		int idx=0;
		boolean flag=false;
		for(int i=0;i<crr.length-1;i++) {
			char now = crr[i];
			idx=i;
			flag=false;
			if(now==' ') {// 공백이면 그냥 찍기
				System.out.print(now);
				continue;
			}
			for(int j=i+1;j<crr.length;j++) {
				char next = crr[j];
				if(crr[i]!='<') {//만약 그냥 단언데
					if(crr[j]==' ' || crr[j]=='<') {// 공백이거나 시작 태그 만나면 거기까지 뒤집어야하니까
						i=j-1; //인덱스 넘겨주기
						break;
					}
				}
				
				if(crr[i]=='<') { // 시작태그만나면 뒤집으면 안됨
					if(crr[j]=='>') { // 종료태그만날때까지
						i=j;
						flag=true; // flag를 이용해서 뒤집으면 안되는거 표시
						break;
					}
				}
				if(j==crr.length-1) { // j가 배열의 마지막 요소이면
					i=j; // 그 인덱스 반환
					break;
				}
			}
			if(flag) { // 만약 시작태그 종료태그 만났으면
				for(int s=idx;s<=i;s++) { // 그대로 출력
					System.out.print(crr[s]);
				}
				continue;
			}
			for(int s=i;s>=idx;s--) { // 아니면 뒤집기
				System.out.print(crr[s]);
			}

		}
	}
}
