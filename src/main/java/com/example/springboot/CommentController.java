package com.example.springboot;

import com.example.springboot.security.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(RelationalDataAccessApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CommentService commentService;

    @RolesAllowed({"commenter", "admin"})
    @GetMapping("/comment")
    public String dbEntrys(Model model){
        model.addAttribute("comment", commentService.findAll());
        return "comment";
    }

    @RolesAllowed({"commenter"})
    @PostMapping("/comment")
    public String submitComment(HttpServletRequest servlet, Model model, Principal user){
        String test1 = user.getName();
        String test11 = servlet.getParameter("userName");
        test1 = "("+test1+") " + test11;
        String test2 = servlet.getParameter("content");
        String build =test1 + "§%&~#~~" + test2;
        List<Object[]> splitUpNames = Arrays.asList(build).stream()
                .map(name -> name.split("§%&~#~~"))
                .collect(Collectors.toList());
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
        jdbcTemplate.batchUpdate("INSERT INTO comments(userName, content) VALUES (?,?)", splitUpNames);
        model.addAttribute("comment", commentService.findAll());
        return "comment";
    }
}
