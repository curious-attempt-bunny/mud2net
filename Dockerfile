FROM pandeiro/lein:latest

RUN mkdir /project

WORKDIR /project

ADD project.clj ./

RUN lein deps

ADD src src
#ADD resources resources

EXPOSE 3000
CMD ["lein", "run"]