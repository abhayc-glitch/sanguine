package com.server.backend.models;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

public class Entry {

  // An Entry is where the user can do a check in, and describe there feelings Similar ot a post.java

  @NotEmpty(message = "Name may not be empty")
  private String title;

  private String textContent;

  private Long postId;

  private int mood;

  private Instant createdDate;


  public Entry(String title, String textContent, Long postId, int mood, Instant createdDate) {
    this.title = title;
    this.textContent = textContent;
    this.postId = postId;
    this.mood = mood;
    this.createdDate = createdDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    title = this.title;
  }

  public String getTextContent() {
    return textContent;
  }

  public void setTextContent(String textContent) {
    this.textContent = textContent;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public int getMood() {
    return mood;
  }

  public void setMood(int mood) {
    this.mood = mood;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }
}
