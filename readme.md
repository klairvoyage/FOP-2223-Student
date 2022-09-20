# FOP-2223-H08-Student

Die Vorlage zu Hausübung 08 der FoP im Wintersemester 2022/2023

Es folgt eine Zusammenfassung der wichtigsten Schritte zum Bearbeiten der Hausübung.
Eine detailliertere Version findet sich im Moodle-Forum.

## Voraussetzungen

- Installation von JDK 17 oder höher (empfohlen wird openjdk17)

## Bearbeitung

- Grundsätzlich kann die Hausübung mit jedem Gradle-Fähigen IDE oder Texteditor bearbeitet werden, jedoch wird die
  Verwendung von IntelliJ stark empfohlen und nur für diese Hilfe garantiert.

## Abgabe

### build.gradle.kts

Um die Abgabe zu erstellen, muss zunächst die `build.gradle.kts` angepasst werden. Die Felder `studentId`, `firstName`
und `lastName` müssen korrekt eingetragen werden, und dürfen **nicht** leer sein.

### prepareSubmission

Um nun die Abgabe zu erzeugen, muss der gradle-Task `prepareSubmission` ausgeführt werden. Das geht entweder direkt über
die Entwicklungsumgebung oder per Kommandozeile:
> Vorher muss natürlich der richtige Ordner per `cd <pfad>` ausgewählt werden!

- Unix-Basierende Systeme:

```sh
./gradlew prepareSubmission
```

- Windows:

```bat
./gradlew.bat prepareSubmission
```

die generierte Datei befindet sich dann im Ordner `build/libs/`.
> **Hinweis:** Die generierte Datei ist nur zur Abgabe gedacht und ist nicht ausführbar!
