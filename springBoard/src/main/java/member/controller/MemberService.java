package member.controller;

import java.util.List;

import member.bean.MemberDTO;

public interface MemberService {
	public int write(MemberDTO memberDTO);
	public String login(String id, String pwd);
	public String isExistId(String id);
	public MemberDTO selectOne(String id);
	public int modify(MemberDTO memberDTO);
	public int delete(String id);
	public List<MemberDTO> selectList(int startNum, int endNum);
	public int getTotalMember();
}
