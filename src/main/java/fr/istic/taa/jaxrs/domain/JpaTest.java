package fr.istic.taa.jaxrs.domain;

import javax.persistence.EntityManager;
import fr.istic.taa.jaxrs.dao.generic.UserDao;


public class JpaTest {
	
	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		User user1= new User();
		user1.setEmail("user1@gmail.com");
		user1.setName("George Bill");
		
		UserDao daoUser1 = new UserDao();
		daoUser1.save(user1);
		
	}

	
	private void createUsers() {
		User user = new User();
		user.setEmail("admin@gmail.com");
		user.setName("DUPONT-DURANT");
		
		manager.persist(user);
	}

	private void listUsers() {
		/*List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
		System.out.println("num of employess:" + resultList.size());
		for (Employee next : resultList) {
			System.out.println("next employee: " + next);
		}*/
	}

}
