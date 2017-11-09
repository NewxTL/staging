package com.newx.staging.util;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FtpUtil {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(FtpUtil.class);
    /**
     * 上传文件（可供Action/Controller层使用）
     * @param hostname FTP服务器地址
     * @param port  FTP服务器端口号
     * @param username  FTP登录帐号
     * @param password  FTP登录密码
     * @param pathname  FTP服务器保存目录
     * @param fileName  上传到FTP服务器后的文件名称
     * @param inputStream 输入文件流
     * @return
     */
    public boolean uploadFile(String hostname, int port, String username, String password, String pathname, String fileName, InputStream inputStream){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
//        ftpClient.setControlEncoding("UTF-8");
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //是否成功登录FTP服务器
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                log.error("===============ftp服务连接失败");
                return flag;
            }

            log.info("=================FTP服务器保存目录: " + pathname);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.makeDirectory(pathname);
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    /**
     * 上传文件（可对文件进行重命名）
     * @param hostname FTP服务器地址
     * @param port  FTP服务器端口号
     * @param username  FTP登录帐号
     * @param password  FTP登录密码
     * @param pathname  FTP服务器保存目录
     * @param filename  上传到FTP服务器后的文件名称
     * @param originfilename 待上传文件的名称（绝对地址）
     * @return
     */
    public boolean uploadFileFromProduction(String hostname, int port, String username, String password, String pathname, String filename, String originfilename){
        boolean flag = false;
        try {
            File file = new File(originfilename);
            if (!file.exists()){
                log.info("该路径{}下无待上传文件", originfilename);
                return true;
            }
            InputStream inputStream = new FileInputStream(file);
            flag = uploadFile(hostname, port, username, password, pathname, filename, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上传文件（不可以进行文件的重命名操作）
     * @param hostname FTP服务器地址
     * @param port  FTP服务器端口号
     * @param username  FTP登录帐号
     * @param password  FTP登录密码
     * @param pathname  FTP服务器保存目录
     * @param originfilename 待上传文件的名称（绝对地址）
     * @return
     */
    public boolean uploadFileFromProduction(String hostname, int port, String username, String password, String pathname, String originfilename){
        boolean flag = false;
        try {
            String fileName = new File(originfilename).getName();
            InputStream inputStream = new FileInputStream(new File(originfilename));
            flag = uploadFile(hostname, port, username, password, pathname, fileName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 删除文件
     * @param hostname FTP服务器地址
     * @param port  FTP服务器端口号
     * @param username  FTP登录帐号
     * @param password  FTP登录密码
     * @param pathname  FTP服务器保存目录
     * @param filename  要删除的文件名称
     * @return
     */
    public boolean deleteFile(String hostname, int port, String username, String password, String pathname, String filename){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //验证FTP服务器是否登录成功
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }

    /**
     * 下载文件
     * @param hostname FTP服务器地址
     * @param port  FTP服务器端口号
     * @param username  FTP登录帐号
     * @param password  FTP登录密码
     * @param pathname  FTP服务器文件目录
     * @param filename  文件名称
     * @param localpath 下载后的文件路径
     * @return
     */
    public boolean downloadFile(String hostname, int port, String username, String password, String pathname, String filename, String localpath){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //验证FTP服务器是否登录成功
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }
            //切换FTP目录
            Boolean s= ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File localFile = new File(localpath + "/" + file.getName());
                    OutputStream os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                    flag = true;
                }
            }
            ftpClient.logout();

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 对比两个文件，取出差异行，封装成JSONArray
     * @param localPath      待处理文件路径
     * @param localPathOld   上次处理文件路径
     * @return
     */
    public JSONArray dataLine(String localPath, String localPathOld) throws RuntimeException{
        BOMInputStream fis = null;
        BOMInputStream fis2 = null;
        InputStreamReader isr = null;
        InputStreamReader isr2 = null;
        BufferedReader br = null;// 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        BufferedReader br2 = null;
        JSONArray jsonArray = new JSONArray();
        try {
            String str = "";
            String str2 = "";
            fis = new BOMInputStream(new FileInputStream(localPathOld));// FileInputStream
            fis2 = new BOMInputStream(new FileInputStream(localPath));// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis,"UTF-8");
            br = new BufferedReader(isr);

            isr2 = new InputStreamReader(fis2,"UTF-8");
            br2 = new BufferedReader(isr2);

            List<String> list1 = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            List<String> list3 = new ArrayList<String>();
            while ((str = br.readLine()) != null) {
                list1.add(str);
            }
            while ((str2 = br2.readLine()) != null) {
                list2.add(str2);
            }
            for (int i = 0; i < list2.size(); i++) {    //注意list2来源数据要distinct
                if (list1.contains(list2.get(i))== false) {
                    jsonArray.add(list2.get(i).toString());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到指定文件");
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                br2.close();
                isr2.close();
                fis2.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    /**
     * 下载文件
     * @param hostname FTP服务器地址
     * @param port  FTP服务器端口号
     * @param username  FTP登录帐号
     * @param password  FTP登录密码
     * @param pathname  FTP服务器文件目录
     * @param localpath 下载后的文件路径
     * @return
     */
    public boolean downloadFileList(String hostname, int port, String username, String password, String pathname, String localpath){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostname, port);
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }
            ftpClient.changeWorkingDirectory(pathname);
            System.out.println("ftp : " + pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            //下载文件
            for(FTPFile file : ftpFiles){
                System.out.println("file : " +  file.getName());
                File localFile = new File(localpath + "/" + file.getName());
                OutputStream os = new FileOutputStream(localFile);
                ftpClient.retrieveFile(file.getName(), os);
                os.close();
            }
            //删除文件
            for (FTPFile ftpFile: ftpFiles){
                ftpClient.dele(ftpFile.getName());
            }
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }

    /**
     * 下载文件
     * @param hostname FTP服务器地址
     * @param port  FTP服务器端口号
     * @param username  FTP登录帐号
     * @param password  FTP登录密码
     * @param pathname  FTP服务器文件目录
     * @return
     */
    public boolean deleteFileList(String hostname, int port, String username, String password, String pathname){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostname, port);
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }
            ftpClient.changeWorkingDirectory(pathname);
            System.out.println("ftp : " + pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();

//            删除文件
            for (FTPFile ftpFile: ftpFiles){
                ftpClient.dele(ftpFile.getName());
            }
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }
}