package com.an9elkiss.api.eling.command;

import java.util.List;

import com.an9elkiss.api.eling.model.Word;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WordCmd {
	private Word word;
	private String scene;
	private List<String> tags;
	private List<String> phrases;
	private List<String> sentences;
}
