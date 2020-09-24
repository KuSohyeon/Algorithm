
import java.util.Scanner;

public class CroatiaAlphabet {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char [] crr = sc.next().toCharArray();

		int cnt=0;
		// 각 경우마다 조건 잘 체크 해주기
		for(int i=0;i<crr.length;i++) {
			if(crr[i]=='=') {
				if(i!=0) {
					if(crr[i-1]=='c' || crr[i-1]=='s' || crr[i-1]=='z') continue;
				}
			}
			if(crr[i]=='-') {
				if(i!=0) {
					if(crr[i-1]=='c' || crr[i-1]=='d') continue;
				}
			}
			if(crr[i]=='z') {
				if(i>0 && i<crr.length-1) {
					if(crr[i-1]=='d' && crr[i+1]=='=') {
						i++;
						continue;
					}
				}
			}else if(crr[i]=='l') {
				if(i<crr.length-1) {
					if(crr[i+1]=='j') {
						i++;
					}
				}

			}else if(crr[i]=='n') {
				if(i<crr.length-1) {
					if(crr[i+1]=='j') {
						i++;
					}
				}
			}
			cnt++;
		}

		System.out.println(cnt);
	}
}
