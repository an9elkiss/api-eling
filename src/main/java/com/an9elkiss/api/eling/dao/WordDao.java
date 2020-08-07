package com.an9elkiss.api.eling.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.eling.command.WordTagCmd;
import com.an9elkiss.api.eling.model.Phrase;
import com.an9elkiss.api.eling.model.Sentence;
import com.an9elkiss.api.eling.model.Word;

@Mapper
public interface WordDao {

	int save(Word word);

	Word find(Word word);

	List<Word> findByScene(String scene);

	List<WordTagCmd> findTags(@Param("wordIds") Integer[] wordIds);

	List<Phrase> findPhrases(@Param("wordIds") Integer[] wordIds);

	List<Sentence> findSentences(@Param("wordIds") Integer[] wordIds);

	int savePhrases(@Param("phrases") List<String> phrases, @Param("wordId") Integer wordId);

	int saveSentences(@Param("sentences") List<String> sentences, @Param("wordId") Integer wordId);

}
