×

#  JAVAX-CIP - CI/CD implementálása Java projekten szabadon választható eszközökkel (3 nap)

Continuous Delivery pipeline kialakítása Java projekten

[A képzés oldala](https://www.training360.com/kepzes/javax-cip)

## Ismertető

A DevOps és a Continuous Delivery (folyamatos szállítás) olyan gyakorlatok és folyamatok összessége, amelyek célja a szoftverfejlesztés hatékonyságának és minőségének növelése.

A DevOps egy olyan munkamódszer, amely összehozza a szoftvertervezést, -fejlesztést és -üzemeltetést. Ennek a megközelítésnek az alapja a kollaboráció, a kommunikáció és az automatizálás.

A képzés célja a DevOps és a Continuous Delivery fogalmainak bemutatása. Gyakorlati feladatként egy pipeline kerül létrehozásra egy webes Spring Boot Java projekthez.

A képzés érdekessége, hogy a résztvevő szabadon választhat, hogy mely eszközt szeretné használni a gyakorlati feladat során:

  * Verziókövetés: GitHub vagy GitLab
  * Build eszköz: Maven vagy Gradle
  * Konténerizáció: Docker
  * CI eszköz: Jenkins vagy GitLab
  * Repository: Nexus vagy Artifactory
  * Statikus kódellenőrzés: SonarQube
  * Infrastructure as a code: Ansible
  * Futtató környezet: AWS EC2 vagy Kubernetes
  * Monitorozás: Prometheus és Grafana
  * Tracing: Zipkin
  * Naplózás: ElasticSearch/Fluentd/Kibana (EFK stack)



## Szükséges előképzettség

Biztonságos operációs rendszer, parancssor és szövegszerkesztő használat. Fejlesztési projektekben való részvétel. Programozói tudás nem szükséges.

Mivel a tananyagok egy része angol nyelvű, ezért alapfokú, dokumentumolvasás-szintű angol nyelvtudás szükséges. A képzés magyar nyelvű, magyar slide-ok alapján.

## Tematika

  * DevOps alapjai
  * Continuous Integration bemutatása, és az általa megoldott problémák
  * Continuous Delivery bemutatása, és az általa megoldott problémák
  * Continuous Deployment bemutatása
  * Pipeline fogalma
  * Maven projekt felépítése, wrapper, build lifecycle, phase, plugins és goals, függőségkezelés
  * Gradle projekt felépítése, wrapper, tasks, függőségkezelés
  * Unit tesztek futtatása, tesztlefedettség (Jacoco)
  * Csomagolás
  * Docker
  * Nexus és Artifactory repository manager, proxy és hosted repository-k, Java artifactok és Docker image-ek deploy-olása
  * Integrációs tesztek
  * Docker image előállítása, layers
  * Docker Compose
  * E2E UI tesztek futtatása Seleniummal
  * E2E API tesztek futtatása Postman/Newman használatával
  * Statikus kódelemzés SonarQube használatával, tesztlefedettség, Quality Profile, Quality Gate, SonarLint
  * DevSecOps alapok
  * Telepítés AWS EC2-re Ansible-lel, Linux service-ként
  * Telepítés Kubernetes környezetre
  * Verziókezelés, trunk based development, feature flags, feature branching, branching stratégiák
  * Jenkins architektúra, pipeline (`Jenkinsfile`), stages, steps, cache, verziószámozás, konténerizáció, credentials, artifacts, párhuzamos futtatás, manual step
  * GitLab architektúra, GitLab Runner, pipeline (`.gitlab-ci.yml`), cache, verziószámozás, konténerizáció, variables, artifacts, párhuzamos futtatás, manual step
  * Helm
  * Telemetria
  * Monitorozás Prometheus és Grafana használatával
  * Tracing Zipkin használatával
  * Naplózás ElasticSearch/Fluentd/Kibana használatával (EFK stack)