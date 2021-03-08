import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18426_감시피하기 {
    static int N;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0}; //상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static List<Integer> teacher;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        teacher = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') { // 선생님일 경우 리스트에 해당 번호 저장
                    teacher.add(i * N + j);
                }
            }
        }

        setObstacle(0, 0);
        // 모든 경우가 되지 않을 경우 NO 출력
        System.out.println("NO");
    }
    private static void setObstacle(int cnt, int nowNum) {
        if (nowNum >= N * N) return;
        if (cnt == 3) {
            isPossible();
            return;
        }
        int i = nowNum / N;
        int j = nowNum % N;
        // 빈 칸일 경우 장애물 놓기
        if (map[i][j] == 'X') {
            map[i][j] = 'O';
            setObstacle(cnt + 1, nowNum + 1);
            map[i][j] = 'X';
        }
        // 학생 또는 선생일 경우 넘겨주기
        setObstacle(cnt, nowNum + 1);
    }
    private static void isPossible() {
        for (int i = 0; i < teacher.size(); i++) {
            // 선생님 좌표 찾기
            int y = teacher.get(i) / N;
            int x = teacher.get(i) % N;
            // 4 방향으로 탐색하면서 학생 찾기
            for (int d = 0; d < 4; d++) {
                int ni = y + dy[d];
                int nj = x + dx[d];
                if (ni < 0 || ni >= N || nj < 0 || nj >= N || map[ni][nj] == 'O') continue;
                if (map[ni][nj] == 'S') return; // 학생일 경우 return
                // 그 방향으로 계속 탐색
                while (true) {
                    ni += dy[d];
                    nj += dx[d];
                    if (ni < 0 || ni >= N || nj < 0 || nj >= N || map[ni][nj] == 'O') break;
                    if (map[ni][nj] == 'S') return;// 학생일 경우 return
                }
            }
        }
        System.out.println("YES");
        System.exit(0);// 시스템 종료
    }
}
