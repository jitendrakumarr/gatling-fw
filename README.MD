
### Setup in user machine:
    1. Setup git in your system(if not already) then clone this repo.
    3. setup your IDE for scala.
    4. Java (1.8) and mvn should be configured and environment path should be set.

### Directory Structure

```
src/test
    ├───resources
    │       gatling.conf
    │       logback.xml 
    │
    └───scala
        │   Engine.scala        [Dont change anything if you are not sure]
        │   IDEPathHelper.scala [Dont change anything if you are not sure]
        │   Recorder.scala      [Dont change anything if you are not sure]
        │
        └───simulations [This is the package where we will create our load test script]
                FirstSimulation.scala [Example load test file]

```

### Execute the Script using terminal:

```
mvn gatling:test -Dgatling.simulationClass=simulations.FirstSimulation
```