import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12761_돌다리 {
    /**
     * 동규가 주미에게 갈 수 있는 방법은 +1, -1, 스카이콩콩의 힘, 현재 위치 * 스카이 콩콩의 힘이다.
     */
    static int A, B, N, M;
    static int[] power;

    static class Point {
        int x;
        int cnt;

        public Point(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        power = new int[3];
        power[0] = 1;
        power[1] = Integer.parseInt(st.nextToken()); // 스카이 콩콩 힘
        power[2] = Integer.parseInt(st.nextToken()); // 스카이 콩콩 힘

        N = Integer.parseInt(st.nextToken()); // 동규 위치
        M = Integer.parseInt(st.nextToken()); // 주미 위치치
        int result = bfs();

        System.out.println(result);

    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[] v = new boolean[100001];

        q.offer(new Point(N, 0));
        v[N] = true;

        int result = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.x == M) {
                result = cur.cnt;
                break;
            }

            // +1, 1, +-스카이 콩콩 힘, 스카이콩콩 * 현재 위치(cur.x)
            for (int i = 0; i < 3; i++) {
                int plusLocation = cur.x + power[i];
                if (isLocation(plusLocation)
                        && !v[plusLocation]) {
                    v[plusLocation] = true;
                    q.offer(new Point(plusLocation, cur.cnt + 1));
                }
                int minusLocation = cur.x - power[i];
                if (isLocation(minusLocation) && !v[minusLocation]) {
                    v[minusLocation] = true;
                    q.offer(new Point(minusLocation, cur.cnt + 1));
                }
                int multipleLocation = cur.x * power[i];
                if (isLocation(multipleLocation) && !v[multipleLocation]) {
                    v[multipleLocation] = true;
                    q.offer(new Point(multipleLocation, cur.cnt + 1));
                }
            }

        }

        return result;
    }

    private static boolean isLocation(int location) {
        if (location >= 0 && location <= 100000) return true;
        return false;
    }
}
