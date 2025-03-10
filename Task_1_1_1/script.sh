javac -d out src/Main.java

# Генерация документации
javadoc -d doc src/Main.java

# Создание JAR файла
jar cfe Main.jar Main -C out .

# Запуск программы
java -jar Main.jar
