

var s = require("acorn")
try{
var hello = fs.readFileSync('Test.js').toString();
/* Use acorn to generate the AST of hello.js */
var ast = s.parse(hello);

}catch(err){
    console.log(err);
}