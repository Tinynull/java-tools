package info.zhaoliang.poitl;

import com.deepoove.poi.XWPFTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author zhaoliang
 * @create 2018-06-27
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        XWPFTemplate template = XWPFTemplate
                .compile("/Users/zhaoliang/github/java-tools/poitl/src/main/resources/templeate.docx")
                .render(new HashMap<String, Object>() {{
                    put("title", "阿灿");
                    put("time", new Date().toLocaleString());
                    put("content", "我觉得这个方式非常好，very good");
                }});
        FileOutputStream out = new FileOutputStream("out_template.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();

    }
}
