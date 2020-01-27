package com.restapi.server.service;

import com.restapi.server.dao.MemberDao;
import com.restapi.server.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao){
        this.memberDao=memberDao;
    }

    @Override
    public void addMember(Member member) {
        memberDao.save(member);
    }

    @Override
    public Iterable<Member> getAllMembers() {
        return memberDao.findAll();
    }

    @Override
    public Member getMemberById(int id) {
        return memberDao.findById(id).get();
    }

    @Override
    public void deleteMemberById(int id) {

    }

    @Override
    public void updateMemberById(Member rp, int id) {

    }
}
