FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=cplayerdb
ENV MYSQL_USER_NAME=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/cplayerdb
ADD ./target/favouriteservice-0.0.1-SNAPSHOT.jar /usr/src/favouriteservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "favouriteservice-0.0.1-SNAPSHOT.jar"]
