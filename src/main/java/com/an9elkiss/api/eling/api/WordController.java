package com.an9elkiss.api.eling.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.an9elkiss.api.eling.command.WordCmd;
import com.an9elkiss.api.eling.command.WordReq;
import com.an9elkiss.api.eling.service.WordService;
import com.an9elkiss.commons.command.ApiResponseCmd;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class WordController {

	@Autowired
	private WordService wordService;

	@RequestMapping(value = "/word/find", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> find(@RequestBody WordReq cmd) {

		return wordService.findByScene(cmd.getScene());
    }

	@RequestMapping(value = "/word/save", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> save(@RequestBody WordCmd cmd) {

		return wordService.save(cmd);
	}
}
