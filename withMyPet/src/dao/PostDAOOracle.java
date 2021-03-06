package dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vo.Blacklist;
import vo.Media_tag;
import vo.Member;
import vo.Post;
import vo.Reply;
import vo.Report;
import exception.AddException;

@Repository("postDAO")
public class PostDAOOracle implements PostDAO {
   @Autowired
   private SqlSession session;
   
   @Override
   public void boardInsertPost(Post post, Media_tag media_tag) throws AddException{
      System.out.println("in PostDAOOracle boardInsertPost");      
      session.insert("BoardMapper.insertPost", post);
      media_tag.setTag_name("null");
      if(media_tag.getImg() == null){
         media_tag.setImg("null");
      }
      if(media_tag.getVideo() == null){
         media_tag.setVideo("null");
      }      
      session.insert("BoardMapper.insertMedia", media_tag);
   }
   
   @Override
   public ArrayList<Post> boardSelectAll(int cntPerPage, int currentPage) throws Exception{
      System.out.println("in PostDAOOracle selectAll(int cntPerPage, int currentPage)");
      ArrayList<Post> posts = new ArrayList<Post>();
      HashMap<String, Integer>map = new HashMap<String, Integer>();
      map.put("currentPage", currentPage);
      map.put("cntPerPage", cntPerPage);
      posts = (ArrayList)session.selectList("BoardMapper.selectAllPage", map);
      return posts;   
   }
   
   @Override
   public ArrayList<Post> boardSelectByEmail(String value) throws Exception{
      System.out.println("in PostDAOOracle selectByEmail(String email)");
      ArrayList<Post> posts = new ArrayList<Post>();
      posts = (ArrayList)session.selectList("BoardMapper.selectByEmail", value);
      return posts;
   }

   @Override
   public int getTotalCnt() throws Exception{
      System.out.println("in PostDAOOracle getTotalCnt()");
      return session.selectOne("BoardMapper.getTotalCnt");
   }

   @Override
   public ArrayList<Post> boardSelectBySubject(String value) throws Exception{
      System.out.println("in PostDAOOracle selectBySubject(String value)");
      ArrayList<Post> posts = new ArrayList<Post>();
      posts = (ArrayList)session.selectList("BoardMapper.selectBySubject", value);
      return posts;
   }

   @Override
   public Post boardPostDetail(int post_no) throws Exception{
      System.out.println("in PostDAOOracle postDetail(String post_no)");
      Post post = new Post();
      post = session.selectOne("BoardMapper.postDetail", post_no);
      return post;
   }
   
   @Override
   public Media_tag boardMedia_tagDetail(int post_no) throws Exception{
      System.out.println("in PostDAOOracle media_tagDetail(String post_no)");
      Media_tag media_tag = new Media_tag();
      media_tag = session.selectOne("BoardMapper.media_tagDetail", post_no);
      return media_tag;
   }
   
   @Override
   public void boardPostLikeCount(Post post) throws Exception{
      System.out.println("in PostDAOOracle likeCount()");
      session.update("BoardMapper.likeCount", post);      
   }
   
   @Override
   public void boardPostHateCount(Post post) throws Exception{
      System.out.println("in PostDAOOracle hateCount()");
      session.update("BoardMapper.hateCount", post);      
   }
   
   @Override
   public void insertReply(Reply reply) throws AddException{
      System.out.println("in PostDAOOracle insertReply(Reply reply)");
      session.insert("BoardMapper.insertReply", reply);      
   }
   
   @Override
   public ArrayList<Reply> selectReply(int post_no) throws Exception{
      System.out.println("in PostDAOOracle selectReply(Reply reply)");
      ArrayList<Reply> replys = new ArrayList<Reply>();
      replys = (ArrayList)session.selectList("BoardMapper.selectReply", post_no);
      return replys;      
   }
   
   @Override
   public void replyLikeCount(Reply reply) throws Exception{
      System.out.println("in PostDAOOracle replyLikeCount()");
      session.update("BoardMapper.replyLikeCount", reply);      
   }

   @Override
   public int selectReplyLikeCount(Reply reply) {
      System.out.println("in PostDAOOracle selectReplyLikeCount()");
      int like_count = session.selectOne("BoardMapper.selectReplyLikeCount", reply);
      return like_count;
   }
   
   @Override
   public void report(Report report) {
      System.out.println("in PostDAOOracle report()");
      session.insert("BoardMapper.report", report);
   }
   
   @Override
   public int reportCount(Report report) {
      System.out.println("in PostDAOOracle reportCount()");
      int report_count = session.selectOne("BoardMapper.reportCount", report);
      return report_count;
   }
   
   @Override
   public void insertBlackList(Blacklist blacklist) {
      System.out.println("in PostDAOOracle insertBlackList()");
      session.insert("BoardMapper.insertBlackList", blacklist);
   }
   
   @Override
   public void deleteBlackList(String e_mail) {
      System.out.println("in PostDAOOracle deleteBlackList()");
      session.delete("BoardMapper.deleteBlackList", e_mail);
   }
   
   @Override
   public void updateBlFlag(Member member) {
      System.out.println("in PostDAOOracle updateBlFlag()");
      session.update("BoardMapper.updateBlFlag", member);
   }

   @Override
   public Member selectByMember(String reported_email) {
      System.out.println("in PostDAOOracle selectByMember()");
      Member member = new Member();
      member = session.selectOne("BoardMapper.selectByMember", reported_email);
      return member;
   }
   
   @Override
   public Blacklist selectByBlacklist(String e_mail) {
      System.out.println("in PostDAOOracle selectByBlacklist()");
      Blacklist blacklist= new Blacklist();
      blacklist = session.selectOne("BoardMapper.selectByBlacklist", e_mail);
      return blacklist;
   }
   
   @Override
   public void boardDelete(int post_no) {
      System.out.println("in PostDAOOracle boardDelete()");
      session.delete("BoardMapper.boardDelete", post_no);
   }
   
   @Override
   public void boardReplyDelete(Reply reply) {
      System.out.println("in PostDAOOracle boardReplyDelete()");
      session.delete("BoardMapper.boardReplyDelete", reply);
   }
   
   @Override
   public void boardPostUpdate(Post post) {
      System.out.println("in PostDAOOracle boardPostUpdate()");
      session.update("BoardMapper.boardPostSubjectUpdate", post);
      session.update("BoardMapper.boardPostMsgUpdate", post);
   }
   
   //------------------------아래부터 타임라인
   
   @Override
   public ArrayList<Post> TimelineSelectAll() throws Exception{
      ArrayList<Post> posts = new ArrayList<Post>();
      posts = (ArrayList)session.selectList("TimelineMapper.selectAll");
      return posts;
   }
   
   @Override
   public ArrayList<Media_tag> TimelineSelectAllMedia_tag() throws Exception{
      ArrayList<Media_tag> media_tags = new ArrayList<Media_tag>();
      media_tags = (ArrayList)session.selectList("TimelineMapper.selectAllMedia_tag");
      return media_tags;
   }
   
   @Override
   public void timelineInsertPost(Post post, Media_tag media_tag) throws AddException{
      System.out.println("in PostDAOOracle boardInsertPost");      
      post.setSubject("null");
      session.insert("TimelineMapper.insertPost", post);
      if(media_tag.getImg() == null){
         media_tag.setImg("null");
      }
      if(media_tag.getTag_name() == null){
         media_tag.setTag_name("null");
      }
      if(media_tag.getVideo() == null){
         media_tag.setVideo("null");
      }      
      session.insert("TimelineMapper.insertMedia", media_tag);
   }
}