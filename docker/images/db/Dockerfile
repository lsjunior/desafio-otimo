FROM postgres:12.3
LABEL maintainer="Lourival Sabino <lourival.sabino.junior@gmail.com>"

ENV POSTGRES_PASSWORD otimo
ENV POSTGRES_USER otimo
ENV POSTGRES_DB otimo

COPY docker-entrypoint-initdb.d/* /docker-entrypoint-initdb.d/
