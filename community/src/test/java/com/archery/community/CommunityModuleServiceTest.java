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
import com.archery.infranstructure.BusinessException;

class CommunityModuleServiceTest {
  private static Application application;
  private static CommunityService service;

  @BeforeAll
  static void prepare() {
    application = TestApplication.start();
    service = application.getBean("community.communityService",
        CommunityService.class);

    TestTransactional.prepare();
  }

  @AfterAll
  static void shutDown() {
    application.stop();
  }

  @BeforeEach
  private void setUp() {
    TestTransactional.deleteEntityType(Archer.class);
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

  @Test
  void registerArcher_duplicated() {
    ArcherDto newArcher = new ArcherDto()
        .email("archer@mail.com")
        .name("archer")
        .pass("secret");

    ResponseEntity response = service.registerArcher(newArcher);
    assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    assertThrows(BusinessException.class,
        () -> service.registerArcher(newArcher),
        "Cannot create Archer");
  }
}
