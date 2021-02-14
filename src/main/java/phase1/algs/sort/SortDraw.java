package phase1.algs.sort;

import java.awt.*;

public class SortDraw {

    private static int max = 0;
    private static int len = 0;
    private static double[] xScale;
    private static double[] yScale;
    private static int currentIndex = 0;

    public static void setScale(int[] arr) {
        getMax(arr);
        xScale = new double[]{-1, 1.5};
        yScale = new double[]{0, max + 10};

//        StdDraw.setXscale(xScale[0], xScale[1]);
//        StdDraw.setYscale(yScale[0], yScale[1]);
    }

    public static void getMax(int[] arr) {
        len = arr.length;
        for (int i = 0; i < len; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
    }

    public static void doDraw(int[] arr, boolean pause, boolean clear) {
        currentIndex ++;
        for (int i = 0; i < len; i++) {
            double  x = 1.0 * i / len;
            double  y = arr[i] / 2.0;
            double rw = 0.4 / len;
            double rh = arr[i] / 2.0;

//            StdDraw.setPenColor();
//            if (i == currentIndex) {
//                StdDraw.setPenColor(Color.gray);
//            }
//            StdDraw.filledRectangle(x, y, rw, rh);
        }
        if (pause) {
//            StdDraw.pause(1000);
//            if (clear) {
//                StdDraw.clear();
//            }
        }
    }

}
