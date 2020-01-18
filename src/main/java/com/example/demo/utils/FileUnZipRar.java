package com.example.demo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileUnZipRar {

	public static void readZipFile(String file) throws Exception {
		ZipFile zf = new ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			if (ze.isDirectory()) {
			} else {
				if (ze.getName().contains(".wmf")) {
					ze.getName().lastIndexOf(".wmf");
				}
				System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
				long size = ze.getSize();
				if (size > 0) {
					BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
					String line;
					while ((line = br.readLine()) != null) {
				byte[]	b=	line.getBytes();
				
						System.out.println(line);
					}
					br.close();
				}
				System.out.println();
			}
		}
		zin.closeEntry();
	}

	public static void main(String[] args) throws Exception {
		// readZipFile("C:\\Users\\chenqiao\\Desktop\\html-web项目\\word2html\\test.docx");
		String str = "imange.wmf";
		str=str.substring(0, str.lastIndexOf(".wmf"))+".png";
		System.out.println(str);
	}
}
