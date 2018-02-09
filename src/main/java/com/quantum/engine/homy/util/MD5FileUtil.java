package com.quantum.engine.homy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5FileUtil {
    private static final Logger logger = LoggerFactory
            .getLogger(MD5FileUtil.class);
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    protected static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5FileUtil messagedigest初始化失败", e);
        }
    }

    @SuppressWarnings("resource")
	public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String_bk(String s) {
        return getMD5String(s.getBytes());
    }
    
    public static String getMD5String(String s) {
       String str = null;
    	try {
    		str =  getMD5String(s.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return str;
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    public static void main(String[] args) throws IOException {
        //long begin = System.currentTimeMillis();

        /*String files = "C:/Users/YYP.QJ/Desktop/城堡过山车.zip";
        File big = new File(files);
        //File big = new File("E:/BaiduYunDownload/The.Interview.1080P/The.Interview.2014.1080P.mp4");
        //File big = new File("D:/Photoshop_CS6.3510481888.7z");
        String md5 = getFileMD5String(big);

        long end = System.currentTimeMillis();//8d0e9e80549c6820d19d4225d7c255cd
                                              //8d0e9e80549c6820d19d4225d7c255cd
        System.out.println("md5:" + md5);
        System.out.println(end - begin);
        System.out.println("time:" + ((end - begin) / 1000) + "s");*/
        
        System.out.println(MD5FileUtil.getMD5String(StringHelper.getRandomStr(8)));

    }

}