## Liquibase
### Gradle
#### Diff
```bash
./gradlew clean
./gradlew liquibaseDiff
```

#### DiffChangeLog
```bash
./gradlew clean
./gradlew liquibaseDiffChangelog
```

#### Update
```bash
./gradlew clean
./gradlew liquibaseUpdate
```

### CLI
#### DiffChangeLog
```bash
liquibase diff-changelog --changelog-file=src/main/resources/db/changelog/changelog-master.yml \
--url=jdbc:postgresql://localhost:32768/mydatabase --username=myuser --password=secret \
--reference-url=hibernate:spring:nl.moukafih.demo.entities?dialect=org.hibernate.dialect.PostgreSQLDialect \
--reference-driver=liquibase.ext.hibernate.database.connection.HibernateDriver
```

#### Update
```bash
liquibase update --changelog-file=src/main/resources/db/changelog/changelog-master.yml \
--url=jdbc:postgresql://localhost:32768/mydatabase --username=myuser --password=secret
```
