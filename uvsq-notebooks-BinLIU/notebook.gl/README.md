# Programmation, GL, preuve
Application ligne de commande pour la prise de notes

## Fichiers
* Add.java : hériter la class `Command` et permet d'ajouter les fichiers par le récepteur
* APP.java : main méthode et réaliser toutes les opérations désirées
* Command.java : la définition pour le fondement des différents fonctionalités
* Delete.java : hériter la class `Command` et permet de supprimer les fichiers par le récepteur
* DirectoryReceiver.java : permettre lister les notes existantes et rechercher par les notes clés
* Edit.java : hériter la class `Command` et permet de rédiger les fichiers par le récepteur
* FileReceiver.java : permettre de créer un fichier de note , modifier une note sur un éditeur , supprimer une note et voir une note sur un navigateur
* ListFile.java : hériter la class `Command` et permet d'afficher les fichiers toutes les noms de fichiers sur un bon chemin et les infos détaillés du fichiers qui peut les enregistrer au format JSON
* ListFileManager.java : permettre de lister les fichiers avec ses index par ordre
* OSValidator.java : configurer dans dse différents systèmes
* Receiver.java : définition des attributs que les différents récepteurs vont appliquer
* Search.java : hériter la class `Command` et permet de trouver les fichiers par le récepteur
* View.java : hériter la class `Command` et permet de voir les fichiers par le récepteur
* logo.txt : le déssin de 'Notebooks'
* menu.txt : interprétations des commandes
* pom.xml : configurer les plugins et les dépendences
* config.xml : configurer le path du répertoire , celui de navigateur , celui d'éditeur et le type de fichier '.adoc' etc pour Linux
* configW.xml : configurer le path du répertoire , celui de navigateur , celui d'éditeur et le type de fichier '.adoc' etc pour Windows


## Utilisation
Passer à l'étape comme des explications dans 'menu.txt' que s'il n'y a AUCUN problème de manager des notes.

1. Ajouter un note avec le command `a`  ou  `add` + un nom du fichier et rédiger des attributs asciidoctor dans ce note sur un éditeur et générer un fichier '.adoc'
2. Lister des fichiers dans notre répertoire pour vérifier s'ils existent avec le command `l` ou `list`, 
3. Manager des ordres alphabétiques sur la liste des notes classés selon différents critères avec le command `listManager -m w` ou `lm -m w` (`listManager -m r` ou `lm -m r`),
4. Modifier des attributs asciidoctor sur un éditeur avec le command `e` ou `edit` + un nom du fichier
5. Voir des attributs asciidoctor sur un navigateur avec le command `v` ou `view` + un nom du fichier et générer un fichier '.html'
6. Rechercher des notes avec le command `s` ou `search` + un mot-clé ou `s` ou `search` + un mot-clé + -r title/projet/context
7. Supprimer des fichiers '.adoc' et '.html' avec le command `d` ou `delete` + un nom du fichier
8. Vérifier si le fichier est bien supprimé avec le command `l` ou `list`,
