# katalog
**Ejercicio 1:** (30-6-2011)

Constrúyeme en java una clase Log con un método add que añade lineas al log y un método persist que escribe en un fichero las lineas.

Tienes prohibido:
- extender clases, o implementar interfaces
- hacer unit testing
- cualquier mierda que no sea escribir una clase monolítica.

Como mucho, te puede resultar cómodo llamar a la clase desde un test junit, para llamarla desde algún sitio y comprobar que se escribe el fichero y tal...

**Ejercicio 2:** (10-7-2011)

Hacer una clase MailLog que, tras añadir lineas con addLine, si llamas a persist() lo que hace es enviar esas lineas por mail.

Tienes prohibido extender clases, implementar interfaes o whatevers ... lo único que puedes hacer es copipastear código de lo que has hecho hasta ahora. Ni más...  ¡ni menos!  :-)

**Ejercicio 3:** (13-7-2011)

Ahora quiero que crees una branch de git.
Esa branch se llamará "clase abstracta".

Como habrás comprobado, ambas clases comparten código... lo que quiero que hagas es que el código común esté en la clase abstracta, y el código diferente, extienda a esas clases.

Concretamente, habrá una clase abstracta Log, y dos clases que le extienden: FileLog, y MailLog.

Primera pregunta: ¿qué es una clase abstracta?  :-)

**Ejercicio 4:** (18-7-2011)

1. Resumen:
..- has desarrollado una clase que loguea en el filesystem.
..- has desarrollado otra clase que loguea por email
..- tras hacerlo, has detectado duplicidad de código. Para eliminarla, has decidido utilizar un patrón Template (míratelo!) que consiste en crear una plantilla (una clase abstracta) de la cuál sus hijas "rellenan" la parte de la plantilla vacía (método persist).

2. Primera parte:
..- quiero que cubras de tests tu código. Preguntas:
..- ¿qué tipo de tests serán? ¿unitarios? ¿integración? ¿funcionales?
..- quiero un 100% de cobertura --> ¿cómo se obtiene con java-eclipse el % de cobertura?

3. Segunda parte:
..- acto seguido quiero que crees una nueva branch llamada "inyección de dependencias". Pero NO como branch de la "clase abstracta" sino como branch de nuestra primera implementación monolítica.
..- quiero que elimines la duplicidad pero, esta vez, en lugar de con un patrón template, utilizando una clase común a la que inyectarás por constructor dos posibles drivers: FileDriver y MailDriver ... 
