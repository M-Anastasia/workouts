# Launch with Docker
To start database: 
1) Go to workouts/db
```sh
sudo docker build -t wdb-image .
sudo docker run --rm -d --network host --name wdb-container -p 5432:5432 wdb-image
```
To start service:
1) Build jar with maven
```sh
mvn clean package
```
2) Go to workouts/
```sh
sudo docker build -t workouts-image .
sudo docker run --rm -d --network host --name workouts -p 8080:8080 workouts-image
```