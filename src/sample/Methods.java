package sample;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Methods {

    public String findRNN(File file, String RNN) throws IOException {
        System.out.println("enter 1");
        String transacId="";
        System.out.println("enter 11");
        FileReader fileReader = new FileReader(file);
        System.out.println("enter 111");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(RNN)) {
                transacId=line.substring(0,9);
                break;
            }
        }
        bufferedReader.close();

        return transacId;
    }

    public List<String> findId(File file,String transacId) throws IOException {
        System.out.println("enter 2");
        List<String> list=new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line=null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(transacId)) {
               list.add(line);
            }
        }
        bufferedReader.close();
        return list;
    }

     public void createDoc(List<String> list, String directory,String filename,String RNN) throws IOException {
         System.out.println("enter 3");
         XWPFDocument document=new XWPFDocument();
         XWPFParagraph paragraph=document.createParagraph();
         File file=new File(directory);
         if(!file.exists()){
             file.mkdirs();
         }
         FileOutputStream fileOutputStream=new FileOutputStream(new File(directory+filename));
         for(String l:list){
             if(l.contains(RNN)) {
                 XWPFRun run=paragraph.createRun();
                 run.setText(l.substring(0,l.length()-RNN.length()));
                 XWPFRun run1=paragraph.createRun();
                 run1.setText(RNN);
                 run1.setTextHighlightColor("red");
                 run1.addBreak();
             }
             else{
                 XWPFRun run2=paragraph.createRun();
                 run2.setText(l);
                 run2.addBreak();
             }
         }
         document.write(fileOutputStream);
         fileOutputStream.close();

     }

     public String checkedFile(String fileName,String direction){
         XWPFDocument document=new XWPFDocument();
         XWPFParagraph paragraph=document.createParagraph();
        int num=0;
        String  name=fileName+ ".docx";
        File file=new File(direction+"/"+name);
        while(file.exists()){
            name=fileName+ (num++)+".docx";
            file=new File(direction+"/"+name);
        }
         System.out.println(file.getName().substring(0,file.getName().length()-4));
        return file.getName().substring(0,file.getName().length()-4);
     }


}
