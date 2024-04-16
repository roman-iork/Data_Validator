### Hexlet tests and linter status:
[![Actions Status](https://github.com/roman-iork/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/roman-iork/java-project-78/actions)

### Continious Integration:
[![Java CI with Gradle](https://github.com/roman-iork/java-project-78/actions/workflows/CI_gradle_78.yml/badge.svg)](https://github.com/roman-iork/java-project-78/actions/workflows/CI_gradle_78.yml)

### Maintainability:
[![Maintainability](https://api.codeclimate.com/v1/badges/9adce84a919ce8fa5123/maintainability)](https://codeclimate.com/github/roman-iork/java-project-78/maintainability)

### Code Coverage:
[![Test Coverage](https://api.codeclimate.com/v1/badges/9adce84a919ce8fa5123/test_coverage)](https://codeclimate.com/github/roman-iork/java-project-78/test_coverage)


#### Data validator.
Allows to create a Validator object and to set a number of restrictions on it so that later check wheather the object to be checked is valid or not.

var validator = new Validator();

Validator can check three types of objects: string, integer and map. Let's call it schemas. Each schema is set by consequent method.

var strVal = validator.string();
var intVal = validator.integer();
var mapVal = validator.map();


Now we can set restrictions (will be described later) to our specified validator and than call method "isValid(<T> object)" to check if our object fits restrictions.

##### Setting restrictions.
String:
 - required() - means that our object can't be null or empty string
 - minLength(int length) - sets min length of the object
 - contains(String chars) - requires that our object contains this chars  

Restrictions are set by consequent method calls. The methods can be called as chain:
   strVal.required().minLengs(3).contains("abc");
   
This line means that the string we will check can't be null or "", must have in length 3 or more characters and contain "abc".

Integer:
 - required() - can't be null
 - positive() - must be >= 0
 - range(int start, int end) - must be between start and end inclusive
 
Map:
 - required() - can't be null
 - sizeof(int size) - must be of this size
 - shape(Map<String, schema<String>> schema) - is for nested structures. Schema's value contains a string schema<String> with already set number of restrictions. Method "isValid(Map<String, String> object)" takes as argument a Map<String, String>. If schema's key equals object's key and object's value is validated by schema's value, than shape method will return "true".

var num1 = Map.of("num", 5);
var num2 = Map.of("num", 3);

var validator = new Validator();
var intVal = validator.integer();
numVal.required().positive().range(4, 6);
var numSchema = Map.of("num", intVal);

var mapVal = validator.map();
mapVal.shape(numSchema);
mapVal.isValid(num1); // true
mapVal.isValid(num2); // false

isValid method can be called in chain:

var res = validator.integer().required().positive().range(1, 6).isValid(5); // true

A specified validator also remembers previous restrictions and renews them if consequent methods are called:

var intVal = validator.integer();

intVal.range(-5, -3);
intVal.isValid(-4); // true

intVal.positive();
intVal.isValid(4); // false

intVal.range(-5, 5);
intVal.isValid(4); // true

