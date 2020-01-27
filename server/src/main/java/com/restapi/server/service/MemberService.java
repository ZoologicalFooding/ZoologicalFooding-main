package com.restapi.server.service;

import com.restapi.server.model.Member;

public interface MemberService {
    void addMember(Member member);
    Iterable<Member> getAllMembers();
    Member getMemberById(int id);
    void deleteMemberById(int id);
    void updateMemberById(Member rp,int id);
}
