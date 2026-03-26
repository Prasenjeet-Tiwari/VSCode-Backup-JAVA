/*
👉 Scope = Area where a variable is accessible.
Types of Scope in JS:
1. Global Scope
2. Function Scope
3. Block Scope
4. Lexical Scope
*/

/**************************************************
 * 1. Global Scope
 **************************************************/
let globalVar = "I am Global";

function printGlobal() {
  console.log(globalVar); // ✅ Accessible
}
printGlobal();
console.log(globalVar); // ✅ Accessible everywhere

/**************************************************
 * 2. Function Scope
 **************************************************/
function functionScope() {
  let a = 10; // function scope
  var b = 20; // function scope
  const c = 30; // function scope
  console.log(a, b, c); // ✅ Accessible inside
}
// console.log(a); // ❌ Error (not accessible outside)

/**************************************************
 * 3. Block Scope (let & const)
 **************************************************/
{
  let x = 100;
  const y = 200;
  console.log(x, y); // ✅ Accessible inside block
}
// console.log(x); // ❌ Error
// console.log(y); // ❌ Error

// var ignores block scope
{
  var z = 300;
}
console.log(z); // ✅ Works (var does not follow block scope)

/**************************************************
 * 4. Lexical Scope
 **************************************************/
// Inner functions can access variables of outer functions
function outer() {
  let name = "Apna College";
  function inner() {
    console.log("Hello from", name); // ✅ Can access outer var
  }
  inner();
}
outer();

/**************************************************
 * var vs let vs const
 **************************************************/
// var -> function scope + hoisted
// let, const -> block scope + temporal dead zone

console.log(hoisted); // ✅ undefined (hoisted)
var hoisted = "I am hoisted";

// console.log(notHoisted); // ❌ Error
let notHoisted = "TDZ error";

/**************************************************
 * Higher Order Function (HOF)
 **************************************************/
// Function that takes another function as input OR returns a function
function applyOperation(x, fn) {
  return fn(x);
}

function square(n) {
  return n * n;
}

console.log(applyOperation(5, square)); // 25

/**************************************************
 * Returning a function from another function
 **************************************************/
function greet(message) {
  return function (name) {
    return `${message}, ${name}`;
  };
}

let sayHi = greet("Hi");
console.log(sayHi("Prasenjeet")); // Hi, Prasenjeet

/**************************************************
 * Methods inside a function (Object with methods)
 **************************************************/
function createUser(name) {
  return {
    name: name,
    sayHi: function () {
      console.log("Hi, I am", this.name);
    },
  };
}

let user1 = createUser("Apna College");
user1.sayHi(); // Hi, I am Apna College

/*
👉 OBJECT LITERALS
- Objects store data in "key: value" pairs.
- Keys are always strings (if not, JS converts them to strings).
- Values can be any data type (string, number, array, object, function).
*/

let student = {
  name: "Prasenjeet",
  age: 20,
  marks: 95.5,
  isPass: true,
};

console.log(student);

/**************************************************
 * Accessing Object Values (2 ways)
 **************************************************/

// 1. Dot notation
console.log(student.name); // Prasenjeet

// 2. Bracket notation
console.log(student["age"]); // 20

/**************************************************
 * Object keys as strings
 **************************************************/
// Internally keys are stored as strings
let obj = {
  1: "one",
  true: "yes",
};

console.log(obj[1]); // "one"
console.log(obj["1"]); // "one" (same!)
console.log(obj[true]); // "yes"
console.log(obj["true"]); // "yes" (same!)

/**************************************************
 * Bracket [] vs Dot .
 **************************************************/
// Dot → only for valid identifiers
// Bracket → works with keys stored as strings (including special chars)

let car = {
  brand: "Tesla",
  "car model": "X",
};

console.log(car.brand); // Tesla
// console.log(car.car model); // ❌ Error
console.log(car["car model"]); // ✅ Works

/**************************************************
 * Array of Objects
 **************************************************/

let users = [
  { name: "Aman", age: 19 },
  { name: "Riya", age: 21 },
  { name: "Sam", age: 20 },
];

console.log(users[0].name); // Aman
console.log(users[1]["age"]); // 21

/**************************************************
 * MATH OBJECT
 **************************************************/

console.log(Math.PI); // 3.14159...
console.log(Math.E); // 2.718...
console.log(Math.ceil(4.2)); // 5
console.log(Math.floor(4.9)); // 4
console.log(Math.random()); // random num between 0 and 1

/**************************************************
 * Generate random number between 1 and 10
 **************************************************/

// Formula: Math.floor(Math.random() * (max - min + 1)) + min
let min = 1;
let max = 10;

let randomNum = Math.floor(Math.random() * (max - min + 1)) + min;
console.log("Random number (1 to 10):", randomNum);

// ===============================
// JavaScript Core Concepts Demo
// ===============================

// ---------- this keyword ----------

// Normal function
function show() {
  console.log("Normal function this:", this);
}
show(); // In browser -> Window, In Node -> global/undefined

// Inside object
let obj1 = {
  name: "JS",
  getName: function () {
    console.log("Object method this.name:", this.name);
  },
};
obj1.getName(); // "JS"

// Arrow function inside object
let obj2 = {
  name: "JS",
  arrow: () => console.log("Arrow this.name:", this.name),
  normal: function () {
    console.log("Normal this.name:", this.name);
  },
};
obj2.arrow(); // undefined (arrow takes parent's this)
obj2.normal(); // "JS"

// ---------- Function Scope vs Lexical Scope ----------
function outer() {
  let a = 10;
  function inner() {
    console.log("Lexical scope a:", a); // inner can access outer's var
  }
  inner();
}
outer();

// ---------- try and catch ----------
try {
  let x = y + 1; // error: y not defined
} catch (err) {
  console.log("Error caught:", err.message);
}

// ---------- Arrow functions & implicit return ----------

// Explicit return
const add1 = (a, b) => {
  return a + b;
};
// Implicit return
const add2 = (a, b) => a + b;

console.log("add1(2,3):", add1(2, 3));
console.log("add2(2,3):", add2(2, 3));

// ---------- setTimeout ----------
setTimeout(() => {
  console.log("setTimeout: runs after 2 sec");
}, 2000);

// ---------- setInterval + clearInterval ----------
let count = 0;
let id = setInterval(() => {
  console.log("setInterval tick:", ++count);
  if (count === 3) {
    clearInterval(id);
    console.log("Interval stopped!");
  }
}, 1000);

// ===============================
// 1. map()
// ===============================
// map() transforms each element of an array and returns a NEW array

let nums = [1, 2, 3];

// Example 1: double numbers
let doubled1 = nums.map(function (n) {
  return n * 2; // multiply each element by 2
});
console.log("map normal (double):", doubled1); // [2, 4, 6]

let doubled2 = nums.map((n) => n * 2);
console.log("map arrow (double):", doubled2); // [2, 4, 6]

// Example 2: real world → extract names
let students = [
  { name: "Alice", marks: 85 },
  { name: "Bob", marks: 70 },
];
let names = students.map((s) => s.name);
console.log("map real-world (names):", names); // ["Alice","Bob"]

// ===============================
// 2. filter()
// ===============================
// filter() keeps elements that pass the condition

// Example 1: even numbers
let evens1 = nums.filter(function (n) {
  return n % 2 === 0;
});
console.log("filter normal (evens):", evens1); // [2]

let evens2 = nums.filter((n) => n % 2 === 0);
console.log("filter arrow (evens):", evens2); // [2]

// Example 2: real world → students above 75 marks
let toppers = students.filter((s) => s.marks > 75);
console.log("filter real-world (toppers):", toppers); // [{name:"Alice",marks:85}]

// ===============================
// 3. every()
// ===============================
// every() → true if ALL elements satisfy condition

// Example 1: check if all positive
let allPositive1 = nums.every(function (n) {
  return n > 0;
});
console.log("every normal:", allPositive1); // true

let allPositive2 = nums.every((n) => n > 0);
console.log("every arrow:", allPositive2); // true

// Example 2: real world → check if all students passed
let allPassed = students.every((s) => s.marks >= 35);
console.log("every real-world (all passed?):", allPassed); // true

// ===============================
// 4. some()
// ===============================
// some() → true if AT LEAST ONE element satisfies condition

// Example 1: any even number?
let hasEven1 = nums.some(function (n) {
  return n % 2 === 0;
});
console.log("some normal (has even?):", hasEven1); // true

let hasEven2 = nums.some((n) => n % 2 === 0);
console.log("some arrow (has even?):", hasEven2); // true

// Example 2: real world → check if any topper
let anyTopper = students.some((s) => s.marks > 90);
console.log("some real-world (any topper?):", anyTopper); // false

// ===============================
// 5. reduce()
// ===============================
// reduce() → reduce array to a single value

// Example 1: sum of numbers
let sum1 = nums.reduce(function (acc, n) {
  return acc + n;
}, 0);
console.log("reduce normal (sum):", sum1); // 6

let sum2 = nums.reduce((acc, n) => acc + n, 0);
console.log("reduce arrow (sum):", sum2); // 6

// Example 2: real world → total marks
let totalMarks = students.reduce((acc, s) => acc + s.marks, 0);
console.log("reduce real-world (total marks):", totalMarks); // 155

// ===============================
// 6. Default Parameters
// ===============================
// Default values for arguments if not provided

// Example 1: normal function
function greet1(name = "Guest") {
  console.log("Hello", name);
}
greet1(); // Hello Guest
greet1("JS"); // Hello JS

// Example 2: arrow function
const greet2 = (name = "Guest") => console.log("Hello", name);
greet2(); // Hello Guest
greet2("Delta"); // Hello Delta

// ===============================
// 1. Spread Operator (...)
// ===============================

// 🔹 Expands (spreads) an array or object

// (a) Use in arrays
let arr = [1, 2, 3];
let arr2 = [4, 5];

// merge arrays
let merged = [...arr, ...arr2];
console.log("Merged arrays:", merged); // [1,2,3,4,5]

// copy array (new reference)
let copy = [...arr];
copy.push(99);
console.log("Original:", arr); // [1,2,3]
console.log("Copy:", copy); // [1,2,3,99]

// convert string → array of characters
let str = "apple";
let chars = [...str];
console.log("String to chars:", chars); // ["a","p","p","l","e"]

// (b) Use in objects
let obj1 = { a: 1, b: 2 };
let obj2 = { c: 3 };

// merge objects
let mergedObj = { ...obj1, ...obj2 };
console.log("Merged objects:", mergedObj); // {a:1,b:2,c:3}

// copy object
let copyObj = { ...obj1 };
copyObj.a = 99;
console.log("Original obj:", obj1); // {a:1,b:2}
console.log("Copy obj:", copyObj); // {a:99,b:2}

// ===============================
// 2. Rest Parameter (...)
// ===============================

// 🔹 Collects all remaining arguments into an array

// Example 1: sum of numbers
function sum(...nums) {
  return nums.reduce((acc, n) => acc + n, 0);
}
console.log("Sum:", sum(1, 2, 3, 4)); // 10

// Example 2: mix of normal + rest
function intro(first, ...hobbies) {
  console.log("Name:", first);
  console.log("Hobbies:", hobbies);
}
intro("Prasenjeet", "coding", "reading", "music");
// Name: Prasenjeet
// Hobbies: ["coding","reading","music"]

// ===============================
// 3. arguments Object
// ===============================

// 🔹 Available in NORMAL functions, not in arrow functions
// 🔹 Like rest, but array-like (not real array)

function demo() {
  console.log(arguments); // looks like array but not exactly
  console.log(arguments[0]); // first argument
}
demo("hi", "hello", 42);
// ["hi","hello",42]

// ===============================
// 4. Destructuring
// ===============================

// 🔹 Extract values from arrays or objects into variables

// (a) Array Destructuring
let arr3 = [10, 20, 30];
let [x, y, z] = arr3;
console.log("Array destructuring:", x, y, z); // 10 20 30

// skip elements
let [a, , c] = arr3;
console.log("Skip 2nd:", a, c); // 10 30

// with default value
let [p, q, r, s = 100] = arr3;
console.log("Default value:", p, q, r, s); // 10 20 30 100

// (b) Object Destructuring
let person = { name: "Alice", age: 21, city: "Delhi" };
let { name, age } = person; // variable names must match keys
console.log("Object destructuring:", name, age); // Alice 21

// rename variables
let { city: place } = person;
console.log("Renamed:", place); // Delhi

// with default
let { gender = "female" } = person;
console.log("Default gender:", gender); // female

// (c) Nested Destructuring
let user = {
  id: 1,
  profile: { username: "JS", email: "js@mail.com" },
};
let {
  profile: { username, email },
} = user;
console.log("Nested destructuring:", username, email); // JS js@mail.com
