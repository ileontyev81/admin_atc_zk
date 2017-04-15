## ATCAdminConsole
Admin UI interface to tune and control ATC(used for sip support inside CRM) and related CRM settings.
Author use Asterisk ATC.

## Start application under Tomcat application server
Чтобы в Tomcat приложение занимало root путь:
1) Скопировать ROOT.xml в %tomcat_root%/conf/
2) Поправить %tomcat_root%/conf/server.xml
<Host name="hostname"  appBase="webapps" unpackWARs="true" autoDeploy="true">
	<Context docBase="admin-console" path="/" reloadable="true"/>
...
3) Удалить webapp/ROOT

## License
The ATCAdminConsole application is open-sourced software licensed under the [ZK Open Source License](https://www.zkoss.org/license/gpl).

