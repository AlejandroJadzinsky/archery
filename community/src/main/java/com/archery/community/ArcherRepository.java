package com.archery.community;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/** The {@link Archer} persistence manager.
 */
class ArcherRepository {
  private final SessionFactory sessionFactory;

  /** Creates a new {@link ArcherRepository} instance.
   *
   * @param theSessionFactory a {@link SessionFactory} implementation, cannot
   * be null.
   */
  ArcherRepository(final SessionFactory theSessionFactory) {
    sessionFactory = theSessionFactory;
  }

  /** Adds the given {@link Archer} instance to the repository.
   *
   * @param archer an {@link Archer} instance, cannot be null.
   */
  void add(final Archer archer) {
    Validate.notNull(archer, "cannot save null archer");

    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(archer);
  }

  /** Retrieves the {@link Archer} with the given id.
   *
   * @param id a valid id.
   *
   * @return an {@link Archer} instance or null if none exists.
   */
  Archer get(final long id) {
    Session session = sessionFactory.getCurrentSession();

    return session.get(Archer.class, id);
  }

  /** List the {@link Archer} instances applying the given filters. If no
   * filter is provided then all the archers in the repository are listed.
   * TODO (A.J. 2018-10-13) limit the result.
   *
   * @param email a valid email. As this attribute is unique when present
   * the result search is at most of size one.
   *
   * @return a List of {@link Archer} instances, never null but can be empty
   * if none was found.
   */
  @SuppressWarnings("unchecked")
  List<Archer> search(final String email) {
    Session session = sessionFactory.getCurrentSession();

    DetachedCriteria query = DetachedCriteria.forClass(Archer.class);
    if (StringUtils.isNotBlank(email)) {
        query.add(Restrictions.eq("email", email));
    }

    return query.getExecutableCriteria(session).list();
  }
}
