package com.archery.community;

import static org.junit.jupiter.api.Assertions.*;

import com.k2.core.Application;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.archery.community.api.model.ArcherDto;

class CommunityServiceTest {
  private static Application application;
  private static CommunityService service;

  @BeforeAll
  static void prepare() {
    application = TestApplication.start();
    service = application.getBean("community.communityService",
        CommunityService.class);
  }

  @AfterAll
  static void shutDown() {
    application.stop();
  }

  @BeforeEach
  private void setUp() {
    //clean up DB.
  }

  @Test
  void registerArcher() {
    ArcherDto newArcher = new ArcherDto()
        .email("archer@mail.com")
        .name("archer")
        .pass("secret");

    ResponseEntity response = service.registerArcher(newArcher);

    assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    assertEquals(response.getStatusCodeValue(), 201);
  }
}
