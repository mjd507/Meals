app="meals-api";

ps auxww | grep "$app" | tr -s ' ' | cut -d ' ' -f 2 | xargs kill -9
java -jar $app-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod &
