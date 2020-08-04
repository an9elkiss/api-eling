package com.an9elkiss.api.eling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.eling.command.WordCmd;
import com.an9elkiss.api.eling.command.WordTagCmd;
import com.an9elkiss.api.eling.dao.WordDao;
import com.an9elkiss.api.eling.model.Word;
import com.an9elkiss.commons.command.ApiResponseCmd;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class WordServiceImpl implements WordService {

	@Autowired
	private WordDao wordDao;


	@Override
	public ApiResponseCmd<List<WordCmd>> findByScene(String scene) {

		List<Word> words = wordDao.findByScene(scene);
		List<WordCmd> cmds = new ArrayList<WordCmd>();
		List<Integer> wordIds = new ArrayList<Integer>();

		for (Word word : words) {
			WordCmd cmd = new WordCmd();
			cmd.setWord(word);
			cmd.setTags(new ArrayList<String>());
			cmds.add(cmd);

			wordIds.add(word.getId());
		}

		List<WordTagCmd> wordTagCmds = wordDao.findTags(wordIds.toArray(new Integer[wordIds.size()]));

		for (WordTagCmd wordTagCmd : wordTagCmds) {
			for (WordCmd cmd : cmds) {
				if (cmd.getWord().getId().equals(wordTagCmd.getWord_id())) {
					cmd.getTags().add(wordTagCmd.getTag());
					break;
				}
			}
		}

		return ApiResponseCmd.success(cmds);
	}



}
