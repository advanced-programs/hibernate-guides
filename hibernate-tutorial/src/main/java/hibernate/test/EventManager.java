package hibernate.test;

import hibernate.domain.Event;
import hibernate.util.HibernateUtil;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventManager {

	private static Logger logger = LoggerFactory.getLogger(EventManager.class);

	public static void main(String[] args) {

		Event event1 = new Event();
		event1.setTitle("标题1");
		event1.setDate(new Date(System.currentTimeMillis()));
		Event event2 = new Event();
		event2.setTitle("标题2");
		event2.setDate(new Date(System.currentTimeMillis() - 86400_000));
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			logger.info("insert " + event1 + " start...");
			session.save(event1);
			logger.info("insert " + event2 + " start...");
			session.save(event2);
			logger.info("commit!");
			tx.commit();
			logger.info("insert succeed...");
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
