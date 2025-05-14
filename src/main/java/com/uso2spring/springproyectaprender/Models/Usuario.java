package com.uso2spring.springproyectaprender.Models;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
@Table(name = "usuarios")
public class Usuario {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Getter @Setter @Column(name = "id")
 private Long id;
 @Getter @Setter @Column(name = "nombre")
 private String nombre;
 @Getter @Setter @Column(name = "apellido")
 private String apellido;
 @Getter @Setter @Column(name = "email")
 private String email;
 @Getter @Setter @Column(name = "password")
 private String password;
 @Getter @Setter @Column(name = "telefono")
  private String telefono;

  

}
