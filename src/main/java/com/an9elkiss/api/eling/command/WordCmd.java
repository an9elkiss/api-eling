package com.an9elkiss.api.eling.command;

import java.util.List;

import com.an9elkiss.api.eling.model.Word;

import lombok.Data;

@Data
public class WordCmd {
	private Word word;
	private List<String> tags;
}
