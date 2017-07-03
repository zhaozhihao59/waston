package com.tocersoft.base.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordUtil {

	Logger logger = Logger.getLogger(WordUtil.class.toString());

	public static final int wdFormatHtml = 8;

	public static final int wdFormatPDF = 17;

	private Dispatch doc;

	// word运行程序对象
	private ActiveXComponent word;

	// 所有word文档集合
	private Dispatch documents;

	// 选定的范围或插入点
	private Dispatch selection;

	private boolean saveOnExit = true;

	public WordUtil() {
		ComThread.InitSTA();// 线程启动
		if (word == null) {
			word = new ActiveXComponent("Word.Application");
			word.setProperty("Visible", new Variant(false)); // 不可见打开word
			word.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
		}
		if (documents == null||documents.m_pDispatch == 0)
			documents = word.getProperty("Documents").toDispatch();
	}

	/**
	 * 设置退出时参数
	 * 
	 * @param saveOnExit
	 *            boolean true-退出时保存文件，false-退出时不保存文件
	 */
	public void setSaveOnExit(boolean saveOnExit) {
		this.saveOnExit = saveOnExit;
	}

	/**
	 * 创建一个新的word文档
	 * 
	 */
	public void createNewDocument() {
		doc = Dispatch.call(documents, "Add").toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	@SuppressWarnings("deprecation")
	public Map<String, List<Integer>> getHeaders() {
		// 获取当前窗口
		Dispatch ActiveWindow = word.getProperty("ActiveWindow").toDispatch();
		// 获得当前激活的pane
		Dispatch ActivePane = Dispatch.get(ActiveWindow, "ActivePane")
				.toDispatch();
		// 获得所有页
		Dispatch pages = Dispatch.get(ActivePane, "pages").toDispatch();
		// 获得页数
		Integer pagesCount = Dispatch.get(pages, "count").toInt();
		logger.info("word page count:" + pagesCount);
		// 获得当前视图
		Dispatch View = Dispatch.get(ActivePane, "View").toDispatch();
		Map<String, List<Integer>> headers = new HashMap<String, List<Integer>>();
		for (int i = 1; i <= pagesCount; i++) {
			// 选中页眉
			Dispatch.put(View, "SeekView", "9");// 9代表页眉
			// 获得页眉
			Dispatch headerFooter = Dispatch.get(selection, "HeaderFooter")
					.toDispatch();
			Dispatch range = Dispatch.get(headerFooter, "Range").toDispatch(); // 当前选中的页眉对象
			// 获得页眉文本
			String content = Dispatch.get(range, "Text").toString(); // 获得当前页眉中的内容
			content = content.replaceAll("\\s", "");
			content = content.replaceAll("\\n", "");
			logger.info("word page " + i + " 页眉：" + content);
			List<Integer> pageNums = headers.get(content);
			if (null == pageNums) {
				pageNums = new ArrayList<Integer>();
			}
			pageNums.add(i);
			headers.put(content, pageNums);
			// 恢复视图
			Dispatch.put(View, "SeekView", new Variant(0));
			// 转到下一页
			Dispatch.call(selection, "GoTo");
		}
		return headers;
	}

	/**
	 * 打开一个已存在的文档
	 * 
	 * @param docPath
	 */
	public void openDocument(String docPath) {
		//closeDocument();
		doc = Dispatch.call(documents, "Open", docPath).toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/**
	 * 只读方式打开一个加密的文档
	 * 
	 * @param docPath-文件全名
	 * @param pwd-密码
	 */
	public void openDocumentOnlyRead(String docPath, String pwd)
			throws Exception {
		// closeDocument();
		doc = Dispatch.callN(
				documents,
				"Open",
				new Object[] { docPath, new Variant(false), new Variant(true),
						new Variant(true), pwd, "", new Variant(false) })
				.toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/**
	 * 打开一个加密的文档
	 * 
	 * @param docPath
	 * @param pwd
	 * @throws Exception
	 */
	public void openDocument(String docPath, String pwd) throws Exception {
		// closeDocument();
		doc = Dispatch.callN(
				documents,
				"Open",
				new Object[] { docPath, new Variant(false), new Variant(false),
						new Variant(true), pwd }).toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/**
	 * 从选定内容或插入点开始查找文本
	 * 
	 * @param toFindText
	 *            要查找的文本
	 * @return boolean true-查找到并选中该文本，false-未查找到文本
	 */
	@SuppressWarnings("static-access")
	public boolean find(String toFindText) {
		if (toFindText == null || toFindText.equals(""))
			return false;
		// 从selection所在位置开始查询
		Dispatch find = word.call(selection, "Find").toDispatch();
		// 设置要查找的内容
		Dispatch.put(find, "Text", toFindText);
		// 向前查找
		Dispatch.put(find, "Forward", "True");
		// 设置格式
		Dispatch.put(find, "Format", "True");
		// 大小写匹配
		Dispatch.put(find, "MatchCase", "True");
		// 全字匹配
		Dispatch.put(find, "MatchWholeWord", "false");
		// 查找并选中
		return Dispatch.call(find, "Execute").getBoolean();
	}

	/**
	 * 把选定选定内容设定为替换文本
	 * 
	 * @param toFindText
	 *            查找字符串
	 * @param newText
	 *            要替换的内容
	 * @return
	 */
	public boolean replaceText(String toFindText, String newText) {
		if (!find(toFindText))
			return false;
		Dispatch.put(selection, "Text", newText);
		return true;
	}

	/**
	 * 全局替换文本
	 * 
	 * @param toFindText
	 *            查找字符串
	 * @param newText
	 *            要替换的内容
	 */
	public void replaceAllText(String toFindText, String newText) {
		while (find(toFindText)) {
			Dispatch.put(selection, "Text", newText);
			Dispatch.call(selection, "MoveRight");
		}
	}

	/**
	 * 在当前插入点插入字符串
	 * 
	 * @param newText
	 *            要插入的新字符串
	 */
	public void insertText(String newText) {
		Dispatch.put(selection, "Text", newText);
	}

	/**
	 * 设置当前选定内容的字体
	 * 
	 * @param boldSize
	 * @param italicSize
	 * @param underLineSize
	 *            下划线
	 * @param colorSize
	 *            字体颜色
	 * @param size
	 *            字体大小
	 * @param name
	 *            字体名称
	 * @param hidden
	 *            是否隐藏
	 */
	public void setFont(boolean bold, boolean italic, boolean underLine,
			String colorSize, String size, String name, boolean hidden) {
		Dispatch font = Dispatch.get(selection, "Font").toDispatch();
		Dispatch.put(font, "Name", new Variant(name));
		Dispatch.put(font, "Bold", new Variant(bold));
		Dispatch.put(font, "Italic", new Variant(italic));
		Dispatch.put(font, "Underline", new Variant(underLine));
		Dispatch.put(font, "Color", colorSize);
		Dispatch.put(font, "Size", size);
		Dispatch.put(font, "Hidden", hidden);
	}

	/**
	 * 文件保存或另存为
	 * 
	 * @param savePath
	 *            保存或另存为路径
	 */
	public void save(String savePath) {
		Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(),"FileSaveAs", savePath);
	}

	/**
	 * 文件保存为html格式
	 * 
	 * @param savePath
	 * @param htmlPath
	 */
	public void saveAsHtml(String htmlPath) {
		Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {htmlPath, new Variant(wdFormatHtml) }, new int[1]);
	}
	
	/**
	 * 文件保存为pdf格式,如果文件已经存在，则默认删除
	 * 
	 * @param pdfPath
	 */
	public void saveAsPdf(String pdfPath) {
		File tofile = new File(pdfPath);
		if (tofile.exists()) {
			tofile.delete();
		}
		Dispatch.call(doc,//
				"SaveAs", //
				pdfPath, // FileName
				wdFormatPDF);
	}

	/**
	 * 关闭文档
	 * 
	 * @param val
	 *            0不保存修改 -1 保存修改 -2 提示是否保存修改
	 */
	public void closeDocument(int val) {
		Dispatch.call(doc, "Close", new Variant(val));// 注 是documents而不是doc
		documents = null;
		doc = null;
	}

	/**
	 * 关闭当前word文档
	 * 
	 */
	public void closeDocument() {
		if (documents != null) {
			Dispatch.call(documents, "Save");
			Dispatch.call(documents, "Close", new Variant(saveOnExit));
			documents = null;
			doc = null;
		}
	}

	public void closeDocumentWithoutSave() {
		if (documents != null) {
			Dispatch.call(documents, "Close", new Variant(false));
			documents = null;
			doc = null;
		}
	}

	/**
	 * 保存并关闭全部应用
	 * 
	 */
	public void close() {
		closeDocument(-1);
		if (word != null) {
			// Dispatch.call(word, "Quit");
			word.invoke("Quit", new Variant[] {});
			word = null;
		}
		selection = null;
		documents = null;
		ComThread.Release();// 释放com线程。根据jacob的帮助文档，com的线程回收不由java的垃圾回收器处理
	}

	/**
	 * 打印当前word文档
	 * 
	 */
	public void printFile() {
		if (doc != null) {
			Dispatch.call(doc, "PrintOut");
		}
	}

	/**
	 * 保护当前档,如果不存在, 使用expression.Protect(Type, NoReset, Password)
	 * 
	 * @param pwd
	 * @param type
	 *            WdProtectionType 常量之一(int 类型，只读)： 1-wdAllowOnlyComments 仅批注
	 *            2-wdAllowOnlyFormFields 仅填写窗体 0-wdAllowOnlyRevisions 仅修订
	 *            -1-wdNoProtection 无保护, 3-wdAllowOnlyReading 只读
	 * 
	 */
	public void protectedWord(String pwd, String type) {
		String protectionType = Dispatch.get(doc, "ProtectionType").toString();
		if (protectionType.equals("-1")) {
			Dispatch.call(doc, "Protect", Integer.parseInt(type), new Variant(
					true), pwd);
		}
	}

	/**
	 * 解除文档保护,如果存在
	 * 
	 * @param pwd
	 *            WdProtectionType 常量之一(int 类型，只读)： 1-wdAllowOnlyComments 仅批注
	 *            2-wdAllowOnlyFormFields 仅填写窗体 0-wdAllowOnlyRevisions 仅修订
	 *            -1-wdNoProtection 无保护, 3-wdAllowOnlyReading 只读
	 * 
	 */
	public void unProtectedWord(String pwd) {
		String protectionType = Dispatch.get(doc, "ProtectionType").toString();
		if (!protectionType.equals("0") && !protectionType.equals("-1")) {
			Dispatch.call(doc, "Unprotect", pwd);
		}
	}

	/**
	 * 返回文档的保护类型
	 * 
	 * @return
	 */
	public String getProtectedType() {
		return Dispatch.get(doc, "ProtectionType").toString();
	}

	/**
	 * 设置word文档安全级别
	 * 
	 * @param value
	 *            1-msoAutomationSecurityByUI 使用“安全”对话框指定的安全设置。
	 *            2-msoAutomationSecurityForceDisable
	 *            在程序打开的所有文件中禁用所有宏，而不显示任何安全提醒。 3-msoAutomationSecurityLow
	 *            启用所有宏，这是启动应用程序时的默认值。
	 */
	public void setAutomationSecurity(int value) {
		word.setProperty("AutomationSecurity", new Variant(value));
	}

	/**
	 * 在word中插入标签 labelName是标签名，labelValue是标签值
	 * 
	 * @param labelName
	 * @param labelValue
	 */
	public void insertLabelValue(String labelName, String labelValue) {

		Dispatch bookMarks = Dispatch.call(doc, "Bookmarks").toDispatch();
		boolean isExist = Dispatch.call(bookMarks, "Exists", labelName)
				.getBoolean();
		if (isExist == true) {
			Dispatch rangeItem1 = Dispatch.call(bookMarks, "Item", labelName)
					.toDispatch();
			Dispatch range1 = Dispatch.call(rangeItem1, "Range").toDispatch();
			String bookMark1Value = Dispatch.get(range1, "Text").toString();
			System.out.println("书签内容：" + bookMark1Value);
		} else {
			System.out.println("当前书签不存在,重新建立!");
			// TODO 先插入文字，再查找选中文字，再插入标签
			this.insertText(labelValue);
			// this.find(labelValue);//查找文字，并选中
			this.setFont(true, true, true, "102,92,38", "20", "", true);
			Dispatch.call(bookMarks, "Add", labelName, selection);
			Dispatch.call(bookMarks, "Hidden", labelName);
		}
	}

	/**
	 * 在word中插入标签 labelName是标签名
	 * 
	 * @param labelName
	 */
	public void insertLabel(String labelName) {

		Dispatch bookMarks = Dispatch.call(doc, "Bookmarks").toDispatch();
		boolean isExist = Dispatch.call(bookMarks, "Exists", labelName)
				.getBoolean();
		if (isExist == true) {
			System.out.println("书签已存在");
		} else {
			System.out.println("建立书签：" + labelName);
			Dispatch.call(bookMarks, "Add", labelName, selection);
		}
	}

	/**
	 * 查找书签
	 * 
	 * @param labelName
	 * @return
	 */
	public boolean findLabel(String labelName) {
		Dispatch bookMarks = Dispatch.call(doc, "Bookmarks").toDispatch();
		boolean isExist = Dispatch.call(bookMarks, "Exists", labelName)
				.getBoolean();
		if (isExist == true) {
			return true;
		} else {
			System.out.println("当前书签不存在!");
			return false;
		}
	}

	/**
	 * 模糊查找书签,并返回准确的书签名称
	 * 
	 * @param labelName
	 * @return
	 */
	public String findLabelLike(String labelName) {
		Dispatch bookMarks = Dispatch.call(doc, "Bookmarks").toDispatch();
		int count = Dispatch.get(bookMarks, "Count").getInt(); // 书签数
		Dispatch rangeItem = null;
		String lname = "";
		for (int i = 1; i <= count; i++) {
			rangeItem = Dispatch.call(bookMarks, "Item", new Variant(i))
					.toDispatch();
			lname = Dispatch.call(rangeItem, "Name").toString();// 书签名称
			if (lname.startsWith(labelName)) {// 前面匹配
				// return lname.replaceFirst(labelName, "");//返回后面值
				return lname;
			}
		}
		return "";
	}

	/**
	 * 模糊删除书签
	 * 
	 * @param labelName
	 */
	public void deleteLableLike(String labelName) {
		Dispatch bookMarks = Dispatch.call(doc, "Bookmarks").toDispatch();
		int count = Dispatch.get(bookMarks, "Count").getInt(); // 书签数
		Dispatch rangeItem = null;
		String lname = "";
		for (int i = 1; i <= count; i++) {
			rangeItem = Dispatch.call(bookMarks, "Item", new Variant(i))
					.toDispatch();
			lname = Dispatch.call(rangeItem, "Name").toString();// 书签名称
			if (lname.startsWith(labelName)) {// 前面匹配
				Dispatch.call(rangeItem, "Delete");
				count--;// 书签已被删除，书签数目和当前书签都要相应减1，否则会报错:集合找不到
				i--;
			}
		}
	}

	/**
	 * 获取书签内容
	 * 
	 * @param labelName
	 * @return
	 */
	public String getLableValue(String labelName) {
		if (this.findLabel(labelName)) {
			Dispatch bookMarks = Dispatch.call(doc, "Bookmarks").toDispatch();
			Dispatch rangeItem1 = Dispatch.call(bookMarks, "Item", labelName)
					.toDispatch();
			Dispatch range1 = Dispatch.call(rangeItem1, "Range").toDispatch();
			Dispatch font = Dispatch.get(range1, "Font").toDispatch();
			Dispatch.put(font, "Hidden", new Variant(false)); // 显示书签内容
			String bookMark1Value = Dispatch.get(range1, "Text").toString();
			System.out.println("书签内容：" + bookMark1Value);
			// font = Dispatch.get(range1, "Font").toDispatch();
			// Dispatch.put(font, "Hidden", new Variant(true)); //隐藏书签内容
			return bookMark1Value;
		}
		return "";
	}
	
	/**
	 * 增加一行
	 * @param tableIndex
	 */
	public void addRow(int tableIndex) {
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch.call(rows, "Add");
	}

	/**
	 * 在指定行前面增加一行
	 * @param tableIndex
	 * @param rowIndex
	 */
	public void addTableRow(int tableIndex, int rowIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.call(rows, "Item", new Variant(rowIndex)).toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	
	/**
	 * 在第一行前增加一行
	 * 
	 * @param tableIndex
	 */
	public void addFirstTableRow(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "First").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	
	/**
	 * 在最后一行前增加一行
	 * @param tableIndex
	 */
	public void addLastTableRow(int tableIndex) {
		// 所有表格
		Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
		// 要填充的表格
		Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
		// 表格的所有行
		Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
		Dispatch row = Dispatch.get(rows, "Last").toDispatch();
		Dispatch.call(rows, "Add", new Variant(row));
	}
	
    /** 
     * 在指定的单元格里填写数据 
     * 
     * @param tableIndex 
     * @param cellRowIdx 
     * @param cellColIdx 
     * @param txt 
     */  
    public void putTxtToCell(int tableIndex,int cellRowIdx,int cellColIdx,String txt) {  
        // 所有表格  
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();  
        // 要填充的表格  
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();  
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),new Variant(cellColIdx)).toDispatch();  
        Dispatch.call(cell, "Select");  
        Dispatch selection = Dispatch.get(word, "Selection").toDispatch(); // 输入内容需要的对象  
        Dispatch.put(selection, "Text", txt);  
    }  
}
