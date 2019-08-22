package member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	private @Autowired MemberDAO dao;
	
	@Override
	public int write(MemberDTO memberDTO) {
		return dao.write(memberDTO);
	}

	@Override
	public String login(String id, String pwd) {
		return dao.login(id, pwd);
	}

	@Override
	public String isExistId(String id) {
		return dao.isExistId(id);
	}

	@Override
	public MemberDTO selectOne(String id) {
		return dao.selectOne(id);
	}

	@Override
	public int modify(MemberDTO memberDTO) {
		return dao.modify(memberDTO);
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

	@Override
	public List<MemberDTO> selectList(int startNum, int endNum) {
		return dao.selectList(startNum, endNum);
	}

	@Override
	public int getTotalMember() {
		return dao.getTotalMember();
	}

}
