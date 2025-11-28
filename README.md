# learn-springboot-graphql

graphql study repo

* [Altair](https://altairgraphql.dev/)
* [GraalVM 22](https://www.oracle.com/java/technologies/javase/graalvm-jdk22-archive-downloads.html)

# GraphQL API 사용 가이드

## 서버 실행

### 1. Postgres 실행
```bash
docker compose up -d
```

### 2. Spring Boot 애플리케이션 실행
```bash
./mvnw spring-boot:run
```

## GraphiQL 접속

브라우저에서 다음 URL로 접속:
```
http://localhost:8080/graphiql
```

## API 예제

### Query Examples

#### 모든 책 조회
```graphql
query {
  books {
    id
    title
    author
    publishedYear
  }
}
```

#### ID로 특정 책 조회
```graphql
query {
  book(id: 1) {
    id
    title
    author
    publishedYear
  }
}
```

#### 저자로 검색
```graphql
query {
  booksByAuthor(author: "김") {
    id
    title
    author
    publishedYear
  }
}
```

#### 제목으로 검색
```graphql
query {
  booksByTitle(title: "Spring") {
    id
    title
    author
    publishedYear
  }
}
```

### Mutation Examples

#### 새 책 생성
```graphql
mutation {
  createBook(input: {
    title: "Spring Boot와 GraphQL"
    author: "김개발"
    publishedYear: 2024
  }) {
    id
    title
    author
    publishedYear
  }
}
```

#### 책 정보 수정
```graphql
mutation {
  updateBook(id: 1, input: {
    title: "Spring Boot와 GraphQL (개정판)"
    author: "김개발"
    publishedYear: 2025
  }) {
    id
    title
    author
    publishedYear
  }
}
```

#### 책 삭제
```graphql
mutation {
  deleteBook(id: 1)
}
```

## 샘플 데이터 생성 스크립트

GraphiQL에서 다음 뮤테이션들을 순서대로 실행하여 테스트 데이터를 생성할 수 있습니다:

```graphql
mutation {
  book1: createBook(input: {
    title: "Effective Java"
    author: "Joshua Bloch"
    publishedYear: 2018
  }) {
    id
    title
  }
  
  book2: createBook(input: {
    title: "Clean Code"
    author: "Robert C. Martin"
    publishedYear: 2008
  }) {
    id
    title
  }
  
  book3: createBook(input: {
    title: "Spring in Action"
    author: "Craig Walls"
    publishedYear: 2022
  }) {
    id
    title
  }
}
```

## 데이터베이스 직접 접속

PostgreSQL에 직접 접속하려면:

```bash
docker exec -it $(docker ps -qf "name=postgres") psql -U myuser -d mydatabase
```

테이블 확인:
```sql
\dt
SELECT * FROM books;
```

## 주의사항

- GraphQL endpoint: `http://localhost:8080/graphql`
- GraphiQL UI: `http://localhost:8080/graphiql`
- 애플리케이션 재시작 시 데이터가 유지됩니다 (JPA ddl-auto: update 설정)
- 데이터베이스를 초기화하려면: `docker compose down -v`




