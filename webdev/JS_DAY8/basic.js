/* -------------------- Naming Conventions -------------------- 
   camelCase
   snake_case
   PascalCase
*/

// -------------------- Template Literals --------------------
// ( `text ${a+b} apple ${a} pineapple` )
let a = 9;
let b = 10;
console.log("The numbers are:", a, "and", b, "and their sum is", a + b); // normal
console.log(`The numbers are ${a} and ${b} and their sum is ${a + b}`); // Template Literals

// -------------------- Unicode of Letters --------------------
// "A" -> 65 (0x41), "a" -> 97 (0x61)

// -------------------- Linking JS file --------------------
// <script src="file.js"></script>

// -------------------- Alerts & Prompt --------------------
// (only work in browser, not Node.js)
console.log("Normal Console");
// alert("Popup Alerts"); // popup appears in browser, must press OK
console.error("Error Message");
console.warn("Warning Message");

/*
console.log("Enter Name:");
let name = prompt("Enter your name:");
console.log("Your name is:", name);
*/

// -------------------- String Methods --------------------

// trim()
let fruit = "  apple ";
console.log(fruit.trim()); // creates a new string (original is unchanged)

// toLowerCase / toUpperCase
let uc = fruit.toUpperCase();
let lc = fruit.toLowerCase();
console.log("Uppercase:", uc, " Lowercase:", lc);

// indexOf
console.log(fruit.indexOf("a")); // find first index of "a"

// Multi use of Methods
console.log(fruit.trim().toLowerCase());

// slice
// string.slice(start) --> starts from 'start' index and goes till end
// string.slice(start, end) --> extracts from 'start' to 'end-1'
// str.slice(-x) --> goes from (length - x) to (length - 1)
let str = "Pineapple";
console.log(str.slice(0, 4), " ", str.slice(-4));

// length
console.log('The length of word "Pineapple" is:', str.length);

// replace
console.log(str.replace("apple", "Guava"));

// repeat
console.log(str.repeat(5));

// -------------------- Array Basics --------------------
// pop, push, shift, unshift
let arr = [1, 2, 3, 4, 5, 6];
console.log(arr); // [1,2,3,4,5,6]

arr.pop(); // removes last element
console.log(arr); // [1,2,3,4,5]

arr.push(100); // adds at the end
console.log(arr); // [1,2,3,4,5,100]

arr.unshift("19292"); // adds at the start
console.log(arr); // ["19292",1,2,3,4,5,100]

arr.shift(); // removes from start
console.log(arr); // [1,2,3,4,5,100]

// -------------------- Array Searching --------------------
let arr1 = [100, 200, 300, 400, 500];
console.log(arr1.indexOf(300)); // returns index if exists else returns -1
console.log(arr1.includes(300)); // returns true if exists else returns false

// -------------------- Array Operations --------------------
console.log(arr1.concat(arr)); // concat
console.log(arr.reverse()); // reverse (mutates original array)
console.log(arr1.slice(0, 3)); // slice (non-mutating)

// splice
// array.splice(start, deleteCount, item1, item2, ...)
// -> Removes 'deleteCount' elements from 'start' index and inserts new items
// -> Modifies the original array
let arr2 = [10, 20, 30, 40, 50];
console.log(arr2.splice(2, 2, 99, 100)); // removes 30,40 and inserts 99,100
console.log(arr2); // [10,20,99,100,50]

// -------------------- Sorting --------------------
// Default sort is Lexicographical (like strings, not numbers!)
let nums = [10, 5, 100, 25, 1];
console.log(nums.sort()); // [1,10,100,25,5] -> WRONG numerically, sorted as strings

// Numeric Sort (ascending)
console.log(nums.sort((a, b) => a - b)); // [1,5,10,25,100]

// Numeric Sort (descending)
console.log(nums.sort((a, b) => b - a)); // [100,25,10,5,1]

// -------------------- IMPORTANT CONCEPTS --------------------

// Array Reference vs Value
let arrA = [1, 2, 3];
let arrB = [1, 2, 3];
console.log(arrA === arrB); // false (different memory)

let arrC = arrA; // arrC points to the SAME array as arrA
console.log(arrA === arrC); // true

arrC.push(99); // modifying arrC also changes arrA
console.log(arrA); // [1,2,3,99]
console.log(arrC); // [1,2,3,99]

// const with Arrays
const myArr = [10, 20, 30];
myArr.push(40); // Allowed
console.log(myArr);

// ❌ Not allowed: reassigning the reference
// myArr = [1,2,3]; // Error: Assignment to constant variable

// ✅ Note: const means "the reference cannot change"
// But elements inside can still be mutated

// -------------------- parseInt --------------------
// parseInt(string, radix)

console.log(parseInt("42")); // 42  (decimal by default)
console.log(parseInt("1010", 2)); // 10  (binary → decimal)
console.log(parseInt("A", 16)); // 10  (hexadecimal → decimal)
console.log(parseInt("07", 8)); // 7   (octal → decimal)
console.log(parseInt("abc123")); // NaN
console.log(parseInt("123abc")); // 123
console.log(parseInt("   99   ")); // 99
console.log(parseInt("3.14159")); // 3

// -------------------- parseInt vs Number() --------------------
// Number("123abc") → NaN (requires full valid number)
// parseInt("123abc") → 123 (parses until invalid part)

// -------------------- for...of --------------------
// Iterates over VALUES of an iterable (array, string, set, map)

let arrX = [10, 20, 30];
for (let val of arrX) {
  console.log(val); // 10, 20, 30
}
