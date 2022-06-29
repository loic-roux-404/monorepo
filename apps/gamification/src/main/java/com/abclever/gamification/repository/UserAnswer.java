package com.abclever.gamification.repository;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class UserAnswer {

  @Id
  private Long id;
  private boolean correct;
  private String answer;
  private Long userId;
  private String question;


}
