let msg = document.querySelector("input");

document.addEventListener("keydown", function (event) {
  if (event.key === "ArrowUp") {
    msg.innerText = "Character moves up";
    console.log("Character moves up");
  } else if (event.key === "ArrowDown") {
    msg.innerText = "Character moves down";
    console.log("Character moves down");
  } else if (event.key === "ArrowLeft") {
    msg.innerText = "Character moves left";
    console.log("Character moves left");
  } else if (event.key === "ArrowRight") {
    msg.innerText = "Character moves right";
    console.log("Character moves right");
  }
});
