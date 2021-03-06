create database if not exists dbbrevets;

use dbbrevets;

-- Creation de la table ENTREPRISE
create table ENTREPRISE
(
  NUM_ENTREPRISE int primary key auto_increment,
  NOM_ENTREPRISE varchar(30),
  ACTIVITE varchar(30),
  CA double,
  VILLE varchar(30)
);
-- Fin de Creation de la table ENTREPRISE

-- Creation de la table INVENTEUR
create table INVENTEUR
(
	NUM_INVENTEUR int primary key auto_increment, 
	NOM VARCHAR(30),
	PRENOM VARCHAR(30),
	ADRESSE VARCHAR(50),
	DATE_NAISS DATE,
	NUM_ENTREPRISE int, 
	FOREIGN KEY (NUM_ENTREPRISE) REFERENCES Entreprise(NUM_ENTREPRISE)
);
-- Fin de Creation de la table INVENTEUR

-- Creation de la table DOMAINE
create table DOMAINE
(
  NUM_DOMAINE int primary key auto_increment,
  NOM_DOMAINE varchar(30)
);
-- Fin de Creation de la table DOMAINE

-- Creation de la table INVENTION
create table INVENTION
(
  NUM_INVENTION int primary key auto_increment,
  DESCRIPTIF varchar(200),
  RESUME varchar(2000),
  NUM_DOMAINE int,
  FOREIGN KEY (NUM_DOMAINE) REFERENCES DOMAINE(NUM_DOMAINE)
);
-- Fin de Creation de la table INVENTION

-- Creation de la table BREVET
create table BREVET
(
  NUM_BREVET int primary key auto_increment,
  DESCRIPTION varchar(400),
  DATE_DEPOT DATE,
  DATE_VALIDATION DATE,
  NUM_INVENTEUR int,
  NUM_INVENTION int,
  FOREIGN KEY (NUM_INVENTEUR) REFERENCES INVENTEUR(NUM_INVENTEUR),
  FOREIGN KEY (NUM_INVENTION) REFERENCES INVENTION(NUM_INVENTION)
);
-- Fin de Creation de la table BREVET


-- Remplissage de la table DOMAINE
insert into DOMAINE values (null,'NTIC');
insert into DOMAINE values (null,'MEDICAL');
insert into DOMAINE values (null,'ENSEIGNEMENT');
insert into DOMAINE values (null,'INDUSTRIE');

-- Remplissage de la table ENTREPRISE
insert into entreprise values (null,'OCP','Mines',278000,'CASA');
insert into entreprise values (null,'ONEP','EAU&ELECTRICITE',7909066,'RABAT');
insert into entreprise values (null,'MAROC TELECOM','TELECOM',4355900,'RABAT');
insert into entreprise values (null,'Royal Air Maroc','TRANSPORT',1200000,'CASA');
insert into entreprise values (null,'Groupe SAHAM','ASSURANCES',2390000,'TETOUAN');
insert into entreprise values (null,'Vivo Energy Maroc','Hydrocarbures',556600,'CASA');
insert into entreprise values (null,'TOTAL MATOC','Hydrocarbures',1890000,'TETOUAN');
insert into entreprise values (null,'MARJANE','GRANDE DISTRIBUTION',9000055,'CASA');
insert into entreprise values (null,'LABEL VIE','GRANDE DISTRIBUTION',340000,'RABAT');
insert into entreprise values (null,'WAFA ASSURANCES','ASSURANCES',500909,'TETOUAN');
insert into entreprise values (null,'RMA','ASSURANCES',4889892,'RABAT');
insert into entreprise values (null,'ORANGE MAROC','TELECOM',4008985,'TANGER');
insert into entreprise values (null,'AL OMRANE','HABITAT',3909095,'RABAT');
insert into entreprise values (null,'HOLCIM','Mat??riaux de construction',7867860,'TANGER');
insert into entreprise values (null,'Lafarge Ciments','Mat??riaux de construction',230000,'CASA');

-- Liste des inventions
insert into INVENTION values (null,'Syst??me de visiophonie mobile','C est une nouvelle technique permettant la communication avec la voix et les images vid??o ?? partir des r??seaux classiques et sans passer par les r??seaux UMTS et les t??l??phones portables de 3e g??n??ration.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='NTIC'));
insert into INVENTION values (null,'Stylo infrarouge','Ce stylo infrarouge innovant ??TBI?? permet de visualiser, de feuilleter, de retravailler, de rembobiner, d incorporer et de corriger un document de type PowerPoint lors d???une projection.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='NTIC'));
insert into INVENTION values (null,'Traitement du d??collement RR','Une nouvelle m??thode d???intervention chirurgicale plus efficace pour le traitement du d??collement regmatog??ne de la r??tine, reposant sur la lib??ration progressive du liquide sous-r??tinien.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='MEDICAL'));
insert into INVENTION values (null,'D??tection d???eau souterraine','Un syst??me permettant la d??tection des flux d???eau en ??coulement au sous-sol ?? partir de l???utilisation de la r??sonance magn??tique nucl??aire (RMN).',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Appareils pour les malvoyants','Youssef A??t Ali a invent?? une urne intelligente ??quip??e d???une lampe et d???un syst??me d???alarme pour d??tecter les pratiques ??lectorales frauduleuses.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Electrocardiogramme','Un ??lectrocardiogramme ??Markar?? pour la d??tection, l?????tude et la surveillance de maladies cardiaques,',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='MEDICAL'));
insert into INVENTION values (null,'Moteur rotatif carr??','Le moteur rotatif rotor carr?? est compos?? d???un rotor en forme carr?? arqu?? et d???un stator ?? quatre chambres, qui permet d???augmenter la puissance et la vitesse par rapport aux moteurs disponibles sur le march?? et aussi diminuer la consommation du combustible.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Antibiotique ?? base d???huiles essentielles','Afin de lutter contre la r??sistance de certaines bact??ries aux antibiotiques, le chercheur marocain a mis au point une nouvelle m??thode pour renforcer les antibiotiques avec des huiles essentielles.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='MEDICAL'));
insert into INVENTION values (null,'Solardo','Le ??Solar Daylighting Optical Fiber system?? est en fait un syst??me d?????clairage naturel de type panneaux solaires ?? la diff??rence pr??s qu???il utilise une fibre optique un peu particuli??re.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='NTIC'));
insert into INVENTION values (null,'Voiture de sport ??lectrique','C???est la premi??re voiture ??lectrique 100% marocaine, d??di??e au march?? africain et arabe.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Brosse en plastique','Autodidacte, ce nonag??naire d??c??d?? il y a quelques mois a con??u la fameuse brosse ?? cheveux ronde en plastique vendue au prix symbolique d???un dirham.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Puce de recharge des batteries','Cette puce r??volutionnaire est capable non seulement de recharger les batteries des smartphones et les v??hicules ??lectriques en dix minutes seulement.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='NTIC'));
insert into INVENTION values (null,'Dessalinisateur solaire','Le prototype de dessalinisateur solaire a pour objectif la production de 5 ?? 10 litres d???eau potable par jour en utilisant exclusivement l?????nergie solaire.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Ampoule ??lectrique',' l???Am??ricain Thomas Edison a d??velopp?? en 1879 et surtout commercialis?? cette trouvaille qui, avec la diffusion de l?????lectricit??, va r??volutionner la vie quotidienne des gens.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Avion','Les avions sont utilis??s tr??s rapidement par les arm??es, mais le premier vol civil commercial a d??j?? lieu aux USA en 1914.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Ordinateur personnel','En 1965, est commercialis?? le premier ordinateur de bureau, l???Olivetti Programma 101. Le premier v??ritable ordinateur individuel, de dimension r??duite, est brevet?? en 1973 : le Micral N fran??ais.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='NTIC'));
insert into INVENTION values (null,'Les vaccins','La protection contre les maladies par la vaccination a sauv?? des millions de vies.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='MEDICAL'));
insert into INVENTION values (null,'L???automobile','Il y en avait 100.000 en 1930 en Belgique, pour pr??s de??? 6 millions aujourd???hui ! L???automobile a connu un succ??s consid??rable, rendant les transports tellement plus simples.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'L???horloge','Pour mesurer le temps qui passe, les hommes ont imagin?? des dizaines de syst??mes, dont l???horloge m??canique, qui appara??t dans la deuxi??me moiti?? du XIIIe si??cle. ',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'Le t??l??phone','Le smartphone a aujourd???hui modifi?? la vie de nombreux usagers. On est loin du premier t??l??phone, mis au point par l???Am??ricain d???origine anglaise Alexander Graham Bell.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='NTIC'));
insert into INVENTION values (null,'La r??frig??ration','Pour conserver la nourriture, les M??sopotamiens, les Grecs ou les Romains utilisaient d??j?? des fosses remplies de glace. Les premi??res machines industrielles de r??frig??ration (?? ammoniaque liquide) apparaissent dans la deuxi??me moiti?? du XIXe si??cle.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='INDUSTRIE'));
insert into INVENTION values (null,'L???appareil photo','Joseph Nic??phore Ni??pce, un inventeur fran??ais, r??ussit ?? fixer des images sur des plaques d?????tain d??s 1826. Louis Daguerre am??liore le proc??d??.',(select NUM_DOMAINE FROM DOMAINE WHERE NOM_DOMAINE='NTIC'));

-- Liste des inventeurs 
insert into INVENTEUR values (null,'El Bouazzaoui','Majid','TANGER','1980-12-09',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='ORANGE MAROC'));
insert into INVENTEUR values (null,'El Mohaddeb','Mostapha','RABAT','1974-06-14',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='RMA'));
insert into INVENTEUR values (null,'RAZIK','SAID','CASA','1966-10-05',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='OCP'));
insert into INVENTEUR values (null,'Oualyouddine','Abdelaziz','TETOUAN','1983-04-20',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='HOLCIM'));
insert into INVENTEUR values (null,'A??t Ali','Youssef','CASA','1954-09-23',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='MARJANE'));
insert into INVENTEUR values (null,'HASSAN','ALAOUI','TANGER','1986-03-10',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='Groupe SAHAM'));
insert into INVENTEUR values (null,'Chakroun','Abdellah','CASA','1985-05-17',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='WAFA ASSURANCES'));
insert into INVENTEUR values (null,'Remmal','Adnane','TETOUAN','1966-06-16',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='ORANGE MAROC'));
insert into INVENTEUR values (null,'Sedki','Leila','RABAT','1982-02-19',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='Lafarge Ciments'));
insert into INVENTEUR values (null,'Morchid','Imad','RABAT','1986-05-07',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='Vivo Energy Maroc'));
insert into INVENTEUR values (null,'TAZI','Abdelalziz','CASA','1972-09-08',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='Royal Air Maroc'));
insert into INVENTEUR values (null,'Yazami','Rachid','RABAT','1973-05-15',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='LABEL VIE'));
insert into INVENTEUR values (null,'Berrada','Mehdi','TETOUAN','1950-03-28',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='MAROC TELECOM'));
insert into INVENTEUR values (null,'Edison','Thomas','CASA','1945-04-18',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='AL OMRANE'));
insert into INVENTEUR values (null,'Ader','Clement','TANGER','1949-07-28',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='TOTAL MATOC'));
insert into INVENTEUR values (null,'Steve','Jobs','TETOUAN','1967-10-19',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='OCP'));
insert into INVENTEUR values (null,'JENNER','EDWARD','CASA','1969-12-03',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='MAROC TELECOM'));
insert into INVENTEUR values (null,'Verbiest','Ferdinand','TETOUAN','1977-05-05',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='ORANGE MAROC'));
insert into INVENTEUR values (null,'MILAN','UNKNOWN','TANGER','1954-01-19',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='HOLCIM'));
insert into INVENTEUR values (null,'BELL','GRAHAM','RABAT','1960-12-20',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='RMA'));
insert into INVENTEUR values (null,'Grec','UNKNOWN','CASA','1961-10-03',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='LABEL VIE'));
insert into INVENTEUR values (null,'Nic??phore','JOSEPH','TETOUAN','1962-11-14',(SELECT NUM_ENTREPRISE FROM ENTREPRISE WHERE NOM_ENTREPRISE='MARJANE'));
 
-- Liste des brevets
insert into BREVET values (null,'Visiophonie Mobile','1997-02-02','2000-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'El Bouazzaoui'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Syst??me de visiophonie mobile'));
insert into BREVET values (null,'Stylo infrarouge','1950-02-02','1951-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'El Mohaddeb'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Stylo infrarouge'));
insert into BREVET values (null,'Eau souterraine','1960-02-02','1962-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'RAZIK'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='D??tection d???eau souterraine'));
insert into BREVET values (null,'App des malvoyants','1950-02-02','1952-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Oualyouddine'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Appareils pour les malvoyants'));
insert into BREVET values (null,'Electrocardiogramme','1951-02-02','1959-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'A??t Ali'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Electrocardiogramme'));
insert into BREVET values (null,'Moteur RC','1952-02-02','1959-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'HASSAN'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Moteur rotatif carr??'));
insert into BREVET values (null,'Antibiotique ?? base HE','1953-02-02','1957-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Chakroun'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Antibiotique ?? base d???huiles essentielles'));
insert into BREVET values (null,'Solardo','1954-02-02','1957-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Remmal'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Solardo'));
insert into BREVET values (null,'Voiture de SE','1970-02-02','1975-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Sedki'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Voiture de sport ??lectrique'));
insert into BREVET values (null,'Brosse en plastique','1971-02-02','1973-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Morchid'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Brosse en plastique'));
insert into BREVET values (null,'Recharge des batteries','1972-02-02','1976-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'TAZI'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Puce de recharge des batteries'));
insert into BREVET values (null,'Dessalinisateur solaire','1973-02-02','1976-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Yazami'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Dessalinisateur solaire'));
insert into BREVET values (null,'Ampoule','1974-02-02','1975-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Berrada'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Ampoule ??lectrique'));
insert into BREVET values (null,'Avion','1900-02-02','1900-09-22',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Edison'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Avion'));
insert into BREVET values (null,'Ordinateur','1901-02-02','1902-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Ader'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Ordinateur personnel'));
insert into BREVET values (null,'Vaccins','1902-02-02','1902-08-15',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Steve'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Les vaccins'));
insert into BREVET values (null,'Automobile','1903-02-02','1904-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'JENNER'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='L???automobile'));
insert into BREVET values (null,'Horloge','1904-02-02','1905-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Verbiest'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='L???horloge'));
insert into BREVET values (null,'T??l??phone','1905-02-02','1908-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'MILAN'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='Le t??l??phone'));
insert into BREVET values (null,'R??frig??ration','1906-02-02','1908-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Grec'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='La r??frig??ration'));
insert into BREVET values (null,'App areil photo','1907-02-02','1910-01-01',(select NUM_INVENTEUR from INVENTEUR where NOM = 'Nic??phore'),(select NUM_INVENTION from INVENTION where DESCRIPTIF='L???appareil photo'));

