package android.extend.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by lubin on 2016/9/12.
 */
public class WriterSDUtil {

    public static void writeToSDCardFile(String file, String destDirStr,
                                         String szOutText) {

        // 获取扩展SD卡设备状态
        String sDStateString = android.os.Environment.getExternalStorageState();

        File myFile = null;
        FileOutputStream outputStream = null;
        // 拥有可读可写权限
        if (sDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {

            try {

                // 获取扩展存储设备的文件目录
                File SDFile = android.os.Environment.getExternalStorageDirectory();

                File destDir = new File(SDFile.getAbsolutePath() + File.separator + destDirStr);//文件目录

                if (!destDir.exists()) {//判断目录是否存在，不存在创建
                    destDir.mkdir();//创建目录
                }
                // 打开文件
                myFile = new File(destDir + File.separator + file + ".txt");

                // 判断文件是否存在,不存在则创建
                if (!myFile.exists()) {
                    myFile.createNewFile();//创建文件
                }

                // 写数据   注意这里，两个参数，第一个是写入的文件，第二个是指是覆盖还是追加，
                //默认是覆盖的，就是不写第二个参数，这里设置为true就是说不覆盖，是在后面追加。
                outputStream = new FileOutputStream(myFile, false);
                outputStream.write(szOutText.getBytes());//写入内容
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.getStackTrace();
            }

        }
    }
}
