$(document).ready(function () {
  //on ready
});
async function login() {
  let datos = {
    email: document.getElementById("exampleInputEmail").value,
    password: document.getElementById("exampleInputPassword").value,
  };
  const request = await fetch(`api/login`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });
  //const response = await request.json();
  const response = await request.text();
  if (response != "FAIL") {
    //alert("Usuario registrado correctamente");
    localStorage.token = response;
    localStorage.email = datos.email;
    window.location.href = "usuarios.html";
  } else {
    alert("Usuario o contraseña incorrectos");
  }
}
