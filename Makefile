compile: run
	@java NBAPlayerSearchApp

test: run
	@javac TextUITester.java
	@java -jar junit5.jar -cp . -c NBAPlayerSearchTests
	

run: PlayerLoader.class SearchFrontEnd.class
	@javac NBAPlayerSearchApp.java
	@javac -cp .:junit5.jar NBAPlayerSearchTests.java

PlayerLoader.class: PlayerStats.class
	@javac PlayerLoader.java

PlayerStats.class: 
	@javac PlayerStats.java

SearchFrontEnd.class: SearchBackEnd.class
	@javac SearchFrontEnd.java

SearchBackEnd.class:
	@javac SearchBackEnd.java
