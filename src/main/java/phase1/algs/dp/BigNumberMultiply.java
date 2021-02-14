package phase1.algs.dp;

/**
 * 大数乘法
 *
 * 按照小学的乘法运算,在进行 n位数 之间的相乘时, 需要大约进行 n^2次个 位数的相乘
 *
 * karatsuba算法:
 *     AB * CD = A*C + B*D + B*C + A*D
 *     B*C + A*D = A*C + B*D - (A-B) * (C-D)
 */
public class BigNumberMultiply {
    public static void main(String[] args) {

    }

}
