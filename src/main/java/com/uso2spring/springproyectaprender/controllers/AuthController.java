package com.uso2spring.springproyectaprender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uso2spring.springproyectaprender.Models.Usuario;
import com.uso2spring.springproyectaprender.dao.UsuarioDao;
import com.uso2spring.springproyectaprender.utils.JWTUtil;

@RestController
public class AuthController {
  @Autowired
  private UsuarioDao usuarioDao;

  @Autowired
  private JWTUtil jwtUtil;

  @RequestMapping(value = "api/login", method = RequestMethod.POST)
  public String login(@RequestBody Usuario usuario) {
    Usuario usuarioAutenticado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
    if (usuarioAutenticado != null) {

      // jwtUtil.create(usuarioAutenticado.getId().toString(),
      // usuarioAutenticado.getEmail());
      // con toStrring se puedey es valido sololoq eu va mas orientado a objetos y en
      // caso de un nullpoistexception lanzaria la excepcion, en cambvio con
      // Stirng.valueof, solo devolveria un null
      // devolvemos el token, podriamos almacenarlo en una varaible String token y
      // devolver esto para que sea un poco mas ebntendible, pero lo ideal es
      // devolverlo de un solo
      return jwtUtil.create(String.valueOf(usuarioAutenticado.getId()), usuarioAutenticado.getEmail());
      // aun asi aqui podriamos devolver por ejemplo permisos del usario, o todso el
      // usuario la info que queremos aasi que el usuario usarea para qeu haga menos
      // request, pero en este caso pues solo devolveremows el token
    } else {
      return "FAIL";
    }
  }
}
