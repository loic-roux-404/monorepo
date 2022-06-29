package com.abclever.gamification.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserAnswerRepository extends ElasticsearchRepository<UserAnswer, String> {

}
