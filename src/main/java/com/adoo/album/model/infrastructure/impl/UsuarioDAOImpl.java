package com.adoo.album.model.infrastructure.impl;

import java.util.Optional;

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
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE username=:username", Usuario.class);
        theQuery.setParameter("username", username);

        Usuario user = theQuery.uniqueResult();

        if (user != null && checkPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findUser(String username) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE username=:username", Usuario.class);
        theQuery.setParameter("username", username);

        return theQuery.uniqueResult();
    }

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Usuario u = currentSession.get(Usuario.class, id);
		return Optional.ofNullable(u);
	}

    private boolean checkPassword(String password, String passwordDB) {
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

