package salaba.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import salaba.vo.Alarm;
import salaba.vo.Member;
import salaba.vo.Nation;
import salaba.vo.board.Board;


@Mapper
public interface MemberDao {

  int addMember(Member member);

  Member selectMemberInfo(int no);

  int updateUserInfo(Member member);

  int checkNickname(String nickname);

  int checkEmail(String email);

  List<Nation> getNation();

  int updateMemberWithdrawal(@Param("memberNo") int memberNo);

  Member selectUserInfoForLogin(
      @Param("email") String email,
      @Param("password") String password);

  Member selectUserInfoForUpdateSession(@Param("memberNo") int memberNo);

  Member findEmail(Member member);

  public Member findPassword(Member member);

  public void changePasswordSave(Member member);

  public int checkPassword(@Param("no") int memberNo, @Param("password") String password);

  void insertPreference(Member member);

  void deletePreference(@Param("no") int memberNo);

  List<Member> findAllTheme(Member sessionInfo);

  int getMemberPoint(int no);

  Member getGrade(Member member);

  List<Member> findAllmyTheme(Member sessionInfo);

  void addNotifyHistory(Alarm alarm);

  List<Alarm> selectNotifyHistory(int memberNo);

  void updateNotifyHistory(char state, int notifyNo);

  String boardStateCheck(Board board);

  List<Board> searchByKeyword(@Param("keyword") String keyword, @Param("type") String type); // 검색

  int countFiltered(String type, String keyword); // 검색으로 필터링해 페이징 처리
}
