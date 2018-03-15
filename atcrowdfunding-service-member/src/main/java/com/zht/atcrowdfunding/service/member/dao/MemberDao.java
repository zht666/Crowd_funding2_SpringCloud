package com.zht.atcrowdfunding.service.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zht.atcrowdfunding.common.bean.Cert;
import com.zht.atcrowdfunding.common.bean.Member;
import com.zht.atcrowdfunding.common.bean.MemberCert;
import com.zht.atcrowdfunding.common.bean.Ticket;

public interface MemberDao {

	@Select("select * from t_member where loginacct = #{loginacct}")
	Member queryMemberByLoginacct(String loginacct);

	@Select("select * from t_ticket where memberid = #{memberid} and status = '0'")
	Ticket queryTicketByMembetid(Integer id);

	void insertTicket(Ticket t);

	void updateAccoutType(Member loginMember);

	void updateStep(Ticket t);

	void updateBasicinfo(Member loginMember);

	List<Cert> queryCertsByAccoutType(String accttype);

	@Select("select * from t_member where id = #{id}")
	Member queryMemberById(Integer memberid);

	//dao里面的方法尽量不要传List、set，如果传的话用@Param注解，可以传Map、Integer、String类型
	void insertMemberCerts(@Param("mcs") List<MemberCert> mcs);

	@Update("update t_member set email = #{email} where id = #{id}")
	void updateEmail(Member loginMember);

	void updateAuthcodeAndStep(Ticket t);
	
	@Update("update t_member set authstatus = #{authstatus} where id = #{id}")
	void updateAuthstatus(Member loginMember);

	Member queryMemberByPiid(String piid);

	List<MemberCert> queryMemberCertsByMemberid(Integer memberid);

	@Update("update t_ticket set status = #{status} where id = #{id}")
	void updateTicketStatus(Ticket t);

}
