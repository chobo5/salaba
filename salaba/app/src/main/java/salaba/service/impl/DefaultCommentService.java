package salaba.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import salaba.dao.CommentDao;
import salaba.service.CommentService;
import salaba.vo.board.Comment;

@RequiredArgsConstructor
@Service
public class DefaultCommentService implements CommentService { // 댓글

  private final CommentDao commentDao;

  @Override
  public void addComment(Comment comment){
    commentDao.addComment(comment);
  } // 댓글 작성

  @Override
  public List<Comment> list(int boardNo) {return commentDao.findAll(boardNo);} // 게시판 번호로 댓글 조회

  @Override
  public int updateComment(Comment comment) {
    return commentDao.updateComment(comment);
  } // 댓글 수정

  @Override
  public int deleteComment(int commentNo) {
    return commentDao.deleteComment(commentNo);
  } // 댓글 삭제

  @Override
  public Comment getBy(int commentNo) {
    return commentDao.findBy(commentNo);
  } // 댓글 찾기

  @Override
  public int selectCommentWriterInfo(int commentNo) {
    return commentDao.selectCommentWriterInfo(commentNo); // 댓글 회원 번호 가져오기
  }
}
