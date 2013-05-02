/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\
/!\/!\/!\ Ne pas utiliser M2Eclipse /!\/!\/!\ 

/!\ Pour généré la configuration Eclipse  /!\
/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\

Methode V1
/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\
Sous maven dans le repertoire du projet
  *  mvn flexmojos:flexbuilder
Puis dans eclipse
  * fichier > import > Flash Builder > Project Flash Builder
  * Dossier du projet 
  * Finish
  * OK
/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\


Methode V2
/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\
Sous maven dans le repertoire du projet
  * mvn eclipse:clean
Puis dans eclipse
  * fichier > new > Project Flex
     * Dossier = le repertoire du projet
     * Type d application = Application Air
     * Version du sdk 4.5
  * Finish
Puis Sous maven dans le repertoire du projet
  * mvn flexmojos:flexbuilder  
/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\


Puis
/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\
Rajouter dans les 
  * Propriété du projet
  * Compilateur Flex
  * Arguments de compilation Flex ajouter
     -include-libraries c:/javadev/repository/maven2/org/graniteds/granite-essentials-swc/2.2.0.RC1/granite-essentials-swc-2.2.0.RC1.swc c:/javadev/repository/maven2/org/graniteds/granite-swc/2.2.0.RC1/granite-swc-2.2.0.RC1.swc
/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\/!\
 
Plugin Eclipse Utils : 
 - Flex Formatter :   http://sourceforge.net/projects/flexformatter/
 
 
 

Note interne :

Création d’un certificat auto-signé à l’aide de l’outil ADT 
http://help.adobe.com/fr_FR/air/build/WS5b3ccc516d4fbf351e63e3d118666ade46-7f74.html

adt -certificate -cn name [-ou org_unit][-o org_name][-c country] key_type pfx_file password

C:\javadev\app\AdobeAIRSDK\bin\adt -certificate -cn SelfSign -ou QE -o "TTbox, Eu" -c FR 2048-RSA sign.p12 39#wnetx3tl 



TODO Finir Build a air application
cf https://docs.sonatype.org/display/FLEXMOJOS/Building+an+AIR+Application
