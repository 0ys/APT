package com.ssafy.board.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(title = "BoardDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class BoardDto {

	@Schema(description = "글번호")
	private int articleNo;
	@Schema(description = "작성자 아이디")
	private String userId;
	@Schema(description = "작성자 이름")
	private String userName;
	@Schema(description = "글제목")
	private String subject;
	@Schema(description = "글내용")
	private String content;
	@Schema(description = "조회수")
	private int hit;
	@Schema(description = "작성일")	
	private String registerTime;
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Schema(description = "업로드 파일정보")
	private List<FileInfoDto> fileInfos;

}
