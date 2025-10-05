import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.journaldev.hibernate.util.HibernateUtil;

public class Repository<T> {
	public T save(T saveMe) {
	    SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
	    Session session = sessionFactory.getCurrentSession();
	    try {
	        // Начало транзакции
	        session.beginTransaction();
	        // Сохранение объекта
	        session.save(saveMe);
	        // Коммит транзакции
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        // Откат транзакции в случае ошибки
	        if (session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        throw new RuntimeException("Ошибка при сохранении объекта: " + e.getMessage(), e);
	    } finally {
	        // Закрытие сессии
	        if (session.isOpen()) {
	            session.close();
	        }
	    }
	    return saveMe;
	}
	
	

}
