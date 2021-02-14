package phase1.practice.bak;

public class SortedSparseArraySearch {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 0, 0, 0, 0, 5, 0, 0, 8, 0, 0, 0, 19, 0, 0, 0, 212};
        int position = search(arr, 19);
        System.out.println("position: " + position);
    }

    private static int search(int[] arr, int val) {
        int len = arr.length;
        if (len < 1) {
            return -1;
        }
        int left  = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (val == arr[mid]) {
                return mid;
            } else if (val > arr[mid]) {
                while (arr[mid] == 0) {
                    mid++;
                }
                left = mid;
            } else {
                while (arr[mid] == 0) {
                    mid--;
                }
                right = mid;
            }
        }

        return -1;
    }
}
