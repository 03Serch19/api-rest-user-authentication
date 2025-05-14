package com.uso2spring.springproyectaprender.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uso2spring.springproyectaprender.Models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Usuario> getUsuarioss() {
    String query = "FROM Usuario";
    // List<Usuario> resultado= entityManager.createQuery(query).getResultList();
    // return resultado;
    // si se crea la variable solo para devolver es mejor directamente devolver el
    // resultado
    return entityManager.createQuery(query/* , Usuario.class */).getResultList();
  }
  /*
   * @Override
   * public Usuario getUsuario(Long id) {
   * return entityManager.createQuery(query+" WHERE id = :id", Usuario.class)
   * .setParameter("id", id)
   * .getSingleResult();
   * //return entityManager.find(Usuario.class, id);
   * }
   */

  // una opcion de eliminarlo
  @Override
  public void deleteUsuario(Long id) {
    Usuario usuario = entityManager.find(Usuario.class, id);
    entityManager.remove(usuario);

  }

  @Override
  public void registrarU(Usuario usuario) {
    entityManager.merge(usuario);
  }

  // @Override
  // public boolean verificarCredenciales(Usuario usuario) {
  // String query="FROM Usuario WHERE email = :email AND password = :password";
  /*
   * Usuario
   * userLogin=entityManager.createQuery(query,Usuario.class).setParameter(
   * "email", usuario.getEmail())
   * .setParameter("password", usuario.getPassword()).getSingleResult();//para
   * cuadnoi se pueden esperar que no encuentre valores lo merjo es usar
   * getResultList
   * //boolean existe = (userLogin != null)?true:false;
   * //return existe; //esto no llegara a ser null, ya qeu si no encuetra nada
   * enviera una exetmpcion la cual es un objeto de todas formas
   * return (userLogin != null);
   */
  // List<Usuario>
  // userLogin=entityManager.createQuery(query,Usuario.class).setParameter("email",
  // usuario.getEmail())
  // .setParameter("password", usuario.getPassword()).getResultList();
  // return !userLogin.isEmpty();
  // }

  // version donde no usamos el pasord, ay que este se verificara de otra foprma
  // ya que esta encriptado
 /*  @Override
  public boolean verificarCredenciales(Usuario usuario) {
    String query = "FROM Usuario WHERE email = :email";
      List<Usuario> userLogin = entityManager.createQuery(query, Usuario.class)
          .setParameter("email", usuario.getEmail())
          .getResultList();
          if (userLogin.isEmpty()) {//esto pra evitar un nullpointer exception en caso no encuentre ninguna coincidencia de eamil y por tanto devolvera una lista vacia
            return false;
          }
      String passwordHashed=userLogin.get(0).getPassword();
      Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
     return argon2.verify(passwordHashed, usuario.getPassword().toCharArray());
  }*/

  @Override
  public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
    String query = "FROM Usuario WHERE email = :email";
      List<Usuario> userLogin = entityManager.createQuery(query, Usuario.class)
          .setParameter("email", usuario.getEmail())
          .getResultList();
           if (userLogin.isEmpty()) {//esto pra evitar un nullpointer exception en caso no encuentre ninguna coincidencia de eamil y por tanto devolvera una lista vacia
            return null;
          }
      String passwordHashed=userLogin.get(0).getPassword();
      Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
     if(argon2.verify(passwordHashed, usuario.getPassword().toCharArray())){
      return userLogin.get(0);//si la verificacion es correcta devolvemos el usuario
     }else{
      return null;
     }
  }
}
