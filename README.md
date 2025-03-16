<div align="center">

# Dream Shops

</div>

## 1. Database setup

<details>

<summary>MySQL</summary>

```bash
docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql
```

</details>

![db_diagram.png](docs/images/db_diagram.png)

## 2. Project setup

<details>

```bash
./mvnw spring-boot:run #visit http://localhost:8080/swagger-ui/index.html
```

</details>

### 2.1 Project structure

```bash
dream-shops/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── hippiezhou/
│   │   │           └── dreamshops/
│   │   │               ├── config/
│   │   │               ├── controller/
│   │   │               ├── dto/
│   │   │               ├── mapper/
│   │   │               ├── service/
│   │   │               │   └── impl/
│   │   │               ├── repository/
│   │   │               ├── model/
│   │   │               ├── remote/
│   │   │               │   └── impl/
│   │   │               └── DreamShopsApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/
│               └── hippiezhou/
│                   └── dreamshops/
├── .gitignore
├── README.md
└── pom.xml
```

### 2.2 Sequence Diagram

```mermaid
%%{init: {"theme":"neutral"}}%%
sequenceDiagram
    actor  U as User
    participant C as Controller
    participant S as Service
    participant DB as Database
    rect rgb(245, 255, 250)
        note right of U: user login
        U ->> C: Request to login
        C ->> S: POST /api/v1/auth/login
        S ->> DB: Validate credentials
        DB -->> S: Credentials valid
        S ->> C: 200 OK (JWT Token)
        C ->> U: Login successful
    end
    rect rgb(245, 255, 250)
        note right of U: products list
        U ->> C: Request to get products
        C ->> S: GET /api/v1/products
        S ->> DB: Fetch all products
        DB -->> S: Products data
        S ->> C: 200 OK (Products list)
        C ->> U: Display products
    end
    rect rgb(245, 255, 250)
        note right of U: others...
        U ->> DB: Request
        DB -->> U: Response
    end

```

## Docs

- [Spring Boot, Spring Security, JWT Course – Shopping Cart Backend Java Project](https://www.youtube.com/watch?v=oGhc5Z-WJSw&ab_channel=freeCodeCamp.org)
- [搭建第一个Spring Boot项目](https://javabetter.cn/springboot/initializr.html)
- [](https://javabetter.cn/home.html)
- [](https://start.spring.io/)
- [](https://github.com/dailycodework/dream-shops)
- [](https://springdoc.org/)