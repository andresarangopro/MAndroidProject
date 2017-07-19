package com.lubin.chj.utils;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;
public class FileUtils {
    private Context context;
    /** SD卡是否存在 **/
    private boolean hasSD = false;
    /** SD卡的路径 **/
    private String SDPATH;
    /** 当前程序包的路径 **/
    private String FILESPATH;

    public FileUtils(Context context) {
        this.context = context;
        hasSD = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        SDPATH = Environment.getExternalStorageDirectory().getPath();
        FILESPATH = this.context.getFilesDir().getPath();
    }

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    public File createSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + "//" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 删除SD卡上的文件
     *
     * @param fileName
     */
    public boolean deleteSDFile(String fileName) {
        File file = new File(SDPATH + "//" + fileName);
        if (file == null || !file.exists() || file.isDirectory())
            return false;
        return file.delete();
    }

    /**
     * 写入内容到SD卡中的txt文本中 str为内容
     */
    public void writeSDFile(String str, String fileName) {
        try {
            FileWriter fw = new FileWriter(SDPATH + "//" + fileName);
            File f = new File(SDPATH + "//" + fileName);
            fw.write(str);
            FileOutputStream os = new FileOutputStream(f);
            DataOutputStream out = new DataOutputStream(os);
            out.writeShort(2);
            out.writeUTF("");
            System.out.println(out);
            fw.flush();
            fw.close();
            System.out.println(fw);
        } catch (Exception e) {
        }
    }

    /**
     * 读取SD卡中文本文件
     *
     * @param fileName
     * @return
     */
    public String readSDFile(String fileName) {
        StringBuffer sb = new StringBuffer();
        File file = new File(SDPATH + "//" + fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            int c;
            while ((c = fis.read()) != -1) {
                sb.append((char) c);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getFILESPATH() {
        return FILESPATH;
    }

    public String getSDPATH() {
        return SDPATH;
    }

    public boolean hasSD() {
        return hasSD;
    }

    //解析xml
    public void readxml(String fileName) {
        StringBuffer sb = new StringBuffer();
        File path = new File(SDPATH + "//" + fileName);
        try {
//            File path = new File(Environment.getExternalStorageDirectory(),
//                    "persons.xml");
            FileInputStream fis = new FileInputStream(path);

            // 获得pull解析器对象
            XmlPullParser parser = Xml.newPullParser();
            // 指定解析的文件和编码格式
            parser.setInput(fis, "utf-8");

            int eventType = parser.getEventType(); // 获得事件类型

            String id = null;
            String name = null;
            String gender = null;
            String age = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName(); // 获得当前节点的名称

                switch (eventType) {
                    case XmlPullParser.START_TAG: // 当前等于开始节点 <person>
                        if ("persons".equals(tagName)) { // <persons>
                        } else if ("person".equals(tagName)) { // <person id="1">
                            id = parser.getAttributeValue(null, "id");
                        } else if ("name".equals(tagName)) { // <name>
                            name = parser.nextText();
                        }else if ("gender".equals(tagName)) { // <age>
                            gender = parser.nextText();
                        } else if ("age".equals(tagName)) { // <age>
                            age = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG: // </persons>
                        if ("person".equals(tagName)) {
                            Log.i("FileUtils", "id---" + id);
                            Log.i("FileUtils", "name---" + name);
                            Log.i("FileUtils", "gender---" + gender);
                            Log.i("FileUtils", "age---" + age);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next(); // 获得下一个事件类型
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
    }
}