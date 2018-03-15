package com.zht.atcrowdfunding.service.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zht.atcrowdfunding.common.bean.Cert;
import com.zht.atcrowdfunding.common.bean.Member;
import com.zht.atcrowdfunding.common.bean.MemberCert;
import com.zht.atcrowdfunding.common.bean.Ticket;
import com.zht.atcrowdfunding.service.member.dao.MemberDao;
import com.zht.atcrowdfunding.service.member.service.MemberService;

@Service
@Transactional(readOnly=true)
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public Member queryMemberByLoginacct(String loginacct) {
		return memberDao.queryMemberByLoginacct(loginacct);
	}

	@Override
	public Ticket queryTicketByMembetid(Integer id) {
		return memberDao.queryTicketByMembetid(id);
	}

	@Transactional
	public void insertTicket(Ticket t) {
		memberDao.insertTicket(t);
	}

	@Transactional
	public void updateAccoutType(Member loginMember) {
		memberDao.updateAccoutType(loginMember);
	}

	@Transactional
	public void updateStep(Ticket t) {
		memberDao.updateStep(t);
	}

	@Transactional
	public void updateBasicinfo(Member loginMember) {
		memberDao.updateBasicinfo(loginMember);
	}

	@Override
	public List<Cert> queryCertsByAccoutType(String accttype) {
		return memberDao.queryCertsByAccoutType(accttype);
	}

	@Override
	public Member queryMemberById(Integer memberid) {
		return memberDao.queryMemberById(memberid);
	}

	@Transactional
	public void insertMemberCerts(List<MemberCert> mcs) {
		memberDao.insertMemberCerts(mcs);
	}

	@Transactional
	public void updateEmail(Member loginMember) {
		memberDao.updateEmail(loginMember);
	}

	@Transactional
	public void updateAuthcodeAndStep(Ticket t) {
		memberDao.updateAuthcodeAndStep(t);
	}

	@Transactional
	public void updateAuthstatus(Member loginMember) {
		memberDao.updateAuthstatus(loginMember);
	}

	@Override
	public Member queryMemberByPiid(String piid) {
		return memberDao.queryMemberByPiid(piid);
	}

	@Override
	public List<MemberCert> queryMemberCertsByMemberid(Integer memberid) {
		return memberDao.queryMemberCertsByMemberid(memberid);
	}

	@Transactional
	public void updateTicketStatus(Ticket t) {
		memberDao.updateTicketStatus(t);
	}
}
