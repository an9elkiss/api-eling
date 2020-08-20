package com.an9elkiss.api.eling.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.an9elkiss.api.eling.command.WordCmd;
import com.an9elkiss.api.eling.command.WordReq;
import com.an9elkiss.api.eling.model.Word;
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

	@RequestMapping(value = "/bug/{x}", method = RequestMethod.GET)
	public ApiResponseCmd<?> f(@PathVariable("x") Integer x) {

		for (int j = 0; j < x; j++) {

			List<Word> l = new ArrayList<Word>();
			for (int i = 0; i < 10000; i++) {
				Word w = new Word();
				w.setExplanation(UUID.randomUUID().toString());
				l.add(w);
			}

			try {
				Thread.sleep(2000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ApiResponseCmd.success();
	}

}
