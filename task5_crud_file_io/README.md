# Task 5: CRUD Application with File I/O

Enhanced version of Task 3. Tasks are saved to `tasks.txt` and loaded automatically on the next run.

## Features
- Saves after every add, update and delete
- Loads existing tasks at startup
- Error handling: missing file, corrupted lines, IO errors

## How to run
```
javac TaskManagerFile.java
java TaskManagerFile
```

## Testing persistence
1. Run the program, add 2 tasks, choose Exit
2. Run again and choose View: tasks should still be there
3. Open `tasks.txt` to see the stored data
4. Delete `tasks.txt` and run again: the program starts fresh without crashing
