//create a variable para and fill using inner text
let para1 = document.createElement("p");
para1.innerText = "Creating para via js(RED class thing )";

//append it into the html file via .append(ELEMENT)
document.querySelector("body").append(para1);

//here red in a class in css
para1.classList.add("red");

//----------------------------------------------
//h3 element inserted which is blue in colour
let head3 = document.createElement("h3");
head3.innerText = "head 3 with blue colour";
document.querySelector("body").append(head3);

//adding blue colour to it
head3.classList.add("blue");
//-----------------------------------------------

let division = document.createElement("div");
document.querySelector("body").append(division);
division.classList.add("div_prop");

//appending para and another h1 inside it
let paradivision = document.createElement("p");
let h1division = document.createElement("h1");

paradivision.innerText = "This is the para inside divison";
h1division.innerText = "This is the heading 1 inside divison";

division.append(paradivision);
division.append(h1division);
