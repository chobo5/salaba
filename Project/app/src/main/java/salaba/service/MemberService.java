package salaba.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import salaba.repository.MemberRepository;
import salaba.vo.Member;
import salaba.vo.Nation;
import salaba.vo.board.Board;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

  private final MemberRepository memberRepository;

  
  public int addMember(Member member) {
    return memberRepository.addMember(member);
  }

  
  public Member selectUserInfoForLogin(String email, String password) {
    return memberRepository.selectUserInfoForLogin(email, password);
  }

  
  public Member selectUserInfoForUpdateSession(int memberNo) {
    return memberRepository.selectUserInfoForUpdateSession(memberNo);
  }

  
  public Member selectUserInfoForLogin(int no) {
    return memberRepository.selectMemberInfo(no);
  }

  
  public List<Nation> getNation() {
    return memberRepository.getNation();
  }

  
  public int updateUserInfo(Member member) {
    return memberRepository.updateUserInfo(member);
  }

  
  public int checkNickname(String nickname) {
    return memberRepository.checkNickname(nickname);
  }
////////////////
  
  public int checkEmail(String email) {
    return memberRepository.checkEmail(email);
  }

  
  public int updateMemberWithdrawal(int memberNo) {
    return memberRepository.updateMemberWithdrawal(memberNo);
  }

  
  public Member findEmail(Member member) {
    return memberRepository.findEmail(member);
  }

  
  public Member findPassword(Member member) {
    return memberRepository.findPassword(member);
  }

  
  public void changePasswordSave(Member member) {
    memberRepository.changePasswordSave(member);
  }

  
  public int checkPassword(int memberNo, String password) {
    return memberRepository.checkPassword(memberNo, password);
  }
  
  public void insertPreference(Member member) {
    memberRepository.insertPreference(member);
  }

  
  public void deletePreference(int memberNo) {
    memberRepository.deletePreference(memberNo);
  }

  
  public String boardStateCheck(Board board){ // 알람 업데이트(알람을 읽었을 경우 업데이트)
    return memberRepository.boardStateCheck(board);
  }

  
  public Member selectEmailForGoogle(String email) {
    return memberRepository.selectEmailForGoogle(email);
  }
}

