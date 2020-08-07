package com.an9elkiss.api.eling.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.eling.model.Scene;

@Mapper
public interface SceneDao {

	int save(@Param("scene") String scene);

	Scene findByScene(@Param("scene") String scene);

	int saveSceneWord(@Param("sceneId") Integer sceneId, @Param("wordId") Integer wordId);
}
