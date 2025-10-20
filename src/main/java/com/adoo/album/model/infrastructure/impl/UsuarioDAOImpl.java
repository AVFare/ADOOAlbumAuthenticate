package com.adoo.album.model.infrastructure.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adoo.album.model.entity.Usuario;
import com.adoo.album.model.infrastructure.IUsuarioDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDAOImpl implements IUsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public Usuario findUser(String username, String password) {
		//todo: mejorar este método porque está medio raro.
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE username=:username", Usuario.class);
		theQuery.setParameter("username", username);

		Usuario user = theQuery.uniqueResult();

		if(user != null && checkPassword(password, user.getPassword())) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUser(String username) {

		//todo: mejorar este método porque está medio raro.
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE username=:username", Usuario.class);
		theQuery.setParameter("username", username);

		Usuario user = theQuery.uniqueResult();

		if(user != null) {
			return user;
		} else {
			return null;
		}
	}

	private boolean checkPassword(String password, String passwordDB) {
		//todo: encriptar todo en todos lados.
		
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// String hashedPassword = passwordEncoder.encode(password);
		// System.out.println("Password: " + password);
		// System.out.println("hashedPassword: " + hashedPassword);
		// System.out.println("passwordDB: " + passwordDB);
		// boolean isPasswordMatch = passwordEncoder.matches(password, passwordDB);
		
		//return isPasswordMatch;

		return password.equals(passwordDB);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(usuario);
		return usuario;
	}


}
