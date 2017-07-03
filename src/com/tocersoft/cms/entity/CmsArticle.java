package com.tocersoft.cms.entity;

import com.tocersoft.base.entity.BaseBusEntity;

/**
 * 文章管理
 * @author 欧阳明航
 * 
 */
public class CmsArticle extends BaseBusEntity {

	private static final long serialVersionUID = 9143225122750279955L;
	/** 文章标题 */
	private String name;
	/** 文章关键词 */
	private String keyword;
	/** 文章关键词 */
	private String keyword2;
	/** 文章关键词 */
	private String keyword3;
	/** 图片路径 */
	private String path;
	/** 图片保存文件名 */
	private String filename;
	/** 文章创建人(昵称) */
	private String createName;
	/** 文章摘要（ 用于首页显示 ） */
	private String summary;
	/** 文章摘要（ 用于首页显示英文 ） */
	private String summaryEn;
	/** 文章内容 */
	private String content;
	/** 栏目ID */
	private String channelId;
	/** 栏目名称 */
	private String channelName;
	/** 状态 1：系统 2：用户 */
	private Integer state;
	/** 排序 */
	private Integer sort;
	/** 阅读次数 */
	private Integer readNum;
	/** 点赞次数 */
	private Integer likeNum;
	/** 是否发布 0-不发布 1-发布 */
	private Integer isPublish;
	
	/** 文章发布人 */
	private String author;
	private String authorEn;
	private String authorJp;
	/** 文章来源 */
	private String source;
	private String annexPath;
	private String annexFilename;
	private String type;
	/**英文标题*/
	private String enName;
	/**中文标题*/
	private String jpName;
	/**英文简介*/
	private String enSummary;
	/**日文简介*/
	private String jpSummary;
	/**英文内容*/
	private String enContent;
	/**日文内容*/
	private String jpContent;
	/**英文附件*/
	private String enAnnexFilename;
	private String jpAnnexFilename;
	private String enAnnexPath;
	private String jpAnnexPath;
	
	public String getAuthorEn() {
		return authorEn;
	}
	public void setAuthorEn(String authorEn) {
		this.authorEn = authorEn;
	}
	public String getAuthorJp() {
		return authorJp;
	}
	public void setAuthorJp(String authorJp) {
		this.authorJp = authorJp;
	}
	public String getEnAnnexFilename() {
		return enAnnexFilename;
	}
	public void setEnAnnexFilename(String enAnnexFilename) {
		this.enAnnexFilename = enAnnexFilename;
	}
	public String getJpAnnexFilename() {
		return jpAnnexFilename;
	}
	public void setJpAnnexFilename(String jpAnnexFilename) {
		this.jpAnnexFilename = jpAnnexFilename;
	}
	public String getEnAnnexPath() {
		return enAnnexPath;
	}
	public void setEnAnnexPath(String enAnnexPath) {
		this.enAnnexPath = enAnnexPath;
	}
	public String getJpAnnexPath() {
		return jpAnnexPath;
	}
	public void setJpAnnexPath(String jpAnnexPath) {
		this.jpAnnexPath = jpAnnexPath;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getJpName() {
		return jpName;
	}
	public void setJpName(String jpName) {
		this.jpName = jpName;
	}
	public String getEnSummary() {
		return enSummary;
	}
	public void setEnSummary(String enSummary) {
		this.enSummary = enSummary;
	}
	public String getJpSummary() {
		return jpSummary;
	}
	public void setJpSummary(String jpSummary) {
		this.jpSummary = jpSummary;
	}
	public String getEnContent() {
		return enContent;
	}
	public void setEnContent(String enContent) {
		this.enContent = enContent;
	}
	public String getJpContent() {
		return jpContent;
	}
	public void setJpContent(String jpContent) {
		this.jpContent = jpContent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAnnexPath() {
		return annexPath;
	}
	public void setAnnexPath(String annexPath) {
		this.annexPath = annexPath;
	}
	public String getAnnexFilename() {
		return annexFilename;
	}
	public void setAnnexFilename(String annexFilename) {
		this.annexFilename = annexFilename;
	}
	public Integer getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getReadNum() {
		return readNum;
	}
	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	
	public String getSummaryEn() {
		return summaryEn;
	}
	public void setSummaryEn(String summaryEn) {
		this.summaryEn = summaryEn;
	}
	
}
