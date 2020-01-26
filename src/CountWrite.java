import InterpreterFile.Interpreter;
import InterpreterFile.Interpreter2;
import InterpreterFile.Laxer;
import InterpreterFile.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountWrite {
//    private static final String PY_URL = "python D:\\Coding\\Project_Python\\TF3.5\\Predict2.py";
    private static final String PY_URL = "python D:\\Coding\\Project_Python\\TF3.5\\Predict_NoneLength.py";
   // private static final String TEST_URL = "python D:\\Coding\\Project_Python\\TF3.5\\test.py";
    private static final String DATA_SWAP = "D:\\Coding\\Project_Python\\TF3.5\\Result\\";

    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }


    public static List<String> readAnswer() {
        BufferedReader br;
        List<String> answer = new ArrayList<String>();

        for (String s :getFiles(DATA_SWAP)){
            try {
                br = new BufferedReader(new FileReader(new File(s)));
                answer.add(br.readLine());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return answer;
    }

    public static void execPy() {
        Process proc = null;
        try {

            proc = Runtime.getRuntime().exec(PY_URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gbk"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            int res = proc.waitFor();
            System.out.println("成功显示：0  失败显示：1");
            System.out.println("调用结果："+res);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        execPy();
        System.out.println();
        for (String read: readAnswer())
            try{
                System.out.println("当前解析数值： "+read +"  ");
                Laxer laxer = new Laxer(read);
                Parser parser = new Parser(laxer);
                Interpreter2 interpreter = new Interpreter2(parser);
                System.out.println(read+" = "+interpreter.interpret());
                Thread.sleep(200);
                System.out.println();
            }catch (Exception e){
                e.printStackTrace();
            }

    }
}
