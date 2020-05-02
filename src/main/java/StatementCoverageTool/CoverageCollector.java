package StatementCoverageTool;

import java.util.HashMap;
import java.util.LinkedHashSet;
/* Collectes coverage for all the test cases in a test suite */
public class CoverageCollector {
    public static HashMap<String, HashMap<String, LinkedHashSet<Integer>>> testSuite;
    public static HashMap<String, LinkedHashSet<Integer>> testCase;
    public static String testName;
    // collect line numbers covered by a test case
    public static void visitLine(String className, int line){
        if(testCase == null || className == null) return;
        LinkedHashSet<Integer> lineset = testCase.get(className);
        if(lineset == null) {
            lineset = new LinkedHashSet<Integer>();
        }
        lineset.add(line);
        testCase.put(className, lineset);
    }
}
