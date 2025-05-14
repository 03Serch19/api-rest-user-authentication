package com.uso2spring.springproyectaprender.dao;

import java.util.List;

import com.uso2spring.springproyectaprender.Models.Usuario;

public interface UsuarioDao {
 List<Usuario> getUsuarioss();
 void deleteUsuario(Long id);
 //Usuario getUsuario(Long id);
 void registrarU(Usuario usuario);
 Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

}
