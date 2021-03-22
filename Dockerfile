FROM openjdk:15.0.2-oraclelinux7

COPY build/libs/test-task-alfa-bank* /test-task-alfa-bank.jar

EXPOSE 24043

CMD [ "java" , "-jar" , "/test-task-alfa-bank.jar" ]