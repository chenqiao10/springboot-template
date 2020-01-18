package com.example.demo.test01;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import sun.misc.BASE64Decoder;
public class FreeMarkerDoc {
	private String templateName = null;
	private String templatePath = null;
	private Configuration configuration = null;
        public FreeMarkerDoc() {
	    configuration = new Configuration(Configuration.VERSION_2_3_21);
	    configuration.setDefaultEncoding("utf-8");
	}
	public void generateDoc(Map<String,Object> dataMap, String fileName) throws IOException, TemplateException {
        Template tempalte = null;
        configuration.setClassForTemplateLoading(this.getClass(), "/com/freemark/template");
        //setServletContextForTemplateLoading(context, "/mht")  /WebRoot/ftlç›®å½•ã€‚
        //configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Desktop\\test"));
        ///tempalte.setEncoding("utf-8");
        tempalte = configuration.getTemplate("index.ftl");
	    File outFile = new File(fileName);
	    Writer out = null;
	    FileOutputStream fos = null;
        OutputStreamWriter oWriter = null;
        try {
            fos = new FileOutputStream(outFile);
            oWriter = new OutputStreamWriter(fos, "UTF-8");
            out = new BufferedWriter(oWriter);
            tempalte.process(dataMap, out);
        } finally {
            if(null != out){
                out.close();
            }
            if(null != oWriter){
                oWriter.close();
            }
            if(null != fos){
                fos.close();
            }
        }
        
        

	}
	  static String getImageStr() {
         String imgFile = "C:\\Users\\chenqiao\\Desktop\\html-web项目\\10.png";
         InputStream in = null;
         byte[] data = null;
         try {
             in = new FileInputStream(imgFile);
             data = new byte[in.available()];
             in.read(data);
             in.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
         final Base64.Encoder encoder = Base64.getEncoder();
         return encoder.encodeToString(data);
     }

	public static void main(String[] args) throws IOException, TemplateException {
		FreeMarkerDoc t=new FreeMarkerDoc();
		Map<String,Object> m=new HashMap<String, Object>();
		 
		m.put("img", getImageStr());
		t.generateDoc(m,  "C:\\Users\\chenqiao\\Desktop\\html-web项目\\test.docx");
	}
}
