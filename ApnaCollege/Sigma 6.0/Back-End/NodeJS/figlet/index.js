let figlet = require("figlet");
//whenever acquiring package no need to write location
//just the name

figlet("Prasenjeet", function (err, data) {
  if (err) {
    console.log("Something went wrong...");
    console.dir(err);
    return;
  }
  console.log(data);
});
