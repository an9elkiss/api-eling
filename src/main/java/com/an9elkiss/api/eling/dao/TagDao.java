package com.an9elkiss.api.eling.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.eling.model.Tag;

@Mapper
public interface TagDao {

	int save(@Param("tag") String tag);

	List<Tag> findByTags(@Param("tags") List<String> tags);

	int saveWordTag(@Param("wordId") Integer wordId, @Param("tagId") Integer tagId);


}
