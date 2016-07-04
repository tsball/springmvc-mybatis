package com.springmvc.util;

public class ResourceFileUploadUtil{
	
	private static String _saveDirPath;

	public static String get_saveDirPath() {
		return _saveDirPath;
	}
	public static void set_saveDirPath(String _saveDirPath) {
		ResourceFileUploadUtil._saveDirPath = _saveDirPath;
	}
}
