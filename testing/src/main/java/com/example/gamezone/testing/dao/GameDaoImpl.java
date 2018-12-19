package com.example.gamezone.testing.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.gamezone.testing.bean.Chat;
import com.example.gamezone.testing.bean.ExtraDetails;
import com.example.gamezone.testing.bean.FavouriteTeam;
import com.example.gamezone.testing.bean.Notification;
import com.example.gamezone.testing.bean.SignIn;
import com.example.gamezone.testing.entity.ChatEntity;
import com.example.gamezone.testing.entity.FavouriteTeamEntity;
import com.example.gamezone.testing.entity.NotificationEntity;
import com.example.gamezone.testing.entity.SignInEntity;

@Repository("employeeDao")
public class GameDaoImpl implements GameDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String signIn(SignIn signIn) {
		System.out.println("saving...");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		SignInEntity signInEntity = new SignInEntity();
		signInEntity.setEmail(signIn.getEmail());
		signInEntity.setId(signIn.getId());
		signInEntity.setImage(signIn.getImage());
		signInEntity.setName(signIn.getName());
		signInEntity.setProvider(signIn.getProvider());
		signInEntity.setToken(signIn.getToken());
		signInEntity.setSecondPage("false");
		signInEntity.setBlocked("false");
		session.persist(signInEntity);
		tx.commit();
		session.close();
		return "Success";
	}

	@Override
	public SignIn getSignInById(String Id) {
		System.out.println("getting...");
		Session session = this.sessionFactory.openSession();
		Query<SignInEntity> query = (Query<SignInEntity>) session.createQuery("from SignInEntity where id = " + Id);
		List<SignInEntity> empList = query.list();
		if (empList.size() == 0) {
			return null;
		}
		SignIn signIn = new SignIn();
		empList.forEach((i) -> {
			signIn.setEmail(i.getEmail());
			signIn.setId(i.getId());
			signIn.setImage(i.getImage());
			signIn.setName(i.getName());
			signIn.setProvider(i.getProvider());
			signIn.setToken(i.getToken());

			signIn.setCity(i.getCity());
			signIn.setCountry(i.getCountry());
			signIn.setSecondPage(i.getSecondPage());
			signIn.setState(i.getState());
			signIn.setPhone(i.getPhone());
			signIn.setBlocked(i.getBlocked());
		});
		session.close();
		return signIn;
	}

	@Override
	@Transactional
	public int extraDetails(ExtraDetails eDetails) {
		System.out.println("extra...");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// Query<SignInEntity> query = (Query<SignInEntity>)
		// entityManager.createQuery("from SignInEntity where id =
		// "+eDetails.getQwerty());
		Query query = session.createQuery(
				"update SignInEntity set city = :city, state = :state, country = :country, phone = :phone, secondPage = :secondPage where id = :id");
		query.setParameter("city", eDetails.getCity());
		query.setParameter("state", eDetails.getState());
		query.setParameter("country", eDetails.getCountry());
		query.setParameter("phone", eDetails.getPhone());
		query.setParameter("secondPage", "true");
		query.setParameter("id", eDetails.getQwerty());
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println(result + " rows updated");
		return result;
	}

	@Override
	public List<SignIn> getAll() {
		Session session = null;
		session = sessionFactory.openSession();
		List<SignIn> l = new ArrayList<>();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SignInEntity> criteriaQuery = builder.createQuery(SignInEntity.class);
		Root<SignInEntity> root = criteriaQuery.from(SignInEntity.class);
		criteriaQuery.select(root);
		List<SignInEntity> empList = session.createQuery(criteriaQuery).list();

		empList.forEach((i) -> {
			SignIn signIn = new SignIn();
			// System.out.println(i.toString());
			signIn.setEmail(i.getEmail());
			signIn.setId(i.getId());
			signIn.setImage(i.getImage());
			signIn.setName(i.getName());
			signIn.setProvider(i.getProvider());
			signIn.setToken(i.getToken());

			signIn.setCity(i.getCity());
			signIn.setCountry(i.getCountry());
			signIn.setSecondPage(i.getSecondPage());
			signIn.setState(i.getState());
			signIn.setPhone(i.getPhone());
			signIn.setBlocked(i.getBlocked());
			Set<FavouriteTeam> fSet = new HashSet<>();
			if (i.getFavouriteTeamEntities().size() > 0) {
				i.getFavouriteTeamEntities().forEach((x) -> {
					FavouriteTeam fTeam = new FavouriteTeam();
					fTeam.setAltCityName(x.getAltCityName());
					fTeam.setCity(x.getCity());
					fTeam.setTeamId(x.getId());
					fTeam.setTricode(x.getTricode());
					fTeam.setFullName(x.getFullName());
					fSet.add(fTeam);
				});
				signIn.setFavouriteTeam(fSet);
			}

			l.add(signIn);
		});
		session.close();
		return l;
	}

	@Override
	@Transactional
	public String block(SignIn signIn) {
		System.out.println("block...");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// Query<SignInEntity> query = (Query<SignInEntity>)
		// entityManager.createQuery("from SignInEntity where id =
		// "+eDetails.getQwerty());
		Query query = session.createQuery("update SignInEntity set blocked = :block where id = :id");
		query.setParameter("block", "true");
		query.setParameter("id", signIn.getId());
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println(result + " rows updated");
		return String.valueOf(result) + " rows updated";
	}

	@Override
	@Transactional
	public String insertTeamAsFav(FavouriteTeam favouriteTeam) {
		System.out.println("in favourite team dao...");
		Session session = this.sessionFactory.openSession();
		SignInEntity sEntity = (SignInEntity) session.get(SignInEntity.class, favouriteTeam.getUserId());
		Transaction tx = session.beginTransaction();
		if (sEntity != null) {
			Set<FavouriteTeamEntity> lEntities = sEntity.getFavouriteTeamEntities();
			FavouriteTeamEntity favouriteTeamEntity = new FavouriteTeamEntity();
			favouriteTeamEntity.setId(favouriteTeam.getTeamId());
			favouriteTeamEntity.setAltCityName(favouriteTeam.getAltCityName());
			favouriteTeamEntity.setCity(favouriteTeam.getCity());
			favouriteTeamEntity.setFullName(favouriteTeam.getFullName());
			favouriteTeamEntity.setTricode(favouriteTeam.getTricode());
			lEntities.add(favouriteTeamEntity);
			sEntity.setFavouriteTeamEntities(lEntities);
		}
		session.save(sEntity);
		tx.commit();
		session.close();
		return "Success";
	}

	@Override
	public List<FavouriteTeam> getFavouriteTeamById(String id) {
		System.out.println("in favourite team dao...");
		Session session = this.sessionFactory.openSession();
		List<FavouriteTeam> favouriteTeams = new ArrayList<>();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<FavouriteTeamEntity> criteriaQuery = criteriaBuilder.createQuery(FavouriteTeamEntity.class);
		Root<SignInEntity> root = criteriaQuery.from(SignInEntity.class);
		criteriaQuery.select(root.get("favouriteTeamEntities"));
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		List<FavouriteTeamEntity> favouriteTeamEntities = session.createQuery(criteriaQuery).list();
		favouriteTeamEntities.forEach((i) -> {
			FavouriteTeam fTeam = new FavouriteTeam();
			fTeam.setAltCityName(i.getAltCityName());
			fTeam.setCity(i.getCity());
			fTeam.setFullName(i.getFullName());
			fTeam.setTeamId(i.getId());
			fTeam.setTricode(i.getTricode());
			fTeam.setUserId(id);
			favouriteTeams.add(fTeam);
		});
		return favouriteTeams;
	}

	@Override
	public String removeFromFavourite(String teamId, String userID) {
		String string = "Failure";
		System.out.println("in remove from favourite ....");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<SignInEntity> criteriaQuery = criteriaBuilder.createQuery(SignInEntity.class);
		Root<SignInEntity> sRoot = criteriaQuery.from(SignInEntity.class);
		criteriaQuery.select(sRoot);
		criteriaQuery.where(criteriaBuilder.equal(sRoot.get("id"), userID));
		SignInEntity i = session.createQuery(criteriaQuery).uniqueResult();
		System.out.println(i.getFavouriteTeamEntities().size());

		SignIn signIn = new SignIn();
		signIn.setEmail(i.getEmail());
		signIn.setId(i.getId());
		signIn.setImage(i.getImage());
		signIn.setName(i.getName());
		signIn.setProvider(i.getProvider());
		signIn.setToken(i.getToken());
		signIn.setCity(i.getCity());
		signIn.setCountry(i.getCountry());
		signIn.setSecondPage(i.getSecondPage());
		signIn.setState(i.getState());
		signIn.setPhone(i.getPhone());
		signIn.setBlocked(i.getBlocked());

		Set<FavouriteTeam> fSet = new HashSet<>();
		i.getFavouriteTeamEntities().forEach((x) -> {
			FavouriteTeam fTeam = new FavouriteTeam();
			fTeam.setAltCityName(x.getAltCityName());
			fTeam.setCity(x.getCity());
			fTeam.setTeamId(x.getId());
			fTeam.setTricode(x.getTricode());
			fTeam.setFullName(x.getFullName());
			fSet.add(fTeam);
		});
		signIn.setFavouriteTeam(fSet);
		session.delete(i);
		tx.commit();

		Transaction tx1 = session.beginTransaction();
		SignInEntity signInEntity = new SignInEntity();
		signInEntity.setEmail(signIn.getEmail());
		signInEntity.setId(signIn.getId());
		signInEntity.setImage(signIn.getImage());
		signInEntity.setName(signIn.getName());
		signInEntity.setProvider(signIn.getProvider());
		signInEntity.setToken(signIn.getToken());

		signInEntity.setSecondPage(signIn.getSecondPage());
		signInEntity.setBlocked(signIn.getBlocked());
		signInEntity.setCity(signIn.getCity());
		signInEntity.setCountry(signIn.getCountry());
		signInEntity.setPhone(signIn.getPhone());
		signInEntity.setState(signIn.getState());
		session.persist(signInEntity);
		tx1.commit();

		signIn.getFavouriteTeam().forEach((fff) -> {
			fff.setUserId(signIn.getId());
			if (String.valueOf(fff.getTeamId()).equals(teamId)) {
				System.err.println("matched");
			} else {
				this.insertTeamAsFav(fff);
			}

		});
		session.close();
		string = "Success";
		return string;

	}

	@Override
	public List<Notification> getAllNotification(String string) {
		List<Notification> list = new ArrayList<>();
		Set<Notification> nSet = new HashSet<>();
		Session session = null;
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<NotificationEntity> criteriaQuery = builder.createQuery(NotificationEntity.class);
		Root<NotificationEntity> root = criteriaQuery.from(NotificationEntity.class);
		criteriaQuery.select(root);
		criteriaQuery.where(builder.equal(root.get("userID"), string));
		List<NotificationEntity> notificationList = session.createQuery(criteriaQuery).list();
		notificationList.forEach((i) -> {
			Notification notification = new Notification();
			notification.setMessage(i.getMessage());
			notification.setNotifyDate(i.getNotifyDate());
			notification.setUserId(i.getUserID());
			notification.setGameId(i.getGameId());
			nSet.add(notification);
		});
		list.addAll(nSet);
		session.close();
		return list;
	}

	@Override
	public String addNotification(List<Notification> notifications) {
		System.out.println("notifying...");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		notifications.forEach((notification) -> {
			NotificationEntity notificationEntity = new NotificationEntity();
			notificationEntity.setMessage(notification.getMessage());
			notificationEntity.setNotifyDate(notification.getNotifyDate());
			notificationEntity.setUserID(notification.getUserId());
			notificationEntity.setGameId(notification.getGameId());
			session.persist(notificationEntity);
		});
		tx.commit();
		session.close();
		return "Success";
	}

	@Override
	public String removeNotificationById(String string) {
		System.out.println("deleting.....");
		final Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<NotificationEntity> criteriaQuery = builder.createQuery(NotificationEntity.class);
		Root<NotificationEntity> root = criteriaQuery.from(NotificationEntity.class);
		criteriaQuery.select(root);
		criteriaQuery.where(builder.equal(root.get("userID"), string));
		List<NotificationEntity> notificationList = session.createQuery(criteriaQuery).list();
		notificationList.forEach((i) -> {
			session.delete(i);
		});
		tx.commit();
		session.close();
		return String.valueOf(notificationList.size()) + " rows deleted.";
	}

	@Override
	public String addChat(Chat chat) {
		System.out.println("chatting...");
		System.err.println(chat.getMessage());
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		ChatEntity chatEntity = new ChatEntity();
		chatEntity.setMessage(chat.getMessage());
		chatEntity.setSender(chat.getSender());
		chatEntity.setDateOfSending(chat.getDateOfSending());
		chatEntity.setTeamId(chat.getTeamId());
		chatEntity.setsID(chat.getsID());
		session.persist(chatEntity);
		tx.commit();
		session.close();
		return "Success";
	}

	@Override
	public List<Chat> getAllChats(String string) {
		List<Chat> list = new ArrayList<>();
		Session session = null;
		session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ChatEntity> criteriaQuery = builder.createQuery(ChatEntity.class);
		Root<ChatEntity> root = criteriaQuery.from(ChatEntity.class);
		criteriaQuery.select(root);
		criteriaQuery.where(builder.equal(root.get("teamId"), string));
		List<ChatEntity> chatEntities = session.createQuery(criteriaQuery).list();
		chatEntities.forEach((i) -> {
			Chat chat = new Chat();
			chat.setMessage(i.getMessage());
			chat.setDateOfSending(i.getDateOfSending());
			chat.setReadUnread(i.isReadUnread());
			chat.setSender(i.getSender());
			chat.setsID(i.getsID());
			chat.setTeamId(i.getTeamId());
			list.add(chat);
		});
		session.close();
		return list;
	}
}
