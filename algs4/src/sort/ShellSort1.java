package sort;

import java.util.List;

public class ShellSort1 {
    private List<Integer> stepSequence;

    private void getShellStepSequence(int[] arr) {
        int step = arr.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
    }

    private void getMyStepSequence(int[] arr) {

    }

    protected void sort(int[] arr) {
        for (Integer step : stepSequence) {
            sort(arr, step);
        }
    }

    /**
     *  分成step列排序
     */
    private void sort(int[] arr, int step) {
        // 第col列
        for (int col = 0; col < step; col++) {

        }
    }
}
