<template>

  <div class="about">
    <router-link to="/">Revenir</router-link> 
    <h1>Bienvenue sur la page des Besoins</h1>
    <h2>3) Implémentation des extensions</h2>
    <h3>3.1) Nouvelles implémentations coté serveur (Backend)</h3>
    <h4>Besoin 22: Filtre conversion couleur en gris</h4>
    <p>Un filtre qui transforme une image de couleur en niveau de gris, cela fonctionne correctement en choisissant l'image que l'on veut appliquer le filtre puis appuyer sur "Apply gray filter" sur le tableau de liste deroulante.</p>
    <h4>Besoin 23: 3 filtres de couleur rouge, vert, bleu</h4>
    <p>Nous avons 3 filtres de couleur rouge, vert et bleu, l'utilisation est la même que celui du filtre de gris. ( A noté que pour les images de très grandes tailles le temps de calcul peut être long)</p>
    <h4>Besoin 24: Filtre pixelisation</h4>
    <p>Nous avons implémenté un filtre qui pixelise l'image selectionné nous avons borné les paramètres pour évité un temps de calcul trop long.</p>
    <h3>3.2) Nouvelles implémentations coté client (Frontend)</h3>
    <h4>Besoin 25: Epuration et amélioration de l'interface graphique de l'utilisateur</h4>
    <p>Nous avons supprimé tous les informations inutiles comme par exemple le logo Vuejs et les directions Home et About puis nous avons ajouté un fond de page fixe et un logo à notre projet.
Nous avons ajouté un "tableau" liste deroulante qui contient les filtres gris, rouge, bleu et vert.
Les images selectionnées sont tous de tailles fixes, elles sont étirées en longueur et en hauteur</p>
    <h4>Besoin 26: Une page suplémentaire spécifique pour les Besoins nouveaux et un lien pour notre gitlab cremi</h4>
    <p>Nous avons ajouté une page annexe où il y a tous les nouveaux besoins que nous avons ajoutés. Vous devez cliqué sur "Les Besoins" pour aller sur la page et cliquer sur "Revenir" pour revenir sur la page principale. Vous pouvez donc soit lire les Besoins dans le README.md sur le gitlab du cremi soit le lire sur la page annexe dédiée ou soit sur le fichier pdf écrit en Latex à la racine de notre projet.</p>
    <h4>Besoin 27: Gestion des erreurs</h4>
    <p>Sur certains algorithmes où il faudra mettre un paramètre, il y aura une alerte/pop-up et un message correspondant à l'erreur (Si le paramètre est grand ou petit ou pas de paramètre du coup par exemple). </p>
    <h4>Besoin 28: Un bouton pour revenir sur l'image original</h4>
    <p>Nous avons ajouté un bouton pour revenir sur l'image original après application d'un filtre.</p>
  </div>

</template>
