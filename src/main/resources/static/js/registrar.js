$(document).ready(function () {
  //on ready
});
async function registrarUsuario() {
  //usando query selector
  /* let datos={
    nombre: document.querySelector('#exampleFirstName').value,
    apellido: document.querySelector('#exampleLastName').value,
    email: document.querySelector('#exampleInputEmail').value,
    telefono: document.querySelector('#exampleTelefono').value,
    password: document.querySelector('#exampleInputPassword').value
  }*/
  //usando getelementbyid//de amabs formas funciioanria igual. solo qeu la de arriba es la notacion que ayuda a seleccionar elementos de html para el css
  let datos = {
    nombre: document.getElementById("exampleFirstName").value,
    apellido: document.getElementById("exampleLastName").value,
    email: document.getElementById("exampleInputEmail").value,
    telefono: document.getElementById("exampleTelefono").value,
    password: document.getElementById("exampleInputPassword").value,
  };
  //o podria hacerlo como let datos ={}, y luego abajo definir por ejemplo datos.nombre = document.getElementById('exampleFirstName').value...etc otra forma de crear el objeto
  const request = await fetch(`api/usuarios`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });
  alert("Usuario registrado correctamente");
  window.location.href = "login.html";
}
