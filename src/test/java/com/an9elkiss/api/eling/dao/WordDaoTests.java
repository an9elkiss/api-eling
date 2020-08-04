/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.an9elkiss.api.eling.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.an9elkiss.api.eling.ElingApiBoot;
import com.an9elkiss.api.eling.model.Word;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElingApiBoot.class })
public class WordDaoTests {

	@Autowired
	private WordDao wordDao;

	@Test
	public void testFindByScene() {

		List<Word> words = wordDao.findByScene("综合");

		Assert.assertTrue(words.size() > 0);
	}


}
