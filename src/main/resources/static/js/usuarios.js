// Call the dataTables jQuery plugin
$(document).ready(function () {
  cargarUsuario();
  //alert("Usuarios page loaded");
  $("#usuarios").DataTable();
});
async function cargarUsuario() {
  const request = await fetch(`api/usuarios`, {
    method: "GET",
    headers: getHeaders(),
  });
  const usuarios = await request.json();
  let listaUsuariosHTML = "";
  for (let usuario of usuarios) {
    let botonElimninar =
      '<a href="#" onclick = "eliminarUsuario(' +
      usuario.id +
      ')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
    let usuarioRow =
      "<tr><td>" +
      usuario.id +
      "</td><td>" +
      usuario.nombre +
      "</td><td>" +
      usuario.apellido +
      "</td><td>" +
      usuario.email +
      "</td><td>" +
      usuario.telefono +
      "</td><td>" +
      botonElimninar +
      "</td></tr>";
    listaUsuariosHTML += usuarioRow;
  }
  console.log(usuarios);
  document.querySelector("#usuarios tbody").outerHTML = listaUsuariosHTML;

  //let usuarioRowHTML ='<tr><td>Tiger Nixon</td><td>prueba</td><td>Edinburgh</td><td>61</td><td>2011/04/25</td><td><a href="#" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a></td></tr>'

  //document.querySelector('#usuarios tbody').outerHTML += usuarioRowHTML;
}
function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token,
  };
}
async function eliminarUsuario(id) {
  //alert(id)
  if (!confirm("¿Desea eliminar el usuario con id: " + id + "?")) {
    return;
  }
  const request = await fetch(`api/usuarios/` + id, {
    method: "DELETE",
    headers: getHeaders(),
  });
  //no recarga la pagina y nada mas actualiza la fila//esta es una mejhor forma
  /*if(request.ok){
  alert("Usuario eliminado");
  cargarUsuario();}*/

  //aca si actualizaria la pagina
  location.reload();
}
