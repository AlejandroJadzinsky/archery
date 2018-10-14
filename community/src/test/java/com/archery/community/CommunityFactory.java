package com.archery.community;

public class CommunityFactory {

  /** Creates a new Archer instance with these attributes:
   *
   * usrName = {name}
   * email = {name}@mail.com
   * usrPass = {name}
   */
  public static Archer newArcher(final String name) {
    return new Archer(name, name + "@mail.com", name);
  }
}
