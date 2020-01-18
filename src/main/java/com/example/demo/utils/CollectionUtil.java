package com.example.demo.utils;

import java.io.File;
import java.util.List;

public class CollectionUtil {

	public static boolean isNotEmpty(List<File> obj) {
		// TODO Auto-generated method stub
		if(obj!=null&&obj.size()!=0&&obj.isEmpty()) {
			return true;
		}
		return false;
	}

}
