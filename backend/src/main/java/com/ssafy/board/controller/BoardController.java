package com.ssafy.board.controller;


import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.board.dto.BoardListDto;
import com.ssafy.board.service.EmailService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/api/board")
@Tag(name = "게시판 컨트롤러", description = "게시판에 글을 등록, 수정, 삭제, 목록, 상세보기등 전반적인 처리를 하는 클래스.")
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final EmailService emailService;

    public BoardController(BoardService boardService, EmailService emailService) {
        super();
        this.boardService = boardService;
        this.emailService = emailService;
    }

    @Operation(summary = "게시판 글작성", description = "새로운 게시글 정보를 입력한다.")
    @PostMapping
    public ResponseEntity<?> writeArticle(
            @RequestBody @Parameter(description = "작성글 정보.", required = true) BoardDto boardDto) {
        //log.info("writeArticle boardDto - {}", boardDto);
        try {
            boardService.writeArticle(boardDto);
//			return ResponseEntity.ok().build();
            sendEmail(boardDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글목록", description = "모든 게시글의 정보를 반환한다.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "게시글목록 OK!!"),
            @ApiResponse(responseCode = "404", description = "페이지없어!!"),
            @ApiResponse(responseCode = "500", description = "서버에러!!") })
    @GetMapping
    public ResponseEntity<?> listArticle(
            @RequestParam @Parameter(description = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map) {
        //log.info("listArticle map - {}", map);
        try {
            BoardListDto boardListDto = boardService.listArticle(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(boardListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @Operation(summary = "게시판 글보기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    @GetMapping("/{articleno}")
    public ResponseEntity<BoardDto> getArticle(
            @PathVariable("articleno") @Parameter(name = "articleno", description = "얻어올 글의 글번호.", required = true) int articleno)
            throws Exception {
        //log.info("getArticle - 호출 : " + articleno);
        boardService.updateHit(articleno);
        return new ResponseEntity<BoardDto>(boardService.getArticle(articleno), HttpStatus.OK);
    }

    @Operation(summary = "수정 할 글 얻기", description = "글번호에 해당하는 게시글의 정보를 반환한다.")
    @GetMapping("/modify/{articleno}")
    public ResponseEntity<BoardDto> getModifyArticle(
            @PathVariable("articleno") @Parameter(name = "articleno", description = "얻어올 글의 글번호.", required = true) int articleno)
            throws Exception {
        //log.info("getModifyArticle - 호출 : " + articleno);
        return new ResponseEntity<BoardDto>(boardService.getArticle(articleno), HttpStatus.OK);
    }

    @Operation(summary = "게시판 글수정", description = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    @PutMapping
    public ResponseEntity<String> modifyArticle(
            @RequestBody @Parameter(description = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
        //log.info("modifyArticle - 호출 {}", boardDto);

        boardService.modifyArticle(boardDto);
        sendEmail(boardDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시판 글삭제", description = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.")
    @DeleteMapping("/{articleno}")
    public ResponseEntity<String> deleteArticle(@PathVariable("articleno") @Parameter(name = "articleno", description = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
        //log.info("deleteArticle - 호출");
        boardService.deleteArticle(articleno);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendEmail(BoardDto boardDto) {
        String to = "syt05342@gmail.com";  // 이메일 수신자 (변경 가능)
        String subject = "게시글 작성/수정 알림: " + boardDto.getSubject();
        String content = "작성자: " + boardDto.getUserName() + "\n"
                + "제목: " + boardDto.getSubject() + "\n"
                + "내용: " + boardDto.getContent();

        emailService.sendEmail(to, subject, content);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BoardDto>> getInquiriesByUser(@PathVariable String userId) {
        List<BoardDto> boards = boardService.getBoardsByUser(userId);
        return ResponseEntity.ok(boards);
    }
}