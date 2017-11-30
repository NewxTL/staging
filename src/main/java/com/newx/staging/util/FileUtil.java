package com.newx.staging.util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by Newx on 2017/9/11.
 */
@Service
public class FileUtil {

    private Logger logger = Logger.getLogger(FileUtil.class);

    public void transcoder(String filePath, String inputCharset, String outputCharset){
        StringBuffer buffer=new StringBuffer();
        try {
            File file = new File(filePath);
            if (!file.exists()){
                return;
            }
            FileInputStream fis=new FileInputStream(filePath);
            InputStreamReader isr=new InputStreamReader(fis, inputCharset);
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            while ((line=br.readLine())!=null) {
                buffer.append(line);
                buffer.append("\r\n");
            }
            buffer.delete(buffer.length()-2,buffer.length());
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fos=new FileOutputStream(filePath);
            OutputStreamWriter osw=new OutputStreamWriter(fos, outputCharset);
            osw.write(buffer.toString());
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
