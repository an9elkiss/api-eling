package com.an9elkiss.api.eling.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.eling.model.Tag;

@Mapper
public interface TagDao {


	List<Tag> findByTags(@Param("tags") List<String> tags);


}
