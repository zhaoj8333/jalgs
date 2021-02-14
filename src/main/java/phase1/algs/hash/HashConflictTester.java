package phase1.algs.hash;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HashConflictTester {

    static void hashTest() throws IOException {
        /*
        从哈希效果来看（Chi^2应该是指卡方分布），虽然33并不一定是最好的数值。
        但17、31、33、63、127和129等相对其他的奇数的一个很明显的优势是，由于这些奇数与16、32、64、128只相差1，
        可以通过移位（如1 << 4 = 16）和加减1来代替乘法，速度更快。
         */
        List<String> words = getWords();
        System.out.println();
//        PrintStream ps = new PrintStream(new FileOutputStream("work.txt"));
//        System.setOut(ps);
        System.out.println("multiplier, confictSize, confictRate, timeCost, listSize, minHash, maxHash");
        for (int i = 0; i < 256; i++) {
            String str = computeConflictRate(words, i);
            System.out.println(str);
        }
    }

    private static String computeConflictRate(List<String> lines, int multiplier) throws FileNotFoundException {
//        Stopwatch st = new Stopwatch();
        List<Integer> hashList = computeHashes(lines, multiplier);
//        double duration = st.elapsedTime();

        Comparator<Integer> comparator = (x, y) -> x > y ? 1 : (x < y ? -1 : 0);
        int maxHash = hashList.parallelStream().max(comparator).get();
        int minHash = hashList.parallelStream().min(comparator).get();

        Set<Integer> hashSet = hashList.parallelStream().collect(Collectors.toSet());

        int conflictSize = lines.size() - hashSet.size();
        float conflictRate = conflictSize * 1.0f / lines.size();

        return String.format(
                "%s, %s, %s, %s, %s, %s, %s",
                multiplier, conflictSize, conflictRate, 0, lines.size(), minHash, maxHash
        );
    }

    private static List<Integer> computeHashes(List<String> lines, int multiplier) {
        Function<String, Integer> hashFunction = x -> {
            int hash = 0;
            for (int i = 0; i < x.length(); i++) {
                hash = (multiplier * hash) + x.charAt(i);
            }
            return hash;
        };
        return lines.parallelStream().map(hashFunction).collect(Collectors.toList());
    }

    private static List<String> getWords() throws IOException{
        InputStream is = Hash.class.getClassLoader().getResourceAsStream("american-english");
//        return IOUtils.readLines(is, "UTF-8");
        return null;
    }

}
