# Test Analyste-Programmeur (Java Spring Boot)

## Commentaires

- J'ai utilisé un Dto(exemple de bonne pratique) pour decopouler le model de l'Api de la couche de persistance (entités), bien que les données soient identique. 
- One-to-many est déduite des ids dans les examples du .MD, bien qu'un many-to-many(an actor could participate in multiple films) aurait peut-etre été plus judicieux.
- Je n'ai testé qu'une seule méthode du Repo SpringDataJpa à titre d'exemple car je n'ai pas crée de methode custom.
- J'ai pas utilisé de profils pour les differents environnements (dev, test, int, qa, prod)
- Le FilmServiceTest est un test unitiare classique avec isolatiton du composant testé avec des dépendances mockés.
- Le FilmControllerTest test le controlleur avec les vrais dépendances(sans mocks et avec un context complet) dans un environnement similaire à la prod.  C'est un test
d'intégration.
- Le fait de charger un context entier rend l'execution des tests plus lente, de même que l'utilisation de @DirtiesContext et @MockBean.
- J'ai modifié la version de Gradle(à cause d'un conflit avec Intellij) 

## Validation du Post dans Postman

![Post assertions](https://raw.github.com/snouasria-it/screenshots/main/post_assertions.PNG)

## Validation du Get dans Postman

![Get assertions](https://github.com/snouasria-it/screenshots/blob/main/get_assertions.PNG)

## Jacoco coverage report

![Jacoco coverage report](https://github.com/snouasria-it/screenshots/blob/main/jacoco_report.PNG)

## Rapport d'execution des tests

![Rapport d'execuion des tests](https://github.com/snouasria-it/screenshots/blob/main/test_report.PNG)

Bonne réception et merci!

