package com.an9elkiss.api.eling.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.an9elkiss.api.eling.command.WordCmd;
import com.an9elkiss.api.eling.command.WordTagCmd;
import com.an9elkiss.api.eling.dao.SceneDao;
import com.an9elkiss.api.eling.dao.TagDao;
import com.an9elkiss.api.eling.dao.WordDao;
import com.an9elkiss.api.eling.model.Phrase;
import com.an9elkiss.api.eling.model.Scene;
import com.an9elkiss.api.eling.model.Sentence;
import com.an9elkiss.api.eling.model.Tag;
import com.an9elkiss.api.eling.model.Word;
import com.an9elkiss.commons.command.ApiResponseCmd;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class WordServiceImpl implements WordService {

	@Autowired
	private WordDao wordDao;

	@Autowired
	private TagDao tagDao;

	@Autowired
	private SceneDao sceneDao;

	@Override
	public ApiResponseCmd<List<WordCmd>> findByScene(String scene) {

		List<Word> words = wordDao.findByScene(scene);
		List<WordCmd> cmds = new ArrayList<WordCmd>();
		List<Integer> wordIds = new ArrayList<Integer>();

		for (Word word : words) {
			WordCmd cmd = new WordCmd();
			cmd.setWord(word);
			cmd.setTags(new ArrayList<String>());
			cmd.setPhrases(new ArrayList<String>());
			cmd.setSentences(new ArrayList<String>());
			cmds.add(cmd);

			wordIds.add(word.getId());
		}
		Integer[] ids = wordIds.toArray(new Integer[wordIds.size()]);

		List<WordTagCmd> wordTagCmds = wordDao.findTags(ids);

		for (WordTagCmd wordTagCmd : wordTagCmds) {
			for (WordCmd cmd : cmds) {
				if (cmd.getWord().getId().equals(wordTagCmd.getWord_id())) {
					cmd.getTags().add(wordTagCmd.getTag());
					break;
				}
			}
		}

		List<Phrase> phrases = wordDao.findPhrases(ids);

		for (Phrase phrase : phrases) {
			for (WordCmd cmd : cmds) {
				if (cmd.getWord().getId().equals(phrase.getWord_id())) {
					cmd.getPhrases().add(phrase.getPhrase());
					break;
				}
			}
		}

		List<Sentence> sentences = wordDao.findSentences(ids);

		for (Sentence sentence : sentences) {
			for (WordCmd cmd : cmds) {
				if (cmd.getWord().getId().equals(sentence.getWord_id())) {
					cmd.getSentences().add(sentence.getSentence());
					break;
				}
			}
		}

		for (WordCmd cmd : cmds) {
			if (cmd.getPhrases().size() == 0) {
				cmd.setPhrases(null);
			}

			if (cmd.getSentences().size() == 0) {
				cmd.setSentences(null);
			}
		}

		return ApiResponseCmd.success(cmds);
	}

	@Override
	@Transactional
	public ApiResponseCmd<Object> save(WordCmd cmd) {

		wordDao.save(cmd.getWord());
		Word word = wordDao.find(cmd.getWord());
		log.debug("新建word, id = {}", word.getId());

		if (cmd.getScene() != null) {

			Scene scene = sceneDao.findByScene(cmd.getScene());
			if (scene != null) {
				sceneDao.saveSceneWord(scene.getId(), word.getId());
			} else {
				sceneDao.save(cmd.getScene());
				sceneDao.saveSceneWord(sceneDao.findByScene(cmd.getScene()).getId(), word.getId());
			}
		}

		if (cmd.getTags() != null && !cmd.getTags().isEmpty()) {

			List<Tag> tags = tagDao.findByTags(cmd.getTags());
			for (String t : cmd.getTags()) {
				boolean isTagExist = false;
				for (Tag tag : tags) {
					if (tag.getTag().equals(t)) {
						tagDao.saveWordTag(word.getId(), tag.getId());
						isTagExist = true;
						break;
					}
				}

				if (isTagExist) {
					continue;
				}
				tagDao.save(t);
				tagDao.saveWordTag(word.getId(), tagDao.findByTags(Arrays.asList(t)).get(0).getId());
			}
		}

		if (cmd.getPhrases() != null && !cmd.getPhrases().isEmpty()) {
			wordDao.savePhrases(cmd.getPhrases(), word.getId());
		}

		if (cmd.getSentences() != null && !cmd.getSentences().isEmpty()) {
			wordDao.saveSentences(cmd.getSentences(), word.getId());
		}

		return ApiResponseCmd.success();
	}

}
