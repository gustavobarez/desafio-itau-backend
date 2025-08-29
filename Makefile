.PHONY: default run build test docs clean deploy

default: run

run:
	mvn spring-boot:run

build:
	mvn clean package -DskipTests

test:
	mvn test

docs:
	mvn springdoc-openapi:generate

deploy:
	@echo "Deploy placeholder"

clean:
	mvn clean
