package StatementCoverageTool;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.instrument.Instrumentation;
import java.util.HashSet;


public class Agent {
    /* The premain is a mechanism associated with the java.lang.instrument package, 
    used for loading "Agents" which make byte-code changes in Java programs. */
    public static void premain(String agentArgs, Instrumentation inst) {
        // getting prefix of the classes under test
        String path = getPath()+File.separator;
        inst.addTransformer(new TransformClassFile(path));
    }

    private static String getPath() {
        int result = 0;
        String outPath = null;
        String rootPath = "src" + File.separator + "main" + File.separator + "java";
        File root = new File(rootPath);
        File[] list = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals(".DS_Store");
            }
        });
        HashSet<String> paths = new HashSet<String>();
        assert list != null;
        getAllPath(list, paths);
        for (String path : paths) {

            int temp = countMatches(path);
            if (result == 0 || temp<result){
                result = temp;
                outPath = path;
            }
        }
        return outPath;
    }

    private static void getAllPath(File[] files, HashSet<String> paths) {
        for (File file : files) {
            if (file.isDirectory()) {
                getAllPath(file.listFiles(), paths);
            } else {
                if(file.getName().endsWith(".java")){
                    String path = file.getParent();
                    path = path.substring(14);
                    paths.add(path);
                }
            }
        }
    }

    private static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    private static int countMatches(String text) {
        if (isEmpty(text) || isEmpty(File.separator)) {
            return 0;
        }
        int index = 0, count = 0;
        while (true) {
            index = text.indexOf(File.separator, index);
            if (index != -1) {
                count++;
                index += File.separator.length();
            } else {
                break;
            }
        }
        return count;
    }

}


