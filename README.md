# Building the code

The project is an Eclipse Maven project.  Here's the steps I would use to check out the code:

* `git clone git@github.com:annieying/ca.mcgill.cs.konaila.git`
* In Eclipse, import `ca.mcgill.cs.konaila` as an Eclipse project
* Once the code is imported, the project is built using Maven which also downloads a number of libraries to your local machine.


# Parser

The parser for code fragments uses ANTLR 4 parser: http://www.antlr.org/

The grammar file is in: `./JavaFragment.g4`

The entry point of the code is in `./src/main/java/ca/mcgill/cs/konaila/chopper/Chopper.java`.  You can also see the test for a simple example of using that code: `src/test/java/ca.mcgill.cs.konaila.chopper/SmokeTest.java`.

# Summary server

Use Eclipse Launch Configuration named `SurveyServer.launch` in the `./launch-configs` folder, by right click that launch file and select `Run`.

Go to your broswer at: `http://localhost:8848/DevelopmentSetServer`
