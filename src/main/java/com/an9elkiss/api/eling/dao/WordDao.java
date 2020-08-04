package com.an9elkiss.api.eling.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.eling.command.WordTagCmd;
import com.an9elkiss.api.eling.model.Word;

@Mapper
public interface WordDao {

	List<Word> findByScene(String scene);

	List<WordTagCmd> findTags(@Param("wordIds") Integer[] wordIds);

}
