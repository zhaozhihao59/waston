package com.tocersoft.base.service;

import java.io.File;

import org.json.simple.JSONObject;

public interface IUploadService {

	/**
	 * 上传历史数据导入临时文件
	 * @param file
	 * @return
	 */
	JSONObject uploadHistoryImportFile(File file,String suffixName);
	
}
