FROM adoptopenjdk:8u262-b10-jdk-openj9-0.21.0
LABEL maintainer="Lourival Sabino <lourival.sabino.junior@gmail.com>"

RUN apt-get update

RUN sed -i 's/# pt_BR.UTF-8 UTF-8/pt_BR.UTF-8 UTF-8/' /etc/locale.gen && \
    locale-gen pt_BR.UTF-8

RUN echo "ttf-mscorefonts-installer msttcorefonts/accepted-mscorefonts-eula select true" | debconf-set-selections && \
    apt-get -y install curl fontconfig wget cabextract xfonts-utils fonts-crosextra-carlito fonts-crosextra-caladea ttf-mscorefonts-installer && \
    fc-cache -f -v

RUN echo "America/Sao_Paulo" > /etc/timezone && \
    apt-get install -y tzdata && \
    rm /etc/localtime && \
    ln -snf /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata

RUN apt-get clean

ENV LANG pt_BR.UTF-8
ENV LANGUAGE pt_BR.UTF-8
ENV LC_ALL pt_BR.UTF-8
ENV TZ=America/Sao_Paulo

COPY app.jar /

EXPOSE 18080
CMD [ "java", "-jar", "-Djava.awt.headless=true", "-Djava.security.egd=file:/dev/./urandom", "/app.jar" ]
