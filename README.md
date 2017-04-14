# admin_atc_zk
UI admin interface to tune and control asterix atc(sip inside CRM) and related CRM settings

Чтобы в Tomcat приложение занимало root путь:
1) Скопировать ROOT.xml в %tomcat_root%/conf/
2) Поправить %tomcat_root%/conf/server.xml
<Host name="hostname"  appBase="webapps" unpackWARs="true" autoDeploy="true">
	<Context docBase="admin-console" path="/" reloadable="true"/>
...
3) Удалить webapp/ROOT
