package com.an9elkiss.api.eling.service;

import java.util.List;

import com.an9elkiss.api.eling.command.WordCmd;
import com.an9elkiss.commons.command.ApiResponseCmd;

public interface WordService {

	ApiResponseCmd<List<WordCmd>> findByScene(String scene);

	ApiResponseCmd<Object> save(WordCmd cmd);

}
