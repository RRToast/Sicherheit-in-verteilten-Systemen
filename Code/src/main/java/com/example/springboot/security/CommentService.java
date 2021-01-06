package com.example.springboot.security;

import com.example.springboot.dbEntries.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Comment> findAll() {
        List<Comment> commentList = new ArrayList<>();
        List<Map<String, Object>> haus2= jdbcTemplate.queryForList("SELECT * FROM comments;");
        System.out.println(haus2.toString());
        int id;
        String userName;
        String content;
        for(int i = 0; i < haus2.size(); i++){
            id = (int) haus2.get(i).get("ID");
            userName = (String) haus2.get(i).get("userName");
            content = (String) haus2.get(i).get("content");
            commentList.add(new Comment(id, userName, content));
            System.out.println("Comment Service : " + content);
        }
        return commentList;



//        String sql = "select * from comment";
//        RowMapper<Comment> rm = new RowMapper<Comment>() {
//            @Override
//            public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
//                Comment comment = new Comment(resultSet.getInt("id"),
//                        resultSet.getString("userName"),
//                        resultSet.getString("content"));
//                return comment;
//            }
//        };
//        System.out.println("Comment Service : " + jdbcTemplate.query(sql, rm).toString());
//
//        return jdbcTemplate.query(sql, rm);
    }


}
