package com.tocersoft.base.service.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.service.IUploadService;
import com.tocersoft.base.utils.CommonUtil;
import com.tocersoft.base.utils.UUIDUtil;

@Service("uploadServiceImpl")
@Transactional(value = "transactionManager")
public class UploadServiceImpl implements IUploadService{
	private static final Log logger = LogFactory.getLog(UploadServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject uploadHistoryImportFile(File file,String suffixName) {
		JSONObject result = new JSONObject();
		String destPath = System.getProperty("java.io.tmpdir") + File.separator + UUIDUtil.uuid() + suffixName;
		File dest = new File(destPath);
		CommonUtil.uploadFile(file, dest);
		
		logger.info("上传文件目录:"+destPath);
		FileUtils.deleteQuietly(file);
		
		result.put("path", destPath);
		result.put("fileName", file.getName());
		result.put("fileSize", 0);
		return result;
	}

	public static void main(String[] args) {
		File file = new File("D:\\123");
		
		file.mkdir();
	}

}
