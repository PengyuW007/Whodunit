########Put all my java files in one file

Compile: javac A3.java

Run: java A3

When it shows
"Welcome to "whodunnit?"
How many computer opponents would you like?" 
type the number of AI players you want to add. Then do the guess to against the AI.

########For testsuite file...

Compile: javac -cp .:junit-platform-console-standalone-1.6.0.jar TestWhodunit.java

Run: java -jar junit-platform-console-standalone-1.6.0.jar --class-path . --scan-class-path

Then it will show the results of my test.