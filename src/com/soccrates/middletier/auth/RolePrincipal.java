package com.soccrates.middletier.auth;

import java.security.Principal;

// TODO: Auto-generated Javadoc
/**
 * The Class RolePrincipal.
 */
public class RolePrincipal implements Principal {
  
  /** The name. */
  private String name;
  
  /**
   * Instantiates a new role principal.
   *
   * @param name the name
   */
  public RolePrincipal(String name) {
    super();
    this.name = name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /* (non-Javadoc)
   * @see java.security.Principal#getName()
   */
  @Override
  public String getName() {
    return name;
  }

}

