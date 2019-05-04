package com.graduate.engine.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 文件处理工具
 */
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    @SuppressWarnings("unused")
    private static final int BUFFER_SIZE = 16 * 1024;

    /**
     * 获取对应文件夹
     *
     * @param folder
     * @return
     * @method name: getFolder
     */
    private static String getFolder(String folder) {

        if (!File.separator.equals(folder.substring(folder.length() - 1))) {
            folder = folder + File.separator;
        }
        return folder;
    }

    /**
     * 判断文件是否已经存在
     *
     * @param folder
     * @param fileName
     * @return
     * @method name: isFileExisted
     */
    public static boolean isFileExisted(String folder, String fileName) {
        File file = new File(getFolder(folder) + fileName);
        if (!file.exists()) {
            return false;
        }

        return true;
    }

    /**
     * 将文件转成字节流
     *
     * @param folder   文件夹
     * @param fileName 文件名称
     * @return
     */
    public static byte[] getBytes(String folder, String fileName) {

        return getBytes(getFolder(folder) + fileName);
    }

    /**
     * 将文件转成字节流
     *
     * @param fileName 文件名(完整路径)
     * @return
     */
    public static byte[] getBytes(String fileName) {

        if (!(new File(fileName).exists())) {
            return null;
        }

        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(fileName));
            ByteArrayOutputStream outByte = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = buf.read(b, 0, 1024)) != -1) {
                outByte.write(b, 0, i);
            }
            outByte.flush();
            buf.close();
            return outByte.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
