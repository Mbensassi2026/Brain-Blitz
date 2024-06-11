import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "What is the capital of France?",
                new String[]{"Paris", "London", "Berlin", "Madrid"},
                "Paris"
        ));
        questions.add(new Question(
                "What year did the Titanic sink?",
                new String[]{"1905", "1912", "1918", "1923"},
                "1912"
        ));
        questions.add(new Question(
                "Who wrote 'To Kill a Mockingbird'?",
                new String[]{"Harper Lee", "J.D. Salinger", "Ernest Hemingway", "Mark Twain"},
                "Harper Lee"
        ));
        questions.add(new Question(
                "What is the hardest natural substance on Earth?",
                new String[]{"Gold", "Iron", "Diamond", "Platinum"},
                "Diamond"
        ));
        questions.add(new Question(
                "What planet is known as the Red Planet?",
                new String[]{"Jupiter", "Mars", "Venus", "Saturn"},
                "Mars"
        ));
        questions.add(new Question(
                "An ___ method shares the same name but a different number of parameters with another method in the same class.",
                new String[]{"overridden", "inherited", "overloaded", "copied"},
                "overloaded"
        ));
        questions.add(new Question(
                "Which statement in main() generates an error?",
                new String[]{
                        "Players newPlayers = new Players()",
                        "playerObject.setName(\"Jessica Smith\")",
                        "leagueName = playerObject.getLeague()",
                        "Players playerObject = new SoccerPlayers()"
                },
                "leagueName = playerObject.getLeague()"
        ));
        questions.add(new Question(
                "If class Animal can only be accessed by classes defined in the same package as Animal which is the appropriate class definition?",
                new String[]{"public class Animal", "class Animal", "private class Animal", "protected class Animal"},
                "class Animal"
        ));
        questions.add(new Question(
                "Declaring a member as ____ in the base class provides access to that member in the derived classes but not to anyone else.",
                new String[]{"public", "protected", "private", "constant"},
                "protected"
        ));
        questions.add(new Question(
                "Which is true given classes Boat and Car both derived from Vehicle?",
                new String[]{
                        "a Vehicle reference can be automatically converted to Boat reference",
                        "a Boat reference can be automatically converted to Car reference",
                        "a Boat reference can be automatically converted to Vehicle reference",
                        "automatic conversion is not an option"
                },
                "a Boat reference can be automatically converted to Vehicle reference"
        ));
        questions.add(new Question(
                "What is output?\n\npublic class Vehicle {\npublic void drive(){\nSystem.out.println(\"Driving vehicle\");\n}\n}\n\n\n\npublic class Plane extends Vehicle {\n@Override\npublic void drive(){\nSystem.out.println(\"Flying plane\");\n}\npublic static void main(String args[]) {\nVehicle myVehicle = new Plane();\nmyVehicle.drive();\n}",
                new String[]{"Driving vehicle", "Flying plane", "syntax error", "Driving vehicle\nFlying plane"},
                "Flying plane"
        ));
        questions.add(new Question(
                "___ refers to determining which program behavior to execute depending on data types.",
                new String[]{"Inheritance", "Polymorphism", "Overloading", "Overriding"},
                "Polymorphism"
        ));
        questions.add(new Question(
                "Which of the following relationships depicts an is-a relationship?",
                new String[]{"Building - School", "School - Classroom", "Classroom - Student", "Student - Building"},
                "Building - School"
        ));
        questions.add(new Question(
                "Which type of relationship is depicted between Student and School?\n\npublic class Student {\nprivate String name;\n}\npublic class School {\nStudent s;\n}",
                new String[]{"Has-a", "Is-a", "Kind-of", "There is no relationship between the two classes"},
                "Has-a"
        ));
        questions.add(new Question(
                "Which type of relationship is depicted between Building and School?\n\npublic class Building {\nprivate int squareFeet;\n}\npublic class School extends Building{\nprivate String schoolDistrict;\n}",
                new String[]{"Has-a", "Is-a", "Contains-a", "There is no relationship between the two classes"},
                "Is-a"
        ));
        questions.add(new Question(
                "Which method has been overridden?\n\npublic class Vehicle {\npublic void setID(int pID) { … }\npublic String getName(){ … }\n}\n\npublic class Plane extends Vehicle { public Plane(){ … } public void setID(int pID1 int pID2){ … } public void getName(String name){ … } public String getName(){ … }}",
                new String[]{"setID(int pID)", "Plane()", "setID(int pID)", "getName()"},
                "getName()"
        ));
        questions.add(new Question(
                "The @Override annotation is ___.",
                new String[]{"placed above an overridden method in the base class", "optional and placed above an overridden method in the derived class", "required and placed above an overridden method in the derived class", "placed above all methods in a derived class"},
                "optional and placed above an overridden method in the derived class"
        ));
        questions.add(new Question(
                "Which of the following is false?",
                new String[]{"Assembly language is easier to read than machine code", "Interpreted programs are executed line by line", "Machine code programs are usually hardware specific", "Compiled programs are easier to debug"},
                "Compiled programs are easier to debug"
        ));
        questions.add(new Question(
                "What is the main difference between compiled and interpreted program?",
                new String[]{"Interpreted program is converted into machine code and executes slower", "Compiled program is converted into machine code and executes slower", "Compiled program is converted into machine code and executes faster", "Interpreted program is converted into machine code and executes faster"},
                "Compiled program is converted into machine code and executes faster"
        ));
        questions.add(new Question(
                "The sample code snippet below would most likely be seen in a program that uses the _____ paradigm.\n\nqplot(x y)",
                new String[]{"procedural", "logical", "functional", "scripting"},
                "functional"
        ));
        questions.add(new Question(
                "Which paradigm does this code primarily use?\n\nclass Food{\npublic:    Food(int c){\nthis->callories = c;\n}\nprivate:\nint callories;\n}",
                new String[]{"Object-oriented programming", "Procedural programming", "Functional programming", "None of the above"},
                "Object-oriented programming"
        ));
        questions.add(new Question(
                "Which of the following languages has \"side-effects\"?",
                new String[]{"Haskell", "Prolog", "JavaScript", "Lisp"},
                "JavaScript"
        ));
        questions.add(new Question(
                "Which of the following languages is NOT an example of Imperative Paradigm?",
                new String[]{"Java", "COBOL", "Python", "Prolog"},
                "Prolog"
        ));
        questions.add(new Question(
                "Java is an example of:",
                new String[]{"Scripting Language", "Procedural Language", "Object Oriented Language", "Functional Language"},
                "Object Oriented Language"
        ));
        questions.add(new Question(
                "Which Programming Paradigm is the better choice for multithreaded environment?",
                new String[]{"Procedural", "Object Oriented", "Scripting", "Functional", "Logical"},
                "Functional"
        ));
        questions.add(new Question(
                "Which Programming Paradigm is the better choice for short program with very little calculation?",
                new String[]{"Procedural", "Object Oriented", "Scripting", "Functional", "Logical"},
                "Procedural"
        ));
        questions.add(new Question(
                "What programming paradigm uses a series of instructions that tell the computer what to do with the input in order to solve the problem?",
                new String[]{"Procedural", "OOP", "Declarative", "Functional"},
                "Procedural"
        ));
        questions.add(new Question(
                "What is the best programming paradigm?",
                new String[]{"Object-oriented programming", "Procedural programming", "Functional programming", "None of the above"},
                "None of the above"
        ));
        questions.add(new Question(
                "Which Programming Paradigm is the better choice for implementing a parking garage system where residents and outsiders can park their cars vans or buses?",
                new String[]{"Procedural", "Object Oriented", "Scripting", "Functional", "Logical"},
                "Object Oriented"
        ));
        questions.add(new Question(
                "During which compilation step register assignment is performed?",
                new String[]{"Generation of intermediate forms", "Code generation", "Code optimization", "Semantic analysis", "Syntactical analysis", "Lexical analysis"},
                "Code generation"
        ));
        questions.add(new Question(
                "During which compilation step derivation tree is constructed?",
                new String[]{"Generation of intermediate forms", "Code generation", "Code optimization", "Semantic analysis", "Syntactic analysis", "Lexical analysis"},
                "Syntactic analysis"
        ));
        questions.add(new Question(
                "The choice between bounded and unbounded iteration is a part of:",
                new String[]{"Syntax", "Semantics", "Pragmatics", "Implementation"},
                "Pragmatics"
        ));
        questions.add(new Question(
                "The instanceof operator will return true for checking if the object is checked against either its declared variable type and its created object type.",
                new String[]{"True", "False"},
                "True"
        ));
        questions.add(new Question(
                "Character is a primitive data type",
                new String[]{"True", "False"},
                "False"
        ));
        questions.add(new Question(
                "Java is an example of Object Oriented Programming",
                new String[]{"True", "False"},
                "True"
        ));
        questions.add(new Question(
                "Autoboxing is the automatic conversion of primitive types to the corresponding wrapper classes",
                new String[]{"True", "False"},
                "True"
        ));
        questions.add(new Question(
                "Functional programming paradigm uses a series of instructions that tell the computer what to do with the input in order to solve the problem",
                new String[]{"True", "False"},
                "False"
        ));
        questions.add(new Question(
                "java.util package contains the Scanner class",
                new String[]{"True", "False"},
                "True"
        ));
        questions.add(new Question(
                "Final Class member modifier makes a Java Class data member a constant",
                new String[]{"True", "False"},
                "True"
        ));
        questions.add(new Question(
                "The strings 'cabc' will be accepted by the following grammar:\n\nG=( {S A F }  {abc ( ) e} R S)\nS-> SabAF | c\nA-> (S)\nF -> cF | c",
                new String[]{"True", "False"},
                "False"
        ));
        questions.add(new Question(
                "“Student-person” relationship is the example of has-a-relationship",
                new String[]{"True", "False"},
                "False"
        ));
        questions.add(new Question(
                "Compiled program is converted into machine code and executes slower",
                new String[]{"True", "False"},
                "False"
        ));
        questions.add(new Question(
                "Which of the following best describes method overriding?",
                new String[]{
                        "Two or more methods with the same name but different argument counts and/or types.",
                        "Two or more methods with different names but the same argument counts and types.",
                        "The [re]implementation of a method that's been defined by an object's parent class.",
                        "The implementation of a method that's been defined by an object's implemented interface.",
                        "Two or more methods with the same name but different return types."
                },
                "The [re]implementation of a method that's been defined by an object's parent class."
        ));
        questions.add(new Question(
                "The java.lang.Math Static Utility Class offers many useful methods that can be used by client code without creating an instance of Math. The code fragment below shows the invocation of two such methods. Considering how these methods are used below what two Class member modifiers would have been used when defining/implementing the random() and pow() methods in java.lang.Math?\n\ndouble rand = Math.random();\ndouble result = Math.pow(2, 8);",
                new String[]{
                        "1) public, 2) final",
                        "1) public, 2) static",
                        "1) protected, 2) static",
                        "1) protected, 2) final"
                },
                "1) public, 2) static"
        ));
        questions.add(new Question(
                "What component of JDK clears the memory of unused instances?",
                new String[]{
                        "No such components. The developer has to clear the memory.",
                        "Trash Lookout",
                        "Memory cleared automatically once out of the scope.",
                        "Garbage Collector"
                },
                "Garbage Collector"
        ));
        questions.add(new Question(
                "If an attribute of an Animal class can only be accessed by classes defined in the same package and in Animal subclasses which is the appropriate class definition?",
                new String[]{"public", "none", "private", "protected"},
                "protected"
        ));
        questions.add(new Question(
                "Given class SimpleCar which line has a syntax error?\n\npublic class SimpleCar {\nprivate int odometer;\npublic void drive(int miles) {\nodometer = odometer + miles;\n}\n}\n\n1 Object objCar;\n2 objCar = new SimpleCar();\n3 System.out.println(objCar.toString());\n4 objCar.drive();",
                new String[]{
                        "line 1 - class Object is not defined",
                        "line 2 - a SimpleCar reference cannot be assigned to an Object reference",
                        "line 3 - toString( ) is not defined in class SimpleCar",
                        "line 4 - drive( ) is not defined in class Object"
                },
                "line 4 - drive( ) is not defined in class Object"
        ));
        questions.add(new Question(
                "The choice between bounded and unbounded iteration is a part of:",
                new String[]{"Syntax", "Semantics", "Pragmatics", "Implementation"},
                "Pragmatics"
        ));

        Collections.shuffle(questions);
        return questions.subList(0, 15); // Return 15 unique questions
    }
}
