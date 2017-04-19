###ATTENTION: PROJECT UNDER CONSTRUCTION

## ATC Admin Console
Administrator interface to configure and monitor call-center CRM(based on Asterisk ATC).

## Start application under Tomcat application server
Чтобы в Tomcat приложение занимало root путь:
1) Скопировать tomcat/ROOT.xml в %tomcat_root%/conf/
2) Поправить %tomcat_root%/conf/server.xml
<Host name="hostname"  appBase="webapps" unpackWARs="true" autoDeploy="true">
	<Context docBase="admin-console" path="/" reloadable="true"/>
...
3) Удалить webapp/ROOT


## Установка и Запуск
Как выполнить инсталляцию и запуск приложения.
(для его инсталляции и запуск.)

## Поддержка
Если у вас возникли сложности или вопросы по использованию пакета, создайте 
[обсуждение][] в данном репозитории или напишите на электронную почту 
<i.leontyev81@gmail.com>.

## Документация
Документацию API можно получить из исходных кодов.

## License
The ATCAdminConsole application is open-sourced software licensed under the [ZK Open Source License](https://www.zkoss.org/license/gpl).

