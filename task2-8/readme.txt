На Tomcat 10.0.11 не взлетело.
10-й требовал Jakarta. Javax не опознал

На Tomcat 9.0.53 отработало без замечаний.

Скомпилированный Servlet и вручную созданный web.xml,
размещались в Tomcat в структуре приложения созданного так же руками.

webapp
  |--task2-8
       |--WEB-INF
           |--classes
           |    |--by
           |        |--academy
           |              |--it
           |                  |--main
           |                       |--web
           |                            |-- Task8Servlet.class
           |--lib
           |--web.xml