package com.tocersoft.game.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tocersoft.base.page.PageResult;
import com.tocersoft.base.utils.DataUtil;
import com.tocersoft.base.utils.DoubleUtil;
import com.tocersoft.game.dao.IGameEnrollDao;
import com.tocersoft.game.dao.IGameEnrollTeamDao;
import com.tocersoft.game.dao.IGameItemDao;
import com.tocersoft.game.dto.GameEnrollCondition;
import com.tocersoft.game.entity.GameEnroll;
import com.tocersoft.game.entity.GameEnrollTeam;
import com.tocersoft.game.entity.GameItem;
import com.tocersoft.game.service.IGameEnrollService;
import com.tocersoft.member.dao.IMemTeamDao;
import com.tocersoft.member.dao.IMemTeamMemberDao;
import com.tocersoft.member.dao.IMemberDao;
import com.tocersoft.member.entity.MemTeam;
import com.tocersoft.member.entity.MemTeamMember;
import com.tocersoft.member.entity.Member;

@Service("gameEnrollServiceImpl")
@Transactional(value = "transactionManager")
public class GameEnrollServiceImpl implements IGameEnrollService{

	@Resource(name = "gameEnrollDaoImpl")
	private IGameEnrollDao gameEnrollDao;
	@Resource(name = "memberDaoImpl")
	private IMemberDao memberDao;
	@Resource(name = "memTeamDaoImpl")
	private IMemTeamDao memberTeamDao;
	@Resource(name = "memTeamMemberDaoImpl")
	private IMemTeamMemberDao memberTeamMemberDao;
	@Resource(name = "gameEnrollTeamDaoImpl")
	private IGameEnrollTeamDao gameEnrollTeamDao;
	@Resource(name = "gameItemDaoImpl")
	private IGameItemDao gameItemDao;

	/**
	 * 分页查询
	 * @param pageResult 分页对象
	 * @param condition 查询条件类
	 */
	public void listGameEnrollByPage(PageResult<GameEnroll> pageResult,GameEnrollCondition condition){
		int rows = gameEnrollDao.listGameEnrollByPageCount(condition);
		pageResult.setRows(rows);

		RowBounds rowBounds = new RowBounds(pageResult.getCurrentPageIndex(),pageResult.getPageSize());
		List<GameEnroll> list = gameEnrollDao.listGameEnrollByPage(rowBounds,condition);
		pageResult.setResult(list);
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 赛事报名表
	 */
	public GameEnroll getGameEnrollById(String id){
		return gameEnrollDao.getGameEnrollById(id);
	}

	/**
	 * 新增个人报名
	 * @param item 赛事报名表
	 */
	public void add(GameEnroll item){
//		if(accountExist(item.getMemberPhone())) {
//			gameEnrollDao.add(item);
//			return;
//		}
		
		if(validAccount(item.getMemberPhone()) != null) { //判断是否已存在会员。如果存在，则直接保存赛事报名信息
			gameEnrollDao.add(item);
			return;
		}
		//如果会员不存在，则新增会员 及 报名实例
		// 创建会员
		Member member = new Member();
		member.setAccount(item.getMemberPhone());
		member.setName(item.getMemberName());
		member.setMobile(item.getMemberPhone());
		// 会员信息
		member.setIdCardNo(item.getIdCardNo());
		member.setEmail(item.getMemberEmail());
		member.setEmailactive(1);// 1为未激活 XXX 修改枚举
		member.setSex(item.getSex());
		member.setNation(item.getMemberNation());
		member.setLinkName(item.getMemberLinkName());
		member.setLinkPhone(item.getMemberLinkPhone());
		member.setLinkRelation(item.getMemberLinkRelation());
		member.setHealth(item.getMemberHealth());
		member.setBloodType(item.getMemberBloodType());

		// 加密密码
		String encPwd = DataUtil.encryptionPw("111111");
		member.setPassword(encPwd);

		memberDao.add(member);
		gameEnrollDao.add(item);
		
	}
	

	/**
	 * 
	 *@param key
	 *@return Member实例，若无则返回null
	 * <pre>
	 * method description :
	 *		判断此账号关键词是否存在。若存在则返回实例，否则返回null.
	 *		当前仅在报名时存在此业务，故暂存于GameEnrollService（addEnroll & addEnrollTeam）。
	 * </pre>
	 * @author J.殷嘉健
	 * @date 2015年3月10日下午4:45:43
	 */
	private Member validAccount(String key) {
		//通过账号判断此用户是否存在
		Member m = memberDao.getMemberByAccount(key);
		if(m != null) return m;
		
		//通过移动电话判断此用户是否存在
		m = memberDao.getMemberByMobile(key);
		if(m != null) return m;
		
		return null;
	}
	
	/**
	 * 新增团队报名
	 * @param item 赛事报名表
	 * @return 
	 */
	@Transactional
	public void addEnrollTeam(GameEnrollTeam enrollTeam) {
		List<Member> memberList = new ArrayList<Member>();
		// 创建车队
		String teamName = enrollTeam.getTeamName();
		MemTeam team = new MemTeam();
		team.setName(teamName);
		team.setType(1);
		memberTeamDao.add(team);
		
		// 创建车队经理
		String memberName = enrollTeam.getTeamLeaderName();
		String memberPhone = enrollTeam.getTeamLeaderPhone();
		
		// 添加判断车队经理的电话号码是否已经存在 >>INFO modify by J.殷嘉健 2015年3月10日<< 
		Member member = validAccount(memberPhone);
		if(member == null) {
			member = new Member();
			member.setAccount(memberPhone);
			member.setName(memberName);
			member.setMobile(memberPhone);
			// 加密密码
			String encPwd = DataUtil.encryptionPw("111111");
			member.setPassword(encPwd);
			
			memberDao.add(member);
		}

		
		// 创建车队与会员关系表
		MemTeamMember teamMember = new MemTeamMember();
		teamMember.setTeamId(team.getId());
		teamMember.setMemberId(member.getId());
		// 设置为1-领队
		teamMember.setType(1);
		memberTeamMemberDao.add(teamMember);
		
		// 取得多条报名明细，依次存入数据库
		List<GameEnroll> enrollList = enrollTeam.getEnrollList();
		// 报名人数 - 默认为4
		Integer count = 4;
		// 报名总金额
		Double sumPrice = 0.0D;
		// 查询获得项目团队单价
		GameItem gameItem = gameItemDao.getGameItemById(enrollTeam.getGameItemId());
		
		Double feeTeam = gameItem.getEnrollFeeTeam();
		// 如果不填写团队单价，则取普通单价
		if(null == feeTeam){
			feeTeam = gameItem.getEnrollFee();
		}
		
		// 查询获得项目当地人单价
		Double feeLocal = gameItem.getEnrollFeeLocal();
		// 如果不填写当地人单价，则取团队单价
		if(null == feeLocal){
			feeLocal = feeTeam;
		}
		if(null != enrollList){
			// 保存报名人数
			count = enrollList.size();
			
			// 遍历报名人员明细信息
			for(GameEnroll enroll : enrollList){
//				Member mem = memberDao.getMemberByAccount(enroll.getMemberPhone()) ;
				Member mem = validAccount(enroll.getMemberPhone());
				// 修改会员判断 >>INFO modify by J.殷嘉健 2015年3月9日<< 
				if(mem == null) { //如果会员不存在
					// 将报名人员保存为会员信息
					mem = new Member();
					mem.setAccount(enroll.getMemberPhone());
					mem.setBloodType(enroll.getMemberBloodType());
					mem.setEmail(enroll.getMemberEmail());
					mem.setHealth(enroll.getMemberHealth());
					mem.setCardType(enroll.getMember().getCardType());
					mem.setIdCardNo(enroll.getMember().getIdCardNo());
					mem.setSex(enroll.getMember().getSex());
					mem.setLinkName(enroll.getMemberLinkName());
					mem.setLinkPhone(enroll.getMemberLinkPhone());
					mem.setLinkRelation(enroll.getMemberLinkRelation());
					mem.setMobile(enroll.getMemberPhone());
					mem.setName(enroll.getMemberName());
					mem.setNation(enroll.getMemberNation());
					// 加密密码
					String encPwd2 = DataUtil.encryptionPw("111111");
					mem.setPassword(encPwd2);
					memberDao.add(mem);
				}
				
				// 创建车队与会员关系表
				MemTeamMember teamMember2 = new MemTeamMember();
				teamMember2.setTeamId(team.getId());
				teamMember2.setMemberId(mem.getId());
				// 设置为0-普通会员
				teamMember2.setType(0);
				memberTeamMemberDao.add(teamMember2);
				
				// 如果是当地人，则存入当地人优惠价
				if(null != enroll.getIsLocal() && enroll.getIsLocal().intValue() == 1){
					enroll.setEnrollFee(feeLocal);
				}else{
					enroll.setEnrollFee(feeTeam);
				}
				enroll.setEnrollTeamId(enrollTeam.getId());
				enroll.setEnrollType(1);
				enroll.setGameItemId(enrollTeam.getGameItemId());
				enroll.setState(0);
				enroll.setTeamId(team.getId());
				enroll.setTeamName(team.getName());
				gameEnrollDao.add(enroll);
				
				sumPrice = DoubleUtil.sum(sumPrice, enroll.getEnrollFee());
			}
		}
		
		// 保存团队报名信息
		enrollTeam.setEnrollCount(count);
		enrollTeam.setEnrollFee(sumPrice);
		gameEnrollTeamDao.add(enrollTeam);
	}

	/**
	 * 修改
	 * @param item 赛事报名表
	 */
	public void update(GameEnroll item){
		gameEnrollDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		gameEnrollDao.delByIds(ids);
	}

	/**
	 * 根据会员ID车队ID查询，判断重复报名
	 * @param id 主键
	 * @return 赛事报名表
	 */
	@Override
	public GameEnroll getGameEnrollByMemberIdAndGameItemId(String memberId,String gameItemId) {
		return gameEnrollDao.getGameEnrollByMemberIdAndGameItemId(memberId,gameItemId);
	}

}

