package com.an9elkiss.api.eling.command;

import lombok.Data;

@Data
public class WordTagCmd {
	private Integer word_id;
	private Integer tag_id;
	private String tag;
}
