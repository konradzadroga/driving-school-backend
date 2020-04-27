FROM openjdk:latest

COPY target/drivingschool.jar /drivingschool.jar

EXPOSE 8080

CMD ["java", "-jar", "drivingschool.jar"]

COPY ./docker-entrypoint.sh /docker-entrypoint.sh
COPY ./wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
