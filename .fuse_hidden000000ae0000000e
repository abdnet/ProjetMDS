#!/bin/bash

fct_menu ()
{
echo ------------------------------ Micro Projet EJB ----------------------------
echo "Choisir un ordre"
echo "Quitter le script via (Q)"
echo 
echo " Clean + install              (1) "
echo " Lancer le Client             (2) "
echo " Lancer le deployement        (3) "
echo " Clean + deployement          (4) "
echo " All                          (5) "
echo " Lancer le serveur            (6) "
echo " arreter le serveur           (7) "

echo
echo -n "Choix > "
echo -n

read optionmenu
    case $optionmenu in
    1)
		clear
		asadmin stop-domain
		asadmin stop-database
        mvn clean install 
        clear
        fct_menu ;;
    3)
       	clear
       	asadmin start-domain
		asadmin start-database
		asadmin undeploy Entity-Bean
		asadmin deploy ServerBean/target/Entity-Bean.jar
		asadmin stop-domain
		asadmin stop-database

        fct_menu ;;
    2)  clear
		asadmin start-domain
		asadmin start-database
        cd ClientBean
		java -classpath $CLASSPATH:../ServerBean/target/Entity-Bean.jar:target/ClientBean-4.0-SNAPSHOT.jar enterprise/appClient
		cd ..
		asadmin stop-domain
		asadmin stop-database
		
        fct_menu ;;
    4)
        clear
        asadmin stop-domain
        asadmin stop-database
        mvn clean install
        asadmin start-domain
        asadmin start-database
        asadmin undeploy Entity-Bean
        asadmin deploy ServerBean/target/Entity-Bean.jar
        fct_menu ;;
    5)
	clear
        asadmin stop-domain
        asadmin stop-database
        mvn clean install
        asadmin start-domain
        asadmin start-database
        asadmin undeploy Entity-Bean
        asadmin deploy ServerBean/target/Entity-Bean.jar
	cd ClientBean
	java -classpath $CLASSPATH:../ServerBean/target/Entity-Bean.jar:target/ClientBean-4.0-SNAPSHOT.jar enterprise/appClient
	cd ..
        fct_menu ;; 
    6) 
	asadmin start-domain
        asadmin start-database
	fct_menu ;;
    7)
	asadmin stop-domain
        asadmin stop-database
	fct_menu ;;
	   
    Q)
        exit ;;
    *)
        echo
        echo "Erreur de frappe "
        echo
        fct_menu ;;
        esac
}
fct_menu
