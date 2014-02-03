import java.io.*;
import java.util.*;

public class Solution {
    static int[][] mountain;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int width = 0, height = 0, cnt = 0;
        while (in.hasNext()) {
            String curr = in.nextLine();
            String[] row = curr.split(" ");
            if (cnt==0) {
                width = Integer.parseInt(row[1]);
                height = Integer.parseInt(row[0]);
                mountain = new int[height][width];
            } else for (int i=0; i<width; i++)  mountain[cnt-1][i]=Integer.parseInt(row[i]);
            cnt++;
        }

        ArrayList<ArrayList<Vector>> routes = new ArrayList<ArrayList<Vector>>();
        for (int x = 0; x < width; x++) {
            ArrayList<Vector> tmp = new ArrayList<Vector>();
            tmp.add(new Vector(x, height - 1));
            routes.add(climb(mountain, tmp));
        }

        ArrayList<Vector> safest = new ArrayList<Vector>();
        for (ArrayList<Vector> curr:routes) if (safest.size()==0 || sum(curr)<sum(safest)) safest = curr;
        String path = "";
        for (int i=safest.size()-1; i>=0; i--) path+="[" + safest.get(i).y + "," + safest.get(i).x + "]";
        System.out.println("Minimum risk path = " + path);
        System.out.println("Risks along the path = " + sum(safest));
    }

    public static ArrayList<Vector> climb(int[][] mtn, ArrayList<Vector> prev) {
        return climb(mtn, prev, Integer.MAX_VALUE);
    }

    public static ArrayList<Vector> climb(int[][] mtn, ArrayList<Vector> prev, int limit) {
        Vector position = prev.get(prev.size()-1);
        if (position.y == 0) return prev;

        ArrayList<Vector> route = new ArrayList<Vector>();
        route.addAll(prev);

        if (sum(prev) > limit) return null;

        int rLeft = Integer.MAX_VALUE;
        int rCenter = Integer.MAX_VALUE;
        int rRight = Integer.MAX_VALUE;

        if (position.x > 0) {
            ArrayList<Vector> leftRoute = new ArrayList<Vector>();
            leftRoute.addAll(route);
            leftRoute.add(new Vector(position.x - 1, position.y - 1 ));
            ArrayList<Vector> l = climb(mtn, leftRoute, limit);
            if (l != null) rLeft = sum(l);
        }

        ArrayList<Vector> centerRoute = new ArrayList<Vector>();
        centerRoute.addAll(route);
        centerRoute.add(new Vector(position.x, position.y - 1));
        ArrayList<Vector> c = climb(mtn, centerRoute, Math.min(limit, rLeft));
        if (c != null) rCenter = sum(c);

        if (position.x < mtn[0].length - 1) {
            ArrayList<Vector> rightRoute = new ArrayList<Vector>();
            rightRoute.addAll(route);
            rightRoute.add(new Vector(position.x + 1, position.y - 1));
            ArrayList<Vector> r = climb(mtn, rightRoute, Math.min(limit, Math.min(rLeft, rCenter)));
            if (r != null) rRight = sum(r);
        }

        int lowest = Math.min(rLeft, Math.min(rCenter, rRight));

        if (lowest == Integer.MAX_VALUE) return null;
        if (rLeft == lowest) route.add(new Vector(position.x - 1, position.y - 1));
        else if (rCenter == lowest) route.add(new Vector(position.x, position.y - 1));
        else route.add(new Vector(position.x + 1, position.y - 1));

        return climb(mtn, route);
    }

    public static Integer sum(List<Vector> list) {
        Integer sum = 0;
        for (Vector v:list) sum += mountain[v.y][v.x];
        return sum;
    }
}

class Vector {
    public int x, y;
    public Vector(int X, int Y) {
        x = X;
        y = Y;
    }
}