package com.uso2spring.springproyectaprender.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uso2spring.springproyectaprender.Models.Usuario;
import com.uso2spring.springproyectaprender.dao.UsuarioDao;
import com.uso2spring.springproyectaprender.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
  // @RequestMapping("prueba")//puede ir asi o con el"/prueba, funciuona igual
  // @RequestMapping(value = "prueba")//otra forma
  // @RequestMapping(value = "/prueba")//otra forma
  /*
   * @RequestMapping("/prueba")
   * public List<String> prueba() {
   * return List.of("Hola", "Mundo", "desde", "Spring");
   * }
   */
  @Autowired
  private UsuarioDao usuarioDao;

  @Autowired
  private JWTUtil jwtUtil;

  /*
   * @RequestMapping("/{id}")
   * public Usuario getUsuario(@PathVariable Long id) {
   * Usuario usuario = new Usuario();
   * usuario.setId(id);
   * usuario.setNombre("Juan");
   * usuario.setApellido("Perez");
   * usuario.setEmail("usuario@email.com");
   * usuario.setTelefono("78966525");
   * return usuario;
   * }
   */
  @RequestMapping
  public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {
    if (!verificarToken(token)) {
      return new ArrayList<Usuario>();// o return null, aunque internamente esto siempre devolvere el error de la
                                      // validacion del token
    }

    return usuarioDao.getUsuarioss();
  }

  public boolean verificarToken(String token) {
    String usuarioId = jwtUtil.getKey(token);
    return usuarioId != null;
  }

  @RequestMapping(method = RequestMethod.POST)
  public void registrarUsuario(@RequestBody Usuario usuario) {
    usuario.getPassword();
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    String hash = argon2.hash(1, 1024, 1, usuario.getPassword().toCharArray());
    usuario.setPassword(hash);
    usuarioDao.registrarU(usuario);
  }

  /*
   * @RequestMapping("/usuariof")
   * public Usuario updateU() {
   * Usuario usuario = new Usuario();
   * usuario.setNombre("Juan");
   * usuario.setApellido("Perez");
   * usuario.setEmail("usuario@email.com");
   * usuario.setTelefono("78966525");
   * return usuario;
   * }
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteU(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {
    if (!verificarToken(token)) {// sin esta verificacion, si llego a borrar el token o caduca aun asi
                                 // podrianelimninar a un usuario auqnue ya luego daria el error por get, pero el
                                 // delete si lo ejecutaria ahora con la vadicaon, no permirititra eliminar a
                                 // menos que el token exista o sea valido, por tanto el delte no se ejecuta a
                                 // menos que la validacion del ytoken sea valida
      return;
    }
    usuarioDao.deleteUsuario(id);
  }
  /*
   * @RequestMapping("/usuariofff")
   * public Usuario findU() {
   * Usuario usuario = new Usuario();
   * usuario.setNombre("Juan");
   * usuario.setApellido("Perez");
   * usuario.setEmail("usuario@email.com");
   * usuario.setTelefono("78966525");
   * return usuario;
   * }
   */

}
