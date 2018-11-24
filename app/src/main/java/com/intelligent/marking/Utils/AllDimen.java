package com.intelligent.marking.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class AllDimen {
    private final static String rootPath = "/Users/zhanghongwei/Downloads/Marking-master/app/src/main/src/values-{0}x{1}";

    private final static float dw = 360f;
    private final static float dh = 640f;

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    public static void main(String[] args) {
//        makeString(320, 480);
//        makeString(480, 854);
//        makeString(720, 1280);
                makeString(720, 1208);
//        makeString(1080, 1920);
//        makeString(1440, 2560);
//        makeString(1152, 1920);
    }

    public static void makeString(int w, int h) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        float cellw =w / dw;
        for (int i = 1; i < 360; i++) {
            sb.append(WTemplate.replace("{0}",i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sb.append(WTemplate.replace("{0}","360").replace("{1}", w + ""));
        sb.append("</resources>");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        float cellh =h / dh;
        for (int i = 1; i < 640; i++) {
            sb2.append(HTemplate.replace("{0}",i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sb2.append(HTemplate.replace("{0}","640").replace("{1}", h + ""));
        sb2.append("</resources>");

        String path = rootPath.replace("{0}",h + "").replace("{1}",w + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path,"lay_x.xml");
        File layyFile = new File(path,"lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            System.out.println(sb.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sb2.toString());
            System.out.println(sb2.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
}