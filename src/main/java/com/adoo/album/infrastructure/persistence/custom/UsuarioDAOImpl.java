package com.adoo.album.infrastructure.persistence.custom;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adoo.album.model.entity.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDAOImpl implements IUsuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    private boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE email = :email", Usuario.class);
        theQuery.setParameter("email", email);
        return theQuery.uniqueResult();
    }

}

