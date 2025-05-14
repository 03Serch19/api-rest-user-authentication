# Api-rest-user-authentication

API REST encargada de la gestión segura de usuarios. El registro se realiza mediante algoritmos de encriptación robustos, y el manejo de sesiones de usuario se implementa a través de tokens JWT. Este mecanismo controla las acciones a realizar en la aplicación mediante una expiración veloz del token, lo que requiere que el usuario se autentique cada cierto tiempo según lo establecido.

Esta API REST es escalable para añadir el resto de las operaciones CRUD. Antes de realizar cualquier operación, se efectuará la inspección pertinente para verificar la validez del token del usuario.

Esta es una versión de aprendizaje y pruebas, con la cual podemos escalar a un funcionamiento regular en un tiempo más corto, logrando la gestión de sesiones que tienen grandes aplicaciones bancarias, las cuales funcionan de manera similar. Además, podemos añadirle aún más robustez implementando Spring Security, ya que esta API utiliza el sistema de encriptación sin el uso del módulo de seguridad de Spring, el cual podría ofrecer una protección adicional significativa.

**Tecnologías:**

* Java SE
* Jakarta EE
* Spring Boot Framework
* Persistencia con JPA puro de Jakarta EE
