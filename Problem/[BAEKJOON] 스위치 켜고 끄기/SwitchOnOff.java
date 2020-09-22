//[백준] 스위치 켜고 끄기
/*
8
0 1 0 1 0 0 0 1
2
1 3
2 1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SwitchOnOff {
	static int [] light, gender, num;
	static int L, N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int L =Integer.parseInt(br.readLine()); // 스위치 개수
		light = new int[L+1]; //스위치 개수만큼 배열 잡아주기 +1
		
		// 스위치 입력
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=L;i++) {
			light[i]=Integer.parseInt(st.nextToken());
		}
		
		//학생 입력
		N = Integer.parseInt(br.readLine());
		gender = new int[N];
		num = new int[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			gender[i]=Integer.parseInt(st.nextToken());
			num[i]=Integer.parseInt(st.nextToken());
		}
		
		//To-Do
		// 학생 수 만큼 처리를 해줘야 함
		for(int i=0;i<N;i++) {
			if(gender[i]==1) { // 남학생의 경우
				int now = num[i];
				for(int b=1;b<=L;b++) {
					if(b%now==0) {
						light[b]=light[b]==0?1:0; // 삼항 연산자를 사용해서 Toggle
					}
				}
			}else { // 여학생의 경우
				int now = num[i];
				light[now]= light[now]==0?1:0; // 일단 그 번호 Toggle
				for(int n=1;n<L;n++) {
					if(now-n<1 || now+n>L) break;
					if(light[now-n]==light[now+n]) { // 양 사이드가 같으면 바꿔주고
						light[now-n]=light[now+n]=light[now-n]==0?1:0; 
					}else break; // 다르면 브레이크로 빠져나오기
				}
			}
			
		}

		for(int i=1;i<=L;i++) {
			System.out.print(light[i]+" ");
			if(i!=0 && i%20==0) System.out.println(); //20개 출력하고 나서 줄바꿔주기!!!!!!! 조건 좀 잘 챙겨라!!!!!!!
		}
	}
}
