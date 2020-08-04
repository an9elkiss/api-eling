package com.an9elkiss.api.eling.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Word {
	private Integer id;
	private String noun;
	private String pronoun;
	private String verb;
	private String adjective;
	private String er;
	private String est;
	private String adverb;
	private String preposition;
	private String conjunction;
	private String explanation;
}
