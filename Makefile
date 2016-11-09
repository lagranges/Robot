# Ensimag 2A POO - TP 2016/17
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

ENCODING=-encoding UTF-8
JAVADOC_ENCODING=$(ENCODING) -charset UTF-8 -docencoding UTF-8
SOURCEPATH=-sourcepath src/
GUI_CLASSPATH=-classpath bin/gui.jar
JAVAC_DESTINATION_FOLDER=-d bin

JAVAC=javac $(ENCODING) $(JAVAC_DESTINATION_FOLDER) $(SOURCEPATH) $(GUI_CLASSPATH)
JAVADOC=javadoc $(JAVADOC_ENCODING) -d doc-tmp/ $(SOURCEPATH)

all: testInvader testLecture

testInvader:
	$(JAVAC) src/robot/TestInvader.java

testLecture:
	$(JAVAC) $(GUI_CLASSPATH) src/robot/TestLecteurDonnees.java

testDonnees:
	$(JAVAC) $(GUI_CLASSPATH) src/robot/TestDonneesSimulation.java

testSimulation:
	$(JAVAC) $(GUI_CLASSPATH) src/robot/TestSimulation.java	

javadoc:
	$(JAVADOC) src/robot/*.java src/robot/entities/*.java src/robot/io/*.java src/robot/map/*.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:bin/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeInvader
exeInvader: 
	java -classpath bin:bin/gui.jar TestInvader

exeLecture: 
	java -classpath bin TestLecteurDonnees cartes/carteSujet.map

exeDonnees:
	java -classpath bin TestDonneesSimulation cartes/carteSujet.map

exeSimulationSujet:
	java -classpath bin:bin/gui.jar TestSimulation cartes/carteSujet.map	

exeSimulationMadness:
	java -classpath bin:bin/gui.jar TestSimulation cartes/spiralOfMadness-50x50.map

exeSimulationDeath:
	java -classpath bin:bin/gui.jar TestSimulation cartes/desertOfDeath-20x20.map

exeSimulationHell:
	java -classpath bin:bin/gui.jar TestSimulation cartes/mushroomOfHell-20x20.map

clean:
	rm -rf bin/*.class
	rm -rf src/robot/*.class
	rm -rf src/robot/entities/*.class
	rm -rf src/robot/map/*.class
	rm -rf src/robot/io/*.class
