package jdbc;
	
import java.util.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;



public class CRUD {
	
	
	public long register(User ul) {
		Configuration cfg=new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		long i;
		
		Transaction t=session.beginTransaction();
	
		i=(long)session.save(ul);
		t.begin();
		
		
		t.commit();
		session.close();
		
		return i;
		
		
	}
	
	public int retrieve(long id,String password)
	{
		Configuration cfg=new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		
		Query q=session.createQuery("select password from jdbc.User where st_roll_no=:i");
		q.setParameter("i",id);
		
		
		t.commit();
		
		List results=q.list();
		
		if(!results.isEmpty())
		{
			if(results.contains(password))
			{
				session.close();
				return 2;
			}else
			{	session.close();
				return 1;
			}
		}
		session.close();
			return 0;
		
	}
	public boolean delete(long id)
	{
		Configuration cfg=new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		
		Query query=session.createQuery("delete * from jdbc.User where st_roll_no=:i");
		
		query.setParameter("i", id);
		query.executeUpdate();
		
		t.commit();
		session.close();
		
		return true;
	}
	
}
