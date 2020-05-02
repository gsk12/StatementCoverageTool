package StatementCoverageTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/* JUnitListener captures the start and end events of each JUnit test method, overriding it to get the log as per given format */
public class JUnitListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        CoverageCollector.testSuite = new HashMap<String, HashMap<String, LinkedHashSet<Integer>>>();
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        String dir = "StatementCoverageLog";
        String logPath = dir + File.separator + "StatementCoverage.txt";
        try {
            File directory = new File(dir);
            if (! directory.exists()){
                directory.mkdir();
            }

            File file = new File(logPath);
            if (!file.exists())
                file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuilder sb = new StringBuilder();
            for (String testName : CoverageCollector.testSuite.keySet()) {
                sb.append(testName + "\n");
                HashMap<String, LinkedHashSet<Integer>> testCase = CoverageCollector.testSuite.get(testName);

                for (String className : testCase.keySet()) {
                    for(int i : testCase.get(className)){
                        sb.append(className + ":" + i + "\n");
                    }
                }
            }
            bw.write(sb.toString());
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        CoverageCollector.testName = "[TEST] " + description.getClassName() + ":" + description.getMethodName();
        CoverageCollector.testCase = new HashMap<String, LinkedHashSet<Integer>>();
    }

    @Override
    public void testFinished(Description description) throws Exception {
        CoverageCollector.testSuite.put(CoverageCollector.testName,CoverageCollector.testCase);
        super.testFinished(description);

    }

}
