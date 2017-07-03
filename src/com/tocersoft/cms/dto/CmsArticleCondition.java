package com.tocersoft.cms.dto;

import java.util.Date;

import com.tocersoft.base.dto.BaseCondition;

public class CmsArticleCondition extends BaseCondition {
	/** 栏目名称 */
	private String channelId;
	private String channelIdArray[];
	/** 大栏目名称 1.信托学院 2.信托咨询*/
	private Integer bChannelId;
	/** 文章名称 */
	private String articleName;
	/** 文章关键词 */
	private String keyword;
	/** 文章关键词 */
	private String keyword2;
	/** 文章关键词 */
	private String keyword3;
	/** 发布开始时间 */
	private Date createDateBegin;
	/** 发布结束时间 */
	private Date createDateEnd;
	/** 是否发布 0-不发布 1-发布 */
	private Integer isPublish;
	private String keyEn;
	private String keyJp;
	private String en;
	private String jp;
	/**父级channel的 parentID*/
	private String parentID;
	
	
	/**父级channel的 parentID*/
	public String getParentID() {
		return parentID;
	}
	/**父级channel的 parentID*/
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getJp() {
		return jp;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getKeyEn() {
		return keyEn;
	}

	public void setKeyEn(String keyEn) {
		this.keyEn = keyEn;
	}

	public String getKeyJp() {
		return keyJp;
	}

	public void setKeyJp(String keyJp) {
		this.keyJp = keyJp;
	}

	/** 全站搜索关键词，模糊匹配文章标题与摘要 */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public Date getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public Integer getIsPublish() {
		return isPublish;
	}
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
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

	public Integer getBChannelId() {
		return bChannelId;
	}

	public void setBChannelId(Integer channelId) {
		bChannelId = channelId;
	}

	public String[] getChannelIdArray() {
		return channelIdArray;
	}

	public void setChannelIdArray(String[] channelIdArray) {
		this.channelIdArray = channelIdArray;
	}

	
}
