package com.archery.community;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class ArcherRepositoryTest extends TestTransactional {

  private ArcherRepository repository;

  @BeforeAll
  static void before() {
    prepare();
  }

  static void after() {
    finish();
  }

  @BeforeEach
  void setUp() {
    //clean up database
    deleteEntityType(Archer.class);
    startTransaction();

    repository = new ArcherRepository(sessionFactory);
  }

  @AfterEach
  void tearDown() {
    commitTransaction();
  }

  @Test
  void add() {
    Archer detached = CommunityFactory.newArcher("Howard.Hill");

    Long detachedId = (Long) ReflectionTestUtils.getField(detached, "id");
    assertEquals(detachedId.longValue(), 0L);

    repository.add(detached);

    Long attachedId = (Long) ReflectionTestUtils.getField(detached, "id");
    assertNotEquals(detachedId, attachedId);
  }

  @Test
  void get() {
    Archer detached = CommunityFactory.newArcher("Howard.Hill");

    persist(detached);
    refreshTransaction();

    Long id = (Long) ReflectionTestUtils.getField(detached, "id");
    Archer retrieved = repository.get(id);

    assertEquals(detached, retrieved);
  }

  @Test
  void list_all() {
    Archer howardHill = CommunityFactory.newArcher("Howard.Hill");
    Archer byronFerguson = CommunityFactory.newArcher("byron.ferguson");
    Archer legolasGreenleaf = CommunityFactory.newArcher("legolas");

    persist(howardHill, byronFerguson, legolasGreenleaf);

    List<Archer> list = repository.search("");

    assertEquals(list.size(), 3);
  }

  @Test
  void list_filtered() {
    Archer howardHill = CommunityFactory.newArcher("Howard.Hill");
    Archer byronFerguson = CommunityFactory.newArcher("byron.ferguson");
    Archer legolasGreenleaf = CommunityFactory.newArcher("legolas");

    persist(howardHill, byronFerguson, legolasGreenleaf);

    List<Archer> list = repository.search("legolas@mail.com");

    assertEquals(list.size(), 1);
    assertEquals(list.get(0), legolasGreenleaf);
  }
}
