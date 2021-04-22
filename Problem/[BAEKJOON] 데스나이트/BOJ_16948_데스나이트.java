import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16948_데스나이트 {static int N;
    static int[][] location;
    static boolean[][] v;
    static int[] dy = {-2, -2, 0, 0, 2, 2};
    static int[] dx = {-1, 1, -2, 2, -1, 1};
    static class Point {
        int i;
        int j;
        int cnt;

        public Point(int i, int j, int cnt) {
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        @Override
        public java.lang.String toString() {
            return "Point{" +
                    "i=" + i +
                    ", j=" + j +
                    ", cnt=" + cnt +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        location = new int[2][2];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            location[i][0] = Integer.parseInt(st.nextToken());
            location[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs());
    }
    private static int bfs() {
        int result = Integer.MAX_VALUE;
        Queue<Point> q = new LinkedList<Point>();
        v = new boolean[N][N];
        q.offer(new Point(location[0][0], location[0][1], 0));
        v[location[0][0]][location[0][1]] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.i == location[1][0] && cur.j == location[1][1]) {
                result = cur.cnt;
                break;
            }

            for (int d = 0; d < 6; d++) {
                int ni = cur.i + dy[d];
                int nj = cur.j + dx[d];
                if (ni < 0 || ni >= N || nj < 0 || nj >= N || v[ni][nj]) continue;
                v[ni][nj] = true;
                q.offer(new Point(ni, nj, cur.cnt + 1));
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
