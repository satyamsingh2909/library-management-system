# Library Management System

## Overview
A Java console-based Library Management System developed as a Build Your Own Project. It demonstrates object-oriented programming, collections, exception handling, file I/O, modular design, and basic testing.

## Functional Modules
1. Book Management - view and add books.
2. Member Management - view registered members.
3. Issue/Return Management - issue and return books with validation.
4. Data Persistence - save and load book data using a text file.

## Technologies
- Java 17 or later
- Java Collections Framework
- File I/O
- Exception Handling

## Project Structure
```text
src/
├── Main.java
├── model/
│   ├── Book.java
│   ├── Member.java
│   └── IssueRecord.java
├── service/
│   ├── BookService.java
│   ├── MemberService.java
│   └── LibraryService.java
├── exception/
│   └── LibraryException.java
└── util/
    └── FileManager.java
tests/
└── TestRunner.java
data/
└── books.txt
```

## Requirements
Install JDK 17 or newer and make sure `java` and `javac` are available in the terminal.

Check:
```bash
java -version
javac -version
```

## Compile
From the project root:

### Windows PowerShell
```powershell
mkdir out -ErrorAction SilentlyContinue
javac -d out src\Main.java src\model\*.java src\service\*.java src\exception\*.java src\util\*.java
```

### Linux/macOS
```bash
mkdir -p out
javac -d out src/Main.java src/model/*.java src/service/*.java src/exception/*.java src/util/*.java
```

## Run
```bash
java -cp out Main
```

## Testing

Compile the application first, then compile the test:

### Windows PowerShell
```powershell
javac -cp out -d out tests\TestRunner.java
java -cp out TestRunner
```

### Linux/macOS
```bash
javac -cp out -d out tests/TestRunner.java
java -cp out TestRunner
```

Expected output:
```text
All tests passed.
```

## Notes
The program creates sample members and books on first run. Book availability is saved to `data/books.txt` when the application exits normally.
