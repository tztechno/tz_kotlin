
myapp/
│
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── myapp/
│   │   │       ├── MyappApplication.kt
│   │   │       └── HelloController.kt
│   │   └── resources/
│   │       └── application.properties (または application.yml)
│   │
│   └── test/
│       └── kotlin/
│           └── myapp/
│               └── MyappApplicationTests.kt
│
├── build.gradle.kts
└── settings.gradle.kts

cd myapp
./gradlew build
./gradlew bootRun

http://localhost:8080

