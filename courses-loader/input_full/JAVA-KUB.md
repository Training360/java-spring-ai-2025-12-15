×

# Kubernetes Java programozóknak (JAVA-KUB) (2 nap)

Container orchestration a gyakorlatban, azaz hogy készítsük fel, telepítsük és üzemeltessük Java alkalmazásunk Kubernetesben

## Ismertető

A képzés célja, hogy a résztvevők megismerjék a Kubernetes alapjait, és képesek legyenek Java alkalmazásaikat hatékonyan konténerizálni, skálázni és üzemeltetni Kubernetes környezetben. A képzés során gyakorlati példákon keresztül bemutatjuk a podok, deploymentek, service-ek és konfigurációkezelés működését, valamint foglalkozunk a monitoringgal, skálázással és CI/CD integrációval is. Az oktatás során a legjobb iparági gyakorlatokat követve biztosítjuk, hogy a résztvevők magabiztosan tudják alkalmazni a Kubernetes nyújtotta lehetőségeket saját projektjeikben.

## Szükséges előképzettség

Elvárt a Java programozási nyelv, valamely Java keretrendszer (pl. Spring vagy Jakarta EE) stabil ismerete, valamint minimális hálózati ismeretek.

Mivel a tananyagok egy része angol nyelvű, ezért alapfokú, dokumentumolvasás-szintű angol nyelvtudás szükséges. A képzés magyar nyelvű, magyar slide-ok alapján.

## Tematika

  * Bevezetés a Kubernetesbe 
    * Mi az a Kubernetes, és miért fontos?
    * Kubernetes architektúra és főbb komponensek
    * Node-ok, namespace-ek, podok, deploymentek, service-ek
  * Kubernetes telepítése és futtatása 
    * Kubernetes disztibúciók
    * kubectl
    * yaml fájlok szerkezete
  * Kubernetes alapfogalmak és gyakorlati használat (1,5 óra) 
    * Podok és konténerek kezelése
    * Pod életciklusa
    * Deployment és ReplicaSet
    * Deployment verziókezelés
    * DaemonSet
    * Job és CronJob
    * Readiness és Liveliness probe-ok
    * Felcsatolt kötetek
    * ConfigMap és Secret használata
    * Command és Argument
    * Környezeti változók
    * Service-ek és hálózatkezelés
    * Service-ek típusai
    * Portok
  * Java alkalmazások konténerizálása és futtatása Kubernetesben 
    * Java alkalmazások Dockerizálása
    * Multi-stage build és image méret optimalizálás
    * Spring Boot alkalmazás futtatása Kubernetesben
    * OpenJDK és JVM optimalizálás konténeres környezetben
    * Konfigurációkezelés Spring Boot alkalmazásban
    * Readiness és Liveliness probe-ok
  * Állapotkezelés Kubernetesben 
    * PersistentVolume és PersistentVolumeClaim
    * StatefulSet és adatbázisok Kubernetesben
  * Bevezetés a Kubernetes üzemeltetés és skálázás témaköreibe 
    * Taints & Tolerations, Node selector, Affinity és AntiAffinity
    * Horizontális és vertikális skálázás
    * Auto Scaling (HPA, VPA)
    * Logging
    * Monitoring (Prometheus, Grafana)
    * Tracing
  * Haladó Kubernetes témák Java fejlesztőknek 
    * Helm chart készítése és alkalmazások telepítése
    * Kustomize
    * Operator pattern és Java alapú operátorok (JOSDK)
    * Service Mesh és Istio alapok
    * RBAC és biztonság Kubernetesben
  * CI/CD és Kubernetes 
    * Kubernetes deployment pipeline (GitHub Actions, GitLab CI)
    * Rolling update és Canary deployment
    * ArgoCD és GitOps megközelítés