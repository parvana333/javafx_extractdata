package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Methods {

    public String findRNN(File file, String RNN){
        String transacId = "";
        try {
            FileReader fileReader = new FileReader(file);
            System.out.println("enter 111");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(RNN)) {
                    transacId = line.substring(0, 9);
                    break;
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            transacId = "no file";
        }
        return transacId;

    }

    public List<String> findId(File file, String transacId) {
        List<String> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(transacId)) {
                    list.add(line);
                }
            }
            bufferedReader.close();
            return list;
        } catch (Exception e) {

        }
        return list;
    }

    public String createDoc(List<String> list, String directory, String filename, String RNN){
        String answer="ok";
        try {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            File file = new File(directory);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(directory + filename));
            for (String l : list) {
                if(l.contains("[Y]")){
                    System.out.println("1");
                    int indexBegin=l.indexOf("[Y]");
                    XWPFRun run3 = paragraph.createRun();
                    run3.setText(l.substring(0,indexBegin));
                    XWPFRun run4 = paragraph.createRun();
                    run4.setText(l.substring(indexBegin,indexBegin+3));
                    run4.setText("3D secured");
                    run4.setTextHighlightColor("yellow");
                    if(l.contains(RNN)){
                        System.out.println("2");
                        XWPFRun run5 = paragraph.createRun();
                        run5.setText(l.substring(indexBegin+3,l.length() - RNN.length()));
                        XWPFRun run6 = paragraph.createRun();
                        run6.setText(RNN);
                        run6.setTextHighlightColor("yellow");
                        run6.addBreak();
                    }
                    else{
                        System.out.println("3");
                        XWPFRun run6 = paragraph.createRun();
                        run6.setText(l.substring(indexBegin+3,l.length()));
                        run6.addBreak();
                    }
                }
                else if(l.contains("[A]")){
                    System.out.println("4");
                    int indexBegin=l.indexOf("[A]");
                    XWPFRun run7 = paragraph.createRun();
                    run7.setText(l.substring(0,indexBegin));
                    XWPFRun run8 = paragraph.createRun();
                    run8.setText(l.substring(indexBegin,indexBegin+3));
                    run8.setText("secure attempt");
                    run8.setTextHighlightColor("yellow");
                    if(l.contains(RNN)){
                        System.out.println("5");
                        XWPFRun run9 = paragraph.createRun();
                        run9.setText(l.substring(indexBegin+3,l.length() - RNN.length()));
                        XWPFRun run10 = paragraph.createRun();
                        run10.setText(RNN);
                        run10.setTextHighlightColor("yellow");
                        run10.addBreak();
                    }
                    else{
                        System.out.println("6");
                        XWPFRun run11 = paragraph.createRun();
                        run11.setText(l.substring(indexBegin+3));
                        run11.addBreak();
                    }
                }
                else if(l.contains(RNN)){
                    System.out.println("7");
                    XWPFRun run12 = paragraph.createRun();
                    run12.setText(l.substring(0,l.length()-RNN.length()));
                    XWPFRun run13 = paragraph.createRun();
                    run13.setText(RNN);
                    run13.setTextHighlightColor("yellow");
                    run13.addBreak();
                }
                else{
                    System.out.println("8");
                    XWPFRun run14 = paragraph.createRun();
                    run14.setText(l);
                    run14.addBreak();
                }
            }
            document.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
         answer="wrong";
        }
       return answer;
    }


}
