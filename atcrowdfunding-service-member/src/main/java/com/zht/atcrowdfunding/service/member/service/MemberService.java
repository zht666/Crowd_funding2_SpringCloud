package com.zht.atcrowdfunding.service.member.service;

import java.util.List;

import com.zht.atcrowdfunding.common.bean.Cert;
import com.zht.atcrowdfunding.common.bean.Member;
import com.zht.atcrowdfunding.common.bean.MemberCert;
import com.zht.atcrowdfunding.common.bean.Ticket;

public interface MemberService {

	Member queryMemberByLoginacct(String loginacct);

	Ticket queryTicketByMembetid(Integer id);

	void insertTicket(Ticket t);

	void updateAccoutType(Member loginMember);

	void updateStep(Ticket t);

	void updateBasicinfo(Member loginMember);

	List<Cert> queryCertsByAccoutType(String accttype);

	Member queryMemberById(Integer memberid);

	void insertMemberCerts(List<MemberCert> mcs);

	void updateEmail(Member loginMember);

	void updateAuthcodeAndStep(Ticket t);

	void updateAuthstatus(Member loginMember);

	Member queryMemberByPiid(String piid);

	List<MemberCert> queryMemberCertsByMemberid(Integer memberid);

	void updateTicketStatus(Ticket t);


}
