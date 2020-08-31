//[SW Expert] 스타일리쉬 들여쓰기
/*
9
5 4
(Follow.my.style
.........starting.from.round.brackets)
{then.curly.brackets
.....[.and.finally
.......square.brackets.]}
(Thank.you
{for.showing.me
[all
the.secrets]})
4 2
(This.time.I.will.show.you
.........(how.to.use.round.brackets)
.........[but.not.about.square.brackets]
.........{nor.curly.brackets})
(I.learned
how.to.use.round.brackets)
4 2
(This.time.I.will.show.you
.........(how.to.use.round.brackets)
.........[but.not.about.square.brackets]
.........{nor.curly.brackets})
[I.have.not.learned
how.to.use.square.brackets]
2 2
(Be.smart.and.let.fear.of
..(closed.brackets).go)
(A.pair.of.round.brackets.enclosing
[A.line.enclosed.in.square.brackets])
1 2
Telling.you.nothing.but.you.can.make.it
[One.liner.(is).(never.indented)]
[One.liner.(is).(never.indented)]
2 4
([{Learn.from.my.KungFu
...}])
((
{{
[[
]]}}))
1 2
Do.not.waste.your.time.trying.to.read.from.emptiness
(
)
2 3
({Quite.interesting.art.of.ambiguity
....})
{
(
)}
2 4
({[
............................................................]})
(
{
[
]})
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StylelishCoding{
	static String [] master,me;
	static int [] result;
	static int q,p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine().trim());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			master = new String[p];
			me = new String[q];
			result = new int[q]; //0~양수이면 들여쓰기 개수이고 -1 이면 유효하지 않은 것
			
			Arrays.fill(result, -2); //-2가 아무것도 안넣은거라고 약속하자

			for(int i=0;i<p;i++) {
				master[i]=br.readLine();
			}
			for(int j=0;j<q;j++) {
				me[j]=br.readLine();
			}

			for(int r=1;r<=20;r++) {
				for(int c=1;c<=20;c++) {
					for(int s=1;s<=20;s++) {
						if(isOK(master,r,c,s)) { //master를 토대로 r,c,s 맞는지 확인하는 메소드
							//여기 두번 이상 올 수 도 있으니까
							calIndent(me,r,c,s,result); //r,c,s 로 내꺼 들여쓰기 계산하는 메소드
						}
					}
				}
			}
			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			for(int r : result) {
				sb.append(r).append(" ");
			}
			System.out.println(sb.toString());


		}
	}
	private static void calIndent(String[] text, int r, int c, int s, int[] result) {
		int rCnt=0,cCnt=0,sCnt=0;//지금까지 열린 괄호의 갯수들로 저장할 변수 준비
		
		for(int i=0;i<text.length;i++) {
			//만약 해당 문장에 대해서 계산이 처음이라면
			if(result[i]==-2) {
				result[i] = r*rCnt + c*cCnt + s*sCnt;
			}
			//처음이 아니라면
			else {
				if(result[i] != r*rCnt + c*cCnt + s*sCnt) {
					result[i]=-1;
				}
			}
			//현재 문장을 쭉 읽으면서 소 중 대 괄호 열림갯수를 갱신
			for(char ch : text[i].toCharArray()) {
				switch(ch) {
				case '(':
					rCnt++;
					break;
				case ')':
					rCnt--;
					break;
				case '{':
					cCnt++;
					break;
				case '}':
					cCnt--;
					break;
				case '[':
					sCnt++;
					break;
				case ']':
					sCnt--;
					break;
				}
			}
			
		}
		
	}
	private static boolean isOK(String [] text, int r, int c, int s) {
		int rCnt=0,cCnt=0,sCnt=0;//지금까지 열린 괄호의 갯수들로 저장할 변수 준비

		for(int i=0;i<text.length;i++) {
			int cnt=0;
			for(char ch : text[i].toCharArray()) {
				if(ch == '.') cnt++; //ch가 들여쓰기면 cnt++
				else break; //아니면 바로 break;
			}
			if(cnt != r*rCnt + c*cCnt + s*sCnt) return false;
			//현재 문장을 쭉 읽으면서 소 중 대 괄호 열림갯수를 갱신
			for(char ch : text[i].toCharArray()) {
				switch(ch) {
				case '(':
					rCnt++;
					break;
				case ')':
					rCnt--;
					break;
				case '{':
					cCnt++;
					break;
				case '}':
					cCnt--;
					break;
				case '[':
					sCnt++;
					break;
				case ']':
					sCnt--;
					break;
				}
			}
		}
		return true;
	}
}