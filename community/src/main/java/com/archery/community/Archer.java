package com.archery.community;

import org.apache.commons.lang3.Validate;

/** A person that practice archery.
 *
 * An {@link Archer} may belong to an {@link Organization}, participate in
 * tournaments
 */
public class Archer implements Comparable<Archer> {
  /** A friendly name to be used in the app, never null nor empty. */
  private String usrName;
  /** The natural ID, never null nor empty. */
  private String email;

  /** Creates a new {@link Archer} instance.
   *
   * @param theUsrName the user's name, cannot be null nor empty.
   */
  public Archer(final String theUsrName) {
    Validate.notEmpty(theUsrName, "The User name cannot be null nor empty");

    usrName = theUsrName;
  }

  /** Information to identify this instance in logs or exception messages.
   *
   * @return a String, never null nor empty.
   */
  public String logInfo() {
    return usrName;
  }

  @Override
  public int compareTo(final Archer o) {
    return usrName.compareTo(o.usrName);
  }
}
