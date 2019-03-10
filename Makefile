build-and-test:
	./gradlew build

clean:
	./gradlew clean

tests:
	./gradlew test

configure-heroku-remote:
	heroku git:remote -a tide-level-api

push-to-heroku:
	git push heroku master