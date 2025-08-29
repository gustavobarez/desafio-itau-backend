# Itaú Unibanco - Desafio de Programação
## [Itaú Backend Challenge](https://github.com/feltex/desafio-itau-backend)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![SPRING_BADGE](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

This project is a **transaction management and statistics API** built with **Java 17** and **Spring Boot 3.5**. The API allows users to:

- Insert financial transactions with a value and timestamp.
- Retrieve statistics (count, sum, average, min, max) over transactions in the last 60 seconds.
- Clear all transactions in memory.

The project emphasizes **in-memory data storage** (no external database), **automatic integration testing**, **Swagger documentation**, **logging**, and **performance measurement** for the statistics calculation.

---

## 🚀 Features

- REST endpoints for transactions and statistics
- Validation for transaction requests
- Automated integration tests using Rest-Assured
- Swagger UI for API exploration
- Actuator for healthcheck and observability
- Logging of all operations and calculation timing
- Containerized using Docker

## 🏗️ Installation

To use this project, you need to follow these steps:

1. Clone the repository: `git clone https://github.com/gustavobarez/desafio-itau-backend.git`
2. Install the dependencies: `mvn clean package`
3. Run the application: `mvn spring-boot:run`

## ⚙️ Makefile Commands

The project includes a Makefile to help you manage common tasks more easily. Here's a list of the available commands and a brief description of what they do:

- `make run`: Run the application locally
- `make build`: Build the application and package a JAR
- `make test`: Run tests for all packages in the project.
- `make docs`: Generate Swagger API documentation
- `make docker-build`: Build the Docker image for the application
- `make docker-run`: Run the application in a Docker container
- `make clean`: Clean project build artifacts

To use these commands, simply type `make` followed by the desired command in your terminal. For example:

```sh
make run
```

## 🐳 Docker

This project includes a `Dockerfile` file for easy containerization and deployment. Here are the most common Docker commands you may want to use:

- `docker build -t your-image-name .`: Build a Docker image for the project. Replace `your-image-name` with a name for your image.
- `docker run -p 8080:8080 -e PORT=8080 your-image-name`: Run a container based on the built image. Replace `your-image-name` with the name you used when building the image. You can change the port number if necessary.

For more information on Docker, refer to the official documentation:

- [Docker](https://docs.docker.com/)

## 🛠️ Used Tools

This project uses the following tools:

- [Java](https://docs.oracle.com/en/java/javase/17/) for backend development  
- [Spring Boot](https://docs.spring.io/spring-boot/index.html) framework for building APIs  
- [Docker](https://docs.docker.com/) for containerization  
- [Swagger](https://swagger.io/) for API documentation and testing

## 💻 Usage

After the API is running, you can use the Swagger UI to interact with the endpoints for searching, creating, editing, and deleting job opportunities. The API can be accessed at `http://localhost:$PORT/swagger/index.html`.

Default $PORT if not provided=8080.

## 🤝 Contributing

To contribute to this project, please follow these guidelines:

1. Fork the repository
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit them using Conventional Commits
4. Push to the branch: `git push origin feature/your-feature-name`
5. Submit a pull request

---

## 📝 License

This project is licensed under the MIT License - see the LICENSE.md file for details.

## ❤️ Credits

This project was created by [Gustavo Barez](https://github.com/gustavobarez).





