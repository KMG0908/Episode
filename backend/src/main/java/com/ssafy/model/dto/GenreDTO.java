package com.ssafy.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {
	private int genrePk;
	private String genreName;
	private List<NovelDTO> novelDTOs;
}
