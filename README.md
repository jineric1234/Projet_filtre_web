# 2 NOYAU COMMUN

## 2.1) Serveur
### Besoin 1: Initialiser un ensemble d’images présentes sur le serveur
Le server est lancé dans le dossier /resources/images, il enregistre bien tous les images présentes à l'interieur du dossier images, le format d'image qui est traité est le format JEPG (pas encore reussi a avoir aussi les images TIF).

### Besoin 2: Gérer les images présentes sur le serveur
La gestion des images présentes sur le serveur est bien implémenté, nous pouvons accéder/supprimer et ajouter une image via son identifiant puis construire la liste des images disponible.

### Besoin 3: Appliquer un algorithme de traitement d’image
Nous pouvons appliquer presque tous les algorithmes de traitemente d'image (voir plus bas).

## 2.2) Communication 
### Besoin 4: Transférer la liste des images existantes
Les informations presentes dans le serveur ont le tableau (id, name, type, size) demandé.

### Besoin 5: Ajout d’image
L'ajout d'image se fait correctement, idem avec la gestion d'erreurs.

### Besoin 6: Récupération d’images
La récupération d'images s'éffectue correctement, idem avec la gestion d'erreurs

### Besoin 7: Suppression d’image
La suppression d'image se fait mais il peut y avoir quelques problemes concernant les "id" et de leurs positionnements.

### Besoin 8: Exécution d’algorithmes par le serveur
L'exécution des algorithmes par le serveur ne se fait pas comme demandé, nous avons pour chaque algorithme une adresse différente fixe, il n'est donc pas dynamique. Par exemple avec luminosite avec un parametre à 50 et id=0 l'URL est : /images/a0/50.
On va effectuer le besoin demandé pour le deuxième rendu.

## 2.3) Client
### Besoin 9: Parcourir les images disponibles sur le serveur
Nous parcourons nos images à l'aide d'un carrousel avec des flèches directionnelles, nous avons une vignette de taille fixe pour les images trop grand mais concernant les petites images, elles sont laissées d'origine.

### Besoin 10: Sélectionner une image et lui appliquer un effet
Nous pouvons en effet choisir l'image que l'on souhaite pour appliquer un algorithme. On a decidé de laisser l'image choisi au dèbut plutot que creer une autre liste derroulante. Le filtre s'applique correctement.

### Besoin 11: Enregistrer une image
Nous pouvons sauvegarder dans son système le fichier image correspondante dans le carrousel avec le bouton "download" mais pas avec un traitement d'image.

### Besoin 12: Ajouter une image aux images disponibles sur le serveur
Je pense que l'ajout d'une image est persistant(il y a un ajout coté serveur).

### Besoin 13: Suppression d’image
L'image est supprimé correctement du serveur. 

## Traitement d'images

On à implementé tout les filtres mais ils nous manque les boutons pour pouvoir le filtre a appliquer. 

### Besoin 14: Réglage de la luminosité
L'utilisateur peut augmenter ou diminuer la luminosité de l'image sélectionnée grâce à une espace d'écriture, après cela il faut sélectionner son image grace à sa liste deroulante "id" et cliquer sur "Apply filer".

### Besoin 15: Égalisation d’histogramme
Pas encore implémenté.

### Besoin 16: Filtre coloré 
L'utilisation est la même mais il faut savoir que le paramètre H de HSV est compris dans l'interval [0,360]

### Besoin 17: Filtres de flou
L'utilisation est la même mais il ne faut pas utilisé un filtre moyenneur trop grand sinon le temps de calcul sera trop long.
Le filtre Gaussien n'est pas encore implémenté.

### Besoin 18: Filtre de contour
Le filtre de contour n'est pas encore implémenté

## 2.5) Besoins non-focntionnels
### besoin 19: Compatibilité du serveur
Environnement : Ubuntu 20.04 ou antérieur

### Besoin 20: Compatibilité du client
Navigateur web : Firefox
Après test le client ne fonctionne que sur Firefox.

### Besoin 21: Documentation d’installation et de test
Nous avons ecrit notre rapport des besoins sur ce fichier README.md car nous pouvons pas utiliser le fichier Latex.
Système d’exploitation : Linux Ubuntu
Navigateur web = Firefox
Nous avons testé sur Chrome, Safari cela ne fonctionne pas.

# 3 Implémentation des extensions

## 3.1) Nouvelles implémentations coté serveur (Backend)
### Besoin 22: Filtre conversion couleur en gris
Un filtre qui transforme une image de couleur en niveau de gris, cela fonctionne correctement en choisissant l'image que l'on veut appliquer le filtre puis appuyer sur "Apply gray filter" sur le tableau de liste deroulante.

### Besoin 23: 3 filtres de couleur rouge, vert, bleu
Nous avons 3 filtres de couleur rouge, vert et bleu, l'utilisation est la même que celui du filtre de gris. ( A noté que pour les images de très grandes tailles le temps de calcul peut être long)

### Besoin 24: Filtre pixelisation
Nous avons implémenté un filtre qui pixelise l'image selectionné nous avons borné les paramètres pour évité un temps de calcul trop long.

## 3.2) Nouvelles implémentations coté client (Frontend)
### Besoin 25: Epuration et amélioration de l'interface graphique de l'utilisateur
Nous avons supprimé tous les informations inutiles comme par exemple le logo Vuejs et les directions Home et About puis nous avons ajouté un fond de page fixe et un logo à notre projet.
Nous avons ajouté un "tableau" liste deroulante qui contient les filtres gris, rouge, bleu et vert.
Les images selectionnées sont tous de tailles fixes, elles sont étirées en longueur et en hauteur

### Besoin 26: Une page suplémentaire spécifique pour les Besoins nouveaux et un lien pour notre gitlab cremi
Nous avons ajouté une page annexe où il y a tous les nouveaux besoins que nous avons ajoutés. Vous devez cliqué sur "Les Besoins" pour aller sur la page et cliquer sur "Revenir" pour revenir sur la page principale. Vous pouvez donc soit lire les Besoins dans le README.md sur le gitlab du cremi soit le lire sur la page annexe dédiée ou soit sur le fichier pdf écrit en Latex à la racine de notre projet.

### Besoin 27: Gestion des erreurs
Sur certains algorithmes où il faudra mettre un paramètre, il y aura une alerte/pop-up et un message correspondant à l'erreur (Si le paramètre est grand ou petit ou pas de paramètre du coup par exemple). 

### Besoin 28: Un bouton pour revenir sur l'image original
Nous avons ajouté un bouton pour revenir sur l'image original après application d'un filtre.

