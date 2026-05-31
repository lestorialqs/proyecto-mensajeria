**UTEC Departamento de Ciencia de la Computaci´on CS3081 - Ingenier´ıa de Software Tarea 8** 

**Fecha: 16/05/2026** 

## **Buen dise˜no - Cohesi´on y Acoplamiento** Profesor: Te´ofilo Chambilla 

**Objetivo:** La presente tarea tiene como objetivo que el estudiante dise˜ne e implemente una soluci´on de software aplicando principios de buen dise˜no, arquitectura de software y calidad de c´odigo. La soluci´on deber´a evidenciar modularidad, abstracci´on, bajo acoplamiento y alta cohesi´on, considerando un escenario basado en mensajer´ıa y procesamiento de eventos. 

- Dise˜nar una soluci´on de software que soporte los atributos de **modularidad** , **abstracci´on** , **bajo acoplamiento** y **alta cohesi´on** . 

- Implementar la soluci´on utilizando un patr´on arquitect´onico adecuado, como **Arquitectura Hexagonal** , **Clean Architecture** , **Microservicios** o **Arquitectura Orientada a Eventos** . 

- Modelar el sistema mediante un **Diagrama de Casos de Uso** , identificando actores, funcionalidades principales y relaciones relevantes. 

- Incorporar un mecanismo de mensajer´ıa utilizando **RabbitMQ** , **Apache Kafka** o **ActiveMQ** , seg´un el enfoque t´ecnico elegido por el equipo. 

- Aplicar buenas pr´acticas de calidad de software, incluyendo pruebas automatizadas, an´alisis est´atico de c´odigo y control de duplicidad. 

**Introducci´on** Ir a un restaurante suele representar un gasto mayor que cocinar en casa; sin embargo, los programas de recompensas y fidelizaci´on permiten que los clientes obtengan beneficios por sus consumos. Estos programas ofrecen acumulaci´on de puntos, reembolsos o beneficios especiales cada vez que un cliente consume en restaurantes afiliados. 

Por ejemplo, Jes´us desea ahorrar dinero para la educaci´on de sus hijos. Cada vez que realiza una cena en un restaurante participante, una parte del consumo es transformada en puntos o recompensas que son abonadas a su cuenta personal. 

Actualmente, debido a la necesidad de procesar grandes vol´umenes de transacciones en tiempo real, las empresas utilizan arquitecturas orientadas a eventos y plataformas de mensajer´ıa como **RabbitMQ** , **Apache Kafka** o **ActiveMQ** , permitiendo desacoplar los sistemas y mejorar la escalabilidad, disponibilidad y resiliencia de las aplicaciones. 

**Implemente el proceso indicado en la Figura 1 considerando una arquitectura basada en mensajer´ıa y eventos:** 

- El restaurante registra la informaci´on de la cena realizada por el cliente. 

CS3081 - Ingenier´ıa de Software 

- P´ag 2 de 3 

- El sistema del restaurante procesa internamente la transacci´on y publica un mensaje en un **Broker de Mensajer´ıa** ( **RabbitMQ, Apache Kafka o ActiveMQ** ) con la siguiente informaci´on: 

   - Monto consumido. 

   - N´umero de tarjeta del cliente. 

   - C´odigo del restaurante afiliado. 

   - Fecha y hora de la transacci´on. 

- El Broker de Mensajer´ıa se encarga de la administraci´on de colas, t´opicos o eventos, garantizando la entrega de mensajes entre productores y consumidores. 

- Un microservicio consumidor correspondiente al sistema de recompensas recibe el mensaje y calcula autom´aticamente los puntos, cashback o beneficios asociados al cliente. 

- El sistema actualiza la cuenta de recompensas del cliente. 

- Opcionalmente, el sistema puede publicar un nuevo evento para el env´ıo de una notificaci´on por correo electr´onico, SMS o aplicaci´on m´ovil indicando que la recompensa fue procesada exitosamente. 

**Consideraciones T´ecnicas** El dise˜no debe considerar principios de: 

- Alta cohesi´on. 

- Bajo acoplamiento. 

- Modularidad. 

- Escalabilidad. 

- Arquitectura orientada a eventos. 

Asimismo, se recomienda aplicar alg´un patr´on arquitect´onico como: 

- Arquitectura Hexagonal. 

- Microservicios. 

- Event-Driven Architecture (EDA). 

- Clean Architecture. 

CS3081 - Ingenier´ıa de Software 

- P´ag 3 de 3 

**Entregable** El proyecto deber´a ser analizado mediante la plataforma **SonarCloud** con el objetivo de evaluar y mejorar la calidad del software desarrollado. El equipo deber´a evidenciar buenas pr´acticas de ingenier´ıa de software relacionadas con mantenibilidad, seguridad, confiabilidad y pruebas automatizadas. 

El proyecto deber´a alcanzar m´etricas vistas en clase en los siguientes atributos de calidad: 

- _Reliability_ (Confiabilidad). 

- _Security_ (Seguridad). 

- _Maintainability_ (Mantenibilidad). 

- _Duplications_ (Duplicaci´on de c´odigo). 

Asimismo, el sistema deber´a alcanzar una cobertura m´ınima de pruebas ( _Test Coverage_ ) del **85%** . 

Para evidenciar el cumplimiento de estas actividades, se deber´a subir a **Canvas** lo siguiente: 

- Enlace p´ublico del an´alisis realizado en **SonarCloud** (Coordinar con los ACL del curso). 

- Enlace del repositorio del proyecto en **GitHub** . 

- Evidencia de ejecuci´on de pruebas automatizadas. 

- Documento breve describiendo la arquitectura implementada y el patr´on arquitect´onico utilizado. 

Figure 1: Proceso del programa de recompensas. 

