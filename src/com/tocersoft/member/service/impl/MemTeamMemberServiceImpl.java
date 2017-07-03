package com.tocersoft.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.UUIDUtil;
import com.tocersoft.member.dao.IMemTeamMemberDao;
import com.tocersoft.member.entity.MemTeamMember;
import com.tocersoft.member.entity.Member;
import com.tocersoft.member.service.IMemTeamMemberService;

import com.tocersoft.member.dto.MemTeamMemberCondition;

@Service("memTeamMemberServiceImpl")
@Transactional(value = "transactionManager")
public class MemTeamMemberServiceImpl implements IMemTeamMemberService{

	@Resource(name = "memTeamMemberDaoImpl")
	private IMemTeamMemberDao memTeamMemberDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listMemTeamMemberByPage(PageResult<MemTeamMember> pageResult,MemTeamMemberCondition condition){
		int rows = memTeamMemberDao.listMemTeamMemberByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<MemTeamMember> list = memTeamMemberDao.listMemTeamMemberByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 团队下会员信息
	 */
	public MemTeamMember getMemTeamMemberById(String id){
		return memTeamMemberDao.getMemTeamMemberById(id);
	}

	/**
	 * 新增
	 * @param item 团队下会员信息
	 */
	public void add(MemTeamMember item){
		memTeamMemberDao.add(item);
	}
	
	
	/**
	 * 根据ID集合批量新增
	 * @param ids ID集合
	 */
	public void addTeamMember(MemTeamMember memTeamMember){
		//声明队员集合
		List<MemTeamMember> items=new ArrayList<MemTeamMember>();
		//声明一个新的对象
		MemTeamMember item=null;
		//如果有勾选队员
		if(StringUtils.isNotBlank(memTeamMember.getMemberId())){
			String[] ids=memTeamMember.getMemberId().split(",");
			
			for (String memberId : ids) {
				memberId.trim();
				item=memTeamMemberDao.getMemTeamMemberByMemberIdAndTeamId(memberId,memTeamMember.getTeamId());
				//如果这个队员不存在
				if(item==null){
					item=new MemTeamMember();
					item.setId(UUIDUtil.uuid());
					item.setMemberId(memberId);
					item.setTeamId(memTeamMember.getTeamId());
					item.setType(memTeamMember.getType());
					items.add(item);
				}else{
					item.setType(memTeamMember.getType());
					memTeamMemberDao.update(item);
				}
				
			}
			memTeamMemberDao.addTeamMember(items);
		}
		
	}

	/**
	 * 修改
	 * @param item 团队下会员信息
	 */
	public void update(MemTeamMember item){
		memTeamMemberDao.update(item);
	}
	
	/**
	 * 根据teamID删除
	 */
	public void delByTeamId(String memberId,String teamId){
		memTeamMemberDao.delByMemberIdAndTeamId(memberId,teamId);
	}


	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		memTeamMemberDao.delByIds(ids);
	}

	/**
	 * 根据TeamID查询
	 * @return 
	 */
	@Override
	public List<MemTeamMember> listMemTeamMemberByTeamId(String teamId) {
		return memTeamMemberDao.listMemTeamMemberByTeamId(teamId);
	}

}

