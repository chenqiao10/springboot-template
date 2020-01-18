package com.example.demo.test01;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.deepoove.poi.XWPFTemplate;
import com.example.demo.model.TreeNode;

public class test {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		TreeNode node1=new TreeNode(1,"chenqiao","0001");
		TreeNode node7=new TreeNode(7,"chenqiao","00010001");
		TreeNode node5=new TreeNode(5,"chenqiao","000100010001");
		TreeNode node6=new TreeNode(6,"chenqiao","00010002");
		TreeNode node3=new TreeNode(2,"chenqiao","0002");
		TreeNode node4=new TreeNode(4,"chenqiao","00020001");
		List<TreeNode>   nodelist=new ArrayList<>();
		nodelist.add(node4);
		nodelist.add(node1);
		nodelist.add(node5);
		nodelist.add(node3);
		
		
		

		
	}
	public void test() throws IOException {
		XWPFTemplate template = XWPFTemplate.compile("C:\\Users\\chenqiao\\Desktop\\html-web项目\\test.docx").render(new HashMap<String, Object>() {
			{
				put("title", "Poi-tl 模板引擎");
			}
		});
		FileOutputStream out = new FileOutputStream("C:\\Users\\chenqiao\\Desktop\\html-web项目\\out_template.docx");
		template.write(out);
		out.flush();
		out.close();
		template.close();
	}
}
