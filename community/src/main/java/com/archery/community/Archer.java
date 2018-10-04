package com.archery.community;

import org.apache.commons.lang3.Validate;

/** A person that practice archery.
 *
 * An {@link Archer} may belong to an {@link Organization}, participate in
 * tournaments
 */
public class Archer implements Comparable<Archer> {
  /** A friendly name to be used in the app, never null nor empty. */
  private String name;
  /** The natural ID and subject credential, never null nor empty. */
  private String email;
  /** The subject password, never null nor empty. */
  private String pass;

  /** Creates a new {@link Archer} instance.
   *
   * @param theName the user's name, cannot be null nor empty.
   * @param theEmail the user's email, cannot be mull nor empty.
   * @param thePass the user's password, cannot be null nor empty.
   */
  Archer(final String theName, final String theEmail, final String thePass) {
    Validate.notEmpty(theName, "The name cannot be null nor empty");
    Validate.notEmpty(theEmail, "The email cannot be null nor empty");
    Validate.notEmpty(thePass, "The password cannot be null nor empty");

    name = theName;
    email = theEmail;
    pass = thePass;
  }

  /** Information to identify this instance in logs or exception messages.
   *
   * @return a String, never null nor empty.
   */
  public String logInfo() {
    return email;
  }

  @Override
  public int compareTo(final Archer o) {
    return name.compareTo(o.name);
  }
}
