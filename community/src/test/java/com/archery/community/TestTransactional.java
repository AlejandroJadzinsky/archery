package com.archery.community;

import com.k2.core.Application;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public abstract class TestTransactional {
  private static Application application;
  protected static SessionFactory sessionFactory;
  protected static PlatformTransactionManager txManager;

  private TransactionStatus txStatus;

  public static void prepare() {
    application = TestApplication.start();
    sessionFactory = application.getBean("hibernate.sessionFactory",
        SessionFactory.class);
    txManager = application.getBean("hibernate.transactionManager",
        PlatformTransactionManager.class);
  }

  public static void finish() {
    if (application != null) {
      application.stop();
    }
  }

  protected void refreshTransaction() {
    commitTransaction();
    startTransaction();
  }

  protected void commitTransaction() {
    if (txStatus != null && !txStatus.isCompleted()) {
      txManager.commit(txStatus);
    }
  }

  protected void startTransaction() {
    txStatus = txManager.getTransaction(new DefaultTransactionDefinition());
  }

  public static void deleteEntityType(final Class<?> entityType) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();

    List<?> entities = DetachedCriteria.forClass(
        entityType).getExecutableCriteria(session).list();
    for (Object entity : entities) {
      session.delete(entity);
    }

    transaction.commit();
    session.disconnect();
  }

  public static void persist(final Object... entity) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();

    Arrays.stream(entity).forEach(session::save);

    transaction.commit();
    session.disconnect();
  }

  public static <T> T retrieve(final Class<T> clazz, final Serializable id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();

    T result = session.get(clazz, id);

    transaction.commit();
    session.disconnect();

    return result;
  }
}
